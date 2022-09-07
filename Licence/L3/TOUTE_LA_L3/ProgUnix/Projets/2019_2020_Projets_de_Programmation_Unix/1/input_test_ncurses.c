/* ======================================================== */
/* Programme de familiarisation avec les ncurses.           */
/* Il permet de d�placer le curseur d'affichage et en       */
/* utilisant les fl�ches du clavier et d'afficher les       */
/* coordonn�es courantes du curseur                         */
/* COMPILATION: cc test_ncurses.c -o test_ncurses -lncurses */
/* ======================================================== */
#include <ncurses.h>
#define DELAI 15000
#define XMAX  25
#define YMAX  80
int x; // axe vertical bas 
int y; // axe horizontal droite
int x,y,k;
int c;

int main(int argc, char *argv[])
{
        initscr();
        erase();
        keypad(stdscr, TRUE);  /* enable keyboard mapping */
        (void) nonl();         /* tell curses not to do NL->CR/NL on output */
        (void) cbreak();       /* take input chars one at a time, no wait for \n */
        (void) noecho();       /* don't echo input */

        x=0;
        y=0;

        move(x,y);

        printw("*");
        refresh();
        for (c = getch();c!='q' && c!='Q';c = getch()){
           
           switch (c){
                  case KEY_LEFT :   // Traitement fl�che gauche
                       if (y>0){
                          y--;
                          erase();
                          move(x,y);
                          printw("X=%d,Y=%d",x,y);
                          refresh();
                       }
                       break;

                  case KEY_RIGHT :  // fl�che droite
                      if (y<YMAX){
                          y++;
                          erase();
                          move(x,y);
                          printw("X=%d,Y=%d",x,y);
                          refresh();
                      }
                      break;
                  case KEY_DOWN :   // fl�che bas
                      if (x<XMAX){
                          x++;
                          erase();
                          move(x,y);
                          printw("X=%d,Y=%d",x,y);
                          refresh();
                       }    
                       break;
                  case KEY_UP :      // fl�che haut
                      if (x>0){
                          x--;
                          erase();
                          move(x,y);
                          printw("X=%d,Y=%d",x,y);
                          refresh();
                      }
                      break;
                  case 'q' :        // q pour sortir
                  case 'Q' :
                       break;
	              default :        
           } // switch (c)
        } //for

        erase();
        refresh();
        endwin();
        return 0;
}
	
