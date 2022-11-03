#include <unistd.h>

int main() {
    int c;
    
    c = 'a';
    write(1, &c - 32, 1);
}