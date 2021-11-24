#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>

int main(int argc, char **argv) {
	if(argc < 2) {
		fprintf(stderr, "Usage : %s file_name \n", argv[0]);
		exit(EXIT_FAILURE);
	}
	
	const char *file_name = argv[1];
	int open_result = open(file_name, O_WRONLY | O_CREAT | O_EXCL, 0666);

	if(open_result < 0) {
		perror("open");
		exit(EXIT_FAILURE);
	}
	if(lseek(open_result, 10000, SEEK_SET) < 0) {
		perror("lseek");
		exit(EXIT_FAILURE);
	}
	char buf[] = "A";
	if(write(open_result, buf, 1) < 0) {
		perror("write");
		exit(EXIT_FAILURE);
	}
}
