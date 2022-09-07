#include <stdio.h>
#include <unistd.h>
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

struct sockaddr_in infoSockEcoute;
struct sockaddr_in infoSockClient;
char nomMachineServeur[MAX_NOM], ligne[MAX_LIGNE];
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
    oK = gethostname(nomMachineServeur, MAX_NOM);
    if(oK < 0) {
        perror("Erreur 1 machine serveur\n");
        exit(1);
    }
    infoMachineServeur = gethostbyname(nomMachineServeur);
    if(infoMachineServeur == NULL) {
        perror("Erreur 2 machines serveurs\n");
        exit(1);
    }
    portServeur = atoi(argv[1]);
    printf("Création de la socket d'écouté\n");
    sockEcoute = socket(AF_INET, SOCK_STREAM, 0);
    if(sockEcoute < 0) {
        perror("Erreur création socket\n");
        exit(1);
    }
    printf("Préparation du nommage de la socket\n");
    bzero(&infoSockEcoute, sizeof(infoSockEcoute));
    infoSockEcoute.sin_family = htons(AF_INET);
    infoSockEcoute.sin_port = htons(portServeur);
    bcopy((char *)infoMachineServeur->h_addr, (char *)&infoSockEcoute.sin_addr, infoMachineServeur->h_length);
    printf("Nommage de la socket d'écoute\n");
    bindOk = bind(sockEcoute, (struct sockaddr*)&infoSockEcoute, sizeof(infoSockEcoute));
    if(bindOk < 0) {
        perror("Erreur bind serveur");
        exit(1);
    }
    printf("Dimmensionnement de la file d'attente\n");
    listenOK = listen(sockEcoute, MAX_CONNEXION);
    if(listenOK < 0) {
        perror("Erreur listen");
        exit(1);
    }
    lgInfoSockClient = sizeof(infoSockClient);
    ligne[0] = ' ';
    while(ligne[0] != '#') {
        for(i = 0; i < MAX_LIGNE; i++)
            ligne[i] = ' ';
        printf("\n\tAttente d'une conneixon...\n");
        sockService = accept(sockEcoute, (struct sockaddr*)&infoSockClient, &lgInfoSockClient);
        printf("\n\tUne connexion vient d'arriver de : %s / %d\n", inet_ntoa(infoSockClient.sin_addr), ntohs(infoSockClient.sin_port));
        infoMachineServeur = gethostbyaddr(&infoSockClient, sizeof(infoSockClient), AF_INET);
        if(infoMachineServeur == NULL) {
            perror("Machine client inconnue du DNS\n");
            exit(1);
        }
        nr = read(sockService, ligne, MAX_LIGNE);
        if(nr < 0)
            perror("Erreur read");
        else {
            if(nr < MAX_LIGNE-1)
                ligne[nr] = '\0';
            else
                ligne[MAX_LIGNE-1] = '\0';
            if(ligne[0] != '#')
                printf("->%d caractères reçus : %s\n", nr, ligne);
        }
        printf("->Fin de la connexion en cours\n");
        printf("...Fermeture de la socket de travail\n");
        close(sockService);
    }
    printf("->Fermeture de la socket d'écoute\n");
    close(sockEcoute);
    printf("The End.\n");
}
