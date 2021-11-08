#include <stdio.h> // fprintf(), perror()
#include <stdlib.h> // exit(), EXIT_FAILURE
#include <unistd.h> // read()
#include <fcntl.h> // open()
#include <string.h> // strlen()

#define MAX_LEN 121
int main(int argc, char *argv[]) {
        if(argc != 2) {
                fprintf(stderr,"Usage : %s fichier \n", argv[0]);
                exit(EXIT_FAILURE);
        }

        int result_open = open(argv[1], O_RDONLY);
        if( result_open < 0 ) {
                perror("Function open() : ");
                exit(EXIT_FAILURE);
        }

        char data[MAX_LEN];

        // ssize_t read(int fd, void *buf, size_t nbytes);
        int read_result = read(result_open, data, MAX_LEN);
        if( read_result < 0 ) {
                perror("Function read() : ");
                exit(EXIT_FAILURE);
        }

        data[read_result] = '\0';

        char* chaineRev = (char*) malloc( strlen(data) + 1);
        int i, j;
        for( i = 0, j = (strlen(data) - 1)  ; i < strlen(data) && j >= 0 ; i++, j--)
                chaineRev[i] = data[j];

        printf("%s \n", chaineRev);
        return 0;
} // main()