/*
NAME: DANIEL CHUNG
DATE: 11/15/2018
was working until a random bug appeared and
was not able to figure out the bug.
*/

#include<stdio.h>
#include<stdlib.h>
#include "racer.h"
#include <string.h>
#include <ctype.h>
#include <pthread.h>
#include <curses.h>
#include <unistd.h>


//global variables used
long sleep_timer;
int finish_line;
pthread_mutex_t lock;

/*
char* makeCar(char name[]){
	int count = 3;
	static char car[12];
	car[0] = '~';
	car[1] = 'O';
	car[2] = '=';
	for (int i = 0; i < (int)strlen(name); i++){
		car[count] = name[i];
		count++;
	}
	while (count < 10){
		car[count] = '-';
		count++;
	}
	car[10] = 'o';
	car[11] = '>';
	return car;
}
*/

//Initializes the global variables.
void init_racers(long milliseconds, int length){
	sleep_timer = milliseconds;
	finish_line = (length - MAX_CAR_LEN - 2);
}

//creates the racer with the memoryu allocation.
Racer* make_racer(char* name, int row){
	Racer *newRacer = (Racer*)malloc(sizeof(Racer));
	newRacer->row = row;
	newRacer->distance = 0;
	char* newgraphic = malloc(sizeof(char) * MAX_CAR_LEN);
	strcpy(newgraphic, "~O=");
	strcat(newgraphic, name);
	for (int w = 0; w < (signed)MAX_NAME_LEN - (signed)strlen(name) + 1; w++)
		strcat(newgraphic, "-");
	strcat(newgraphic, "o>");
	newRacer->graphic = newgraphic;
	return newRacer;
}

//frees the structure so the memory can be used again.
void destroy_racer(Racer *racer){
	free(racer->graphic);
	free(racer);
}

//main thread for the program.
void *run (void *racer){
	Racer* currentRacer = racer;
	mvaddstr(currentRacer->row, currentRacer->distance, currentRacer->graphic);

	while (currentRacer->distance < finish_line){
		int randomwait = rand() % sleep_timer;

		if (randomwait <= 3)
			currentRacer->graphic[1] = 'X';
		else {
			sleep(randomwait * 1000);
			currentRacer->distance++;
		}
		pthread_mutex_lock(&lock);
		move(currentRacer->row, 0);
		clrtoeol();
		mvaddstr(currentRacer->row, currentRacer->distance, currentRacer->graphic);
		refresh();
		pthread_mutex_unlock(&lock);
		if (randomwait <= 3){
			break;
		}
	}
	return NULL;
}

//main function.
int main(int argc, char* argv[]){
	int maxSpeed = DEFAULT_WAIT;
	int index = 1;
	int racers = 0;
	int row = 1;
	//printf("1\n");
	if (argc < 3){
		printf("usage: pt-cruisers [max-speed-delay] name1 name2 [name3...]\n");
		exit(1);
	}
	//printf("2\n");
	if (atoi(argv[1]) < 0){
		exit(1);
	}
	//printf("3\n");
	if (atoi(argv[1]) != 0){
		index++;
		maxSpeed = atoi(argv[1]);
		if (argc < 4){
			printf("usage: pt-cruisers [max-speed-delay] name1 name2 [name3...]\n");
			exit(1);
		}
	}
//	printf("4\n");
	Racer* array[argc - index];
	for(; index < argc; index++){
//		printf("5\n");
		if (strlen(argv[index]) < MAX_NAME_LEN){
//			printf("6\n");
			array[racers] = make_racer(argv[index], row);
			row++;
			racers++;
		}
		else {
			printf("Error: racer %s exceeds length limit 6.\n", argv[index]);
			exit(1);
		}
	}
//	printf("7\n");
	pthread_t racersThread[racers];
	initscr();
	cbreak();
	noecho();
	int width = getmaxx(stdscr);
	clear();
	refresh();
	init_racers(maxSpeed, width);
	void* valuereturn;
	int x, j;
	long y;
	srand(time(NULL));
	x = pthread_mutex_init(&lock, NULL);
	if (x){
		printf("Error: pthread_create() exits with %d\n", x);
		exit(1);
	}
	for (y = 0; y < racers; y++){
	//	printf("8\n");
		x = pthread_create(&racersThread[y], NULL, run, (void*) array[y]);
		if (x){
			printf("Error: pthread_create() exits with %d\n", x);
			exit(1);	
		}
	}

	for (y = 0; y < racers; y++){
	//	printf("9\n");
		pthread_join(racersThread[y], &valuereturn);
	}
	for (j = 0; j < racers; j++){
	//	printf("10\n");
		destroy_racer(array[j]);
	}
	mvaddstr(0, 0, "Race is over. Press any key to exit the ractrack!");
	getch();
	endwin();
	return 0; 
}
