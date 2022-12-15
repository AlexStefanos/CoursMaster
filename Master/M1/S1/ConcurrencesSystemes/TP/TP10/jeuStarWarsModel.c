// jeuStarWars.c
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include <curses.h>
#include <unistd.h>

#include <pthread.h>
#include <semaphore.h>

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
int xJedi = 0, yJedi = 0, xRobot = 0, yRobot = 0, score = 0;

void robot() {
	int dx = rand() % 2, dy = rand() % 2;

	if(dx == 0)
		dx = -1;
	if(dy == 0)
		dy = -1;
	srand(time(NULL));
	xRobot = rand() % COLS;
	yRobot = rand() % LINES;
	while(true) {
		int clone = 0;
		if((xRobot == COLS-1) || (xRobot == 0)) {
			dx = -dx;
			clone = 1;
		}
		if((yRobot == LINES - 1) || (yRobot == 0)) {
			dy = -dy;
			clone = 1;
		}
		pthread_mutex_lock(&mutex);
		mvaddch(yRobot, xRobot, ' ');
		xRobot += dx;
		yRobot += dy;
		mvaddch(yRobot, xRobot, 'R');
		refresh();
		pthread_mutex_unlock(&mutex);
		usleep(300000);
	}
}

void jedi() {
	int dx = 0, dy = 0;
	char c;
	srand(time(NULL));
	xJedi = rand() % COLS;
	yJedi = rand() % LINES;
	noecho();
	c = getch();
	echo();
	while((c == 'r') || (c == 'z') || (c == 's') || (c == 'q') || (c == 'd')) {
		if(c == 'r') {
			pthread_t thread;
			pthread_create(&thread, NULL, robot, (int *) (0, 0));
		} else {
			if(c == 'z') {
				dx = 0;
				dy = -1;
			}else if(c == 's') {
				dx = 0;
				dy = 1;
			}else if(c == 'q') {
				dx = -1;
				dy = 0;
			}else {
				dx = 1;
				dy = 0;
			}
			if(((xJedi == 0) && (dx < 0)) || ((xJedi == (COLS-1)) && (dx > 0)))
				dx = 0;
			if(((yJedi == 0) && (dy < 0)) || ((yJedi == (LINES-1)) && (dy > 0)))
				dy = 0;
			pthread_mutex_lock(&mutex);
			mvaddch(yJedi, xJedi, ' ');
			xJedi += dx;
			yJedi += dy;
			mvaddch(yJedi, xJedi, 'O');
			refresh();
			pthread_mutex_unlock(&mutex);
		}
		nonech();
		c = getch();
		echo();
	}
}

void etoile(int x, int y) {
	int dx = rand() % 2, dy = rand() % 2;
	
	if(dx == 0)
		dx = -1;
	if (dy == 0)
		dy = -1;
	srand(time(NULL));
	if(x == 0)
		x = rand() % COLS;
	if(y == 0)
		y = rand() % LINES;
	while(true) {
		int clone = 0;
		if((x == COLS-1) || (x == 0)) {
			dx = -dx;
			clone = 1;
		}
		if((y == LINES-1) || (y == 0)) {
			dy = -dy;
			clone = 1;
		}
		if(clone == 1) {
			pthread_t thread;
			pthread_create(&thread, NULL, etoile, (int *) (0, 0));
		}
		pthread_mutex_unlock(&mutex);
		mvaddch(y, x, ' ');
		x += dx;
		y += dy;
		if((x == xJedi) && (y == yJedi)) {
			score++;
			pthread_mutex_unlock(&mutex);
			pthread_exit(0);
		}else if((x == xRobot) && (y == yRobot)) {
			pthread_mutex_unlock(&mutex);
			pthread_exit(0);
		}else {
			mvaddch(y, x, '*');
			refresh();
			pthread_mutex_unlock(&mutex);
			usleep(300000);
		}
	}
}

// programme principal
int main(void){
	initscr();
	clear();

	pthread_t thread1, thread2, thread3;
	pthread_create(&thread1, NULL, etoile, (int *) (0, 0));
	pthread_create(&thread2, NULL, etoile, (int *) (10, 10));
	pthread_create(&thread3, NULL, jedi, (void *) NULL);
	pthread_join(thread1, NULL);
	pthread_join(thread2, NULL);
	pthread_join(thread3, NULL);
	endwin();
}

// Compilation et edition des liens
// gcc jeuStarWarModel.c   -lcurses -ltermcap







