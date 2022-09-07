#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <stdio.h>

#define MAX_NOM 50
#define MAX_LIGNE 80

struct sockaddr_in infoSockServeur;
char nomMachineServeur[MAX_NOM], ligne[MAX_LIGNE];
int sockVersServeur, portServeur, connectOK, nw;
struct hostent *infoMachineServeur;
int main(int argc, char **argv) {
    if(argc < 3) {
        printf("\nUsage %s : Nom_machine_serveur Numero_port\n", argv[0]);
        exit(1);
    }
    errno = 0;
    printf("Recherche de la machine\n");
    strcpy(nomMachineServeur, argv[1]);
    infoMachineServeur = gethostbyname(nomMachineServeur);
    if(infoMachineServeur == NULL) {
        printf("\nErreur machine serveur inconnue\n");
        exit(1);
    }
    printf("Création de la socket de dialoguqe avec le serveur\n");
    sockVersServeur = socket(AF_INET, SOCK_STREAM, 0);
    if(sockVersServeur < 0) {
        perror("Erreur Socket\n");
        exit(1);
    }
    printf("Préparation de la connexion avec le serveur\n");
    bzero(&infoSockServeur, sizeof(infoSockServeur));
    infoSockServeur.sin_family = AF_INET;
    portServeur = atoi(argv[2]);
    infoSockServeur.sin_port = htons(portServeur);
    bcopy((char *)infoMachineServeur ->h_addr, (char *)&infoSockServeur.sin_addr, infoMachineServeur->h_length);
    printf("Connexion avec le serveur : \n");
    printf("IP : %s / Port : %d...\n", inet_ntoa(infoSockServeur.sin_addr), portServeur);
    connectOK = connect(sockVersServeur, (struct sockaddr*)&infoSockServeur, sizeof(infoSockServeur));
    if(connectOK < 0) {
        perror("Erreur connect\n");
        exit(1);
    }
    else
        printf("Connexion établie\n");
    printf("Entrer une chaîne de caractères : ");
    fgets(ligne, MAX_LIGNE, stdin);
    ligne[strlen(ligne)-1] = '\0';
    nw = write(sockVersServeur, ligne, strlen(ligne));
    if(nw < 0)
        perror("Erreur write");
    if(nw == 0)
        printf("Problème write\n");
    else
        printf("%d carctères envoyés au serveur\n", nw);
    printf("Fermeture de la socket de dialogue avec le serveur\n");
    close(sockVersServeur);
}
