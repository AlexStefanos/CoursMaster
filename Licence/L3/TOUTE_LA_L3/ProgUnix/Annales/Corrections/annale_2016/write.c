#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
 
int main(void)
{
    int filedesc = open("fil.txt", O_WRONLY | O_CREAT );
 
    if (filedesc < 0) {
        return -1;
    }
 
    if (write(filedesc, "This will be output to testfile.txt\n", 36) != 36) {
        write(2, "There was an error writing to testfile.txt\n", 43);
        return -1;
    }
 
    return 0;
}