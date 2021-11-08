#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

//SET_BIT(0 ou 1,   à quelle place,   de quel nombre)   
#define SET_BIT(etat, n, nbr)  etat == 1 ? (int) (nbr | 1 << (n-1)) : (int) (nbr & ~(0 | 1 << (n-1)))

//GET_BIT(quel place de bit, de quel nombre)
#define GET_BIT(n, nbr) (int) (nbr >> (n-1) & 0b1)

/*
 * format des donnees de "opts":
 *   1er bit : est-ce en arriere plan
 *   2 ------: y'a t'il redirection
 *   3 ------: flux sortie standard ?
 *   4 ------: flux d'entree ?
 *   5 ------: flux d'entree ?
 */
#define 	IS_BACKGROUND(n)	GET_BIT(1, n)
#define 	IS_REDIRECTED(n)	GET_BIT(2, n)
#define 	IS_STDOUT(n)		GET_BIT(3, n)
#define 	IS_STDIN(n) 		GET_BIT(4, n)
#define 	IS_STDERR(n) 		GET_BIT(5, n)

#define 	SET_BACKGROUND(n)	n = SET_BIT(1, 1, n)
#define 	SET_REDIRECTED(n)	n = SET_BIT(1, 2, n)
#define 	SET_STDOUT(n)		n = SET_BIT(1, 3, n)
#define 	SET_STDIN(n)		n = SET_BIT(1, 4, n)
#define 	SET_STDERR(n)		n = SET_BIT(1, 5, n)

#define		MAX_ARGS	20
#define		MAX_ARGS_LEN	40
#define		MAX_INPUT_LEN	MAX_ARGS * MAX_ARGS_LEN

void	dispTab(char **t)
{
	for(int i = 0; t[i] != NULL; i++) {
		printf("\tt[%d] = %s\n", i, t[i]);
	};
}

void	lancerCommande(char **cmd, int opts, char *redirection)
{
	pid_t	launcher,
		commande;
	int	launcherStatus,
		commandeStatus;
	
	launcher = fork();
	
	if(launcher == -1) {
		perror("Fork launcher fail ");
		exit(EXIT_SUCCESS);
	}
	else if(launcher == 0) { // CODE LAUNCHER
		commande = fork();
	
		if(commande == -1) {
			perror("Fork commande fail ");
			exit(EXIT_SUCCESS);
		}
		else if(commande == 0) { // LA VRAIE COMMANDE
			if(IS_BACKGROUND(opts))
				printf("[%d]\n", getpid());
			
			if(IS_REDIRECTED(opts)) {
				int fd = open(redirection, O_RDWR|O_CREAT, 0666);
				if(fd < 0) {
					perror("Impossible d'ouvrir le fichier de redirection ");
					exit(EXIT_FAILURE);
				}
				if(IS_STDOUT(opts)) {
					if(dup2(fd, STDOUT_FILENO) < 0) {
						perror("Impossible de dupliquer fd vers STDOUT ");
						exit(EXIT_FAILURE);
					}
				}
				else if(IS_STDIN(opts)) {
					if(dup2(fd, STDIN_FILENO) < 0) {
						perror("Impossible de dupliquer fd vers STDIN ");
						exit(EXIT_FAILURE);
					}
				}
				else if(IS_STDERR(opts)) {
					if(dup2(fd, STDERR_FILENO) < 0) {
						perror("Impossible de dupliquer fd vers STDERR ");
						exit(EXIT_FAILURE);
					}
				}
			}
			
			execvp(cmd[0], cmd);
			perror("Erreur lors de l'exec ");
			exit(EXIT_FAILURE);
		}
		else { // CODE DU LAUNCHER
			if(!IS_BACKGROUND(opts)) {
				if(waitpid(commande, &commandeStatus, 0) == -1) {
					perror("Erreur wait commande ");
					exit(EXIT_FAILURE);
				}
				else if(!WIFEXITED(commandeStatus)) {
					int numsign = WTERMSIG(commandeStatus);
					printf("Erreur commande : %d (status=%d) (msg=%s)\n", numsign, commandeStatus, strerror(numsign) );
				}
			}
			exit(EXIT_SUCCESS);
		}
	}
	else { // CODE DU PERE
		if(wait(&launcherStatus) == -1) {
			perror("Erreur wait launcher ");
			exit(EXIT_FAILURE);
		}
		else {
			if(!WIFEXITED(launcherStatus)) {
				int numsign = WTERMSIG(launcherStatus);
				printf("Erreur dans le launcher : %d (%s)\n",
				    numsign,
				    strerror(numsign)
				);
			}
		}
	}
}

