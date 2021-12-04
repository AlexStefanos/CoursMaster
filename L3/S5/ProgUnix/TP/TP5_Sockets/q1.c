#include <stdio.h>
#include <errno.h>
#include <strings.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

#define MAX_CONNEXION 5
#define MAX_NOM 40
#define MAX_LIGNE 80

struct sockaddr_in InfoSockEcoute;
struct sockaddr_in InfoSockClient;
char NomMachineServeur[Max_NOM], ligne[MAX_LIGNE];
int sockEcoute, sockService, portServeur;
socklen_t lgInfoSockClient;
struct hostent *infoMachineServeur, *infoMachineClient;
int bindOk, listenOK, oK, nr, i;

int main(int argc, char **argv) {
    if(argc < 2) {
        printf("Usage %s : Numero_port\n", argv[0]);
        exit(1);
    }
    printf("DEMARRAGE DU SERVEUR...\n");
    printf("RECHERCHE DE L'ADRESSE DE LA MACHINE DU SERVEUR\n");
    oK = gethostname(NomMachineServeur, MAX_NOM);
    if(oK < 0) {
        perror("Erreur 1 machine serveur\n");
        exit(1);
    }
    infoMachineServeur = gethostbyname(NomMachineServeur);
    if(infoMachineServeur == NULL) {
        perror("Erreur 2 machines serveurs");
        exit(1);
    }

}