#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <curses.h>
#include <pthread.h>
#include "functions.c"

#define MAX_CITY 10000
//Main function to run the program.
int main(int argc, char* argv[]){
	int ch, max_missiles = 0, city[MAX_CITY], x, row, col, tallestBuilding = 0;
	pthread_t missiles, attackA;
	char arr[5] = "file"
	char defense[80], attack[80];
	WINDOW *wnd;
	check_arguments(argc);
	wnd = initscr();
	noecho();
	getmaxyx(wnd, row, col);
	read_file(argv[1], defense, attack, max_missiles, city, tallestBuilding);
	char string[59] = "Enter 'q' to quit at the end of the attack, or control-C";
	print_middle(wnd, string);
	City newcity = into_structure(attack, defense, max_missiles, city, tallestBuilding);
	create_city(wnd, newcity.cityarray);
	refresh();
	Parameters param = adding_parameters(row, col, tallestBuilding);
	Parameters param2 = adding_parameters(row, col, max_missiles);
	pthread_create(&missiles, NULL, launch, &param);
	//pthread_create(&attackA, NULL, attack, &param2);
	while(1){
		refresh();
		ch = getch();
		if(ch == 'q'){
			break;	
		}
	}
	refresh();
	pthread_join(missiles, NULL);
	//pthread_join(attackA, NULL);
	endwin();
	printf("\n");
	return 0;
}	