char**	parseInput(char *input, int *opts, char **redirection)
{
	char	**args;
	char 	argsBuffer[MAX_ARGS][MAX_ARGS_LEN];
	char	redir_fich[MAX_ARGS_LEN];
	int	num_arg = 0,
		pos_arg = 0,
		nouvel_arg = 0,
		force_param = 0;
	
	*opts = 0;
	
	for(int i = 0; input[i] != '\0' && input[i] != '\n'; i++) {
		if(!force_param && (input[i] == '>' || input[i] == '<')) {
			SET_REDIRECTED(*opts);
			if(input[i] == '>') {
				if(input[i-1] == '2' || input[i-1] == '1') {
					nouvel_arg = 1;
					if(pos_arg != 0)
						argsBuffer[num_arg][pos_arg-1] = '\0';
					else {
						--num_arg;
						pos_arg = strlen(argsBuffer[num_arg]);
					}
				}
				if(input[i-1] == '2') {
					SET_STDERR(*opts);
				}
				else SET_STDOUT(*opts);
			}
			else SET_STDIN(*opts);
			
			int i_redir = 0;
			for(i++; input[i] != '&' && input[i] != '\n' && input[i] != ' '; i++, i_redir++) {
				redir_fich[i_redir] = input[i];
			}
			redir_fich[i_redir] = '\0';
			*redirection = strdup(redir_fich);
		}
		else if(!force_param && (input[i] == '&'))  {
			SET_BACKGROUND(*opts);
		}
		else if(!force_param && (input[i] == ' ')) {
			if(!nouvel_arg)
				argsBuffer[num_arg][pos_arg] = '\0';
			nouvel_arg = 1;
		}
		else if(!force_param && input[i] == '"')
			force_param = 1;
		else if(force_param && input[i] == '"')
			force_param = 0;
		else {
			if(nouvel_arg) {
				nouvel_arg = 0;
				++num_arg;
				pos_arg = 0;
			}
			argsBuffer[num_arg][pos_arg] = input[i];
			pos_arg++;
		}
	}
	argsBuffer[num_arg][pos_arg] = '\0';
	
	args = (char**)calloc(num_arg + 2, sizeof(char*));
	if(!args) {
		perror("erreur fork parseInput ");
		exit(EXIT_FAILURE);
	}
	for(int i = 0; i <= num_arg; i++) {
		args[i] = strdup(argsBuffer[i]);
	}
	return args;
}

int	main(void)
{
	char saisie[MAX_INPUT_LEN];
	int quitter = 0;
	
	
	char **args;
	int opts;
	char *redirection = NULL;
	
	do{
		printf("%s$ ", getenv("USER"));
		fgets(saisie, MAX_INPUT_LEN - 1, stdin);
		if(saisie[0] == '\0' || saisie[0] == '\n') ;
		else if(strncmp(saisie, "quit", 4) == 0 || strncmp(saisie, "exit", 4) == 0)
			quitter = 1;
		else {
			args = parseInput(saisie, &opts, &redirection);
			lancerCommande(args, opts, redirection);
			free(args);
		}
		
	} while(!quitter);
	
	printf("Au revoir ! \n");
	return EXIT_SUCCESS;
}
