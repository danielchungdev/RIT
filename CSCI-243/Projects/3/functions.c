#include <stdio.h>
#include <stdlib.h>
#include <curses.h>
#include <string.h>
#include <pthread.h>
#define MAXCHAR 10000

typedef struct City{
	char attack[80];
	char defense[80];
	int maxmissiles;
	int tallestBuilding;
	int cityarray[MAXCHAR];
}City;

typedef struct Parameters{
	int row;
	int col;
	int tallest;
}Parameters; 
//Adding into a structure of parameters.
Parameters adding_parameters(int row, int col, int tallest){
	Parameters param; 
	param.row = row;
	param.col = col;
	param.tallest = tallest;
}
//Prints the information to quit in the middle.
void print_middle(WINDOW *wnd, char *word){
	int row, col;
	int wordlen = strlen(word);
	getmaxyx(wnd, row, col);
	mvaddstr(0, (col/2) - (wordlen/2), word);
}
//Checks for the correct amount of arguments
void check_arguments(int argc){
	if (argc < 2){
		printf("usage: ./threads config-file\n");
		exit(1);
	}
}
//puts the city into a city structure.
City into_structure(char attack[80], char defense[80], int missiles, int cityarray[MAXCHAR], int tallestBuilding){
	City newcity; 
	strcpy(newcity.attack, attack);
	strcpy(newcity.defense, defense);
	newcity.maxmissiles = missiles; 
	newcity.tallestBuilding = tallestBuilding;
	memcpy(newcity.cityarray, cityarray, MAXCHAR);
	return newcity;
}
//The defensive shield thread that helps move left
//and right the plataform to block missiles.
void* launch(void* param){
	int ch;
	ch = getch();
	Parameters * p = (Parameters*)param;
	int x = p->col / 2;
	mvaddstr(p->row / 2, x, "####");
	refresh();
	if (ch == 67){
		move(p->row, 0);
		x += 1;
		clrtoeol();
		refresh();
		mvaddstr(p->row / 2, x, "####");
		refresh();
	}
	if (ch == 68){
		move(p->row, 0);
		x -= 1;
		clrtoeol();
		refresh();
		mvaddstr(p->row / 2, x, "####");
		refresh();
	}
}
//The attack thread that shows the
//missiles coming from above.
void* attack (void* param){
	Parameters *p = (Parameters*) param;
	for(int i = 0; i < p->tallest; i++){
		mvaddstr(0, rand() % p->col,"|");
		refresh();
	}
}
//This function creates the city in the ncurses enviorment
void create_city(WINDOW *wnd, int city[MAXCHAR]){
	int row, col;
	getmaxyx(wnd, row, col);
	col = 0;	
	int i = 0, count = 0, b = 0;
	while (city[i] != 0){
		refresh();
		if (city[i] == 2){
			mvaddstr(row - (city[i]), b, "_");
		}
		if (city[i] > 2){
			for (int x = 2; x < city[i] + 1; x++){
				mvaddstr(row - x, b, "|");
			}
			b++;
			mvaddstr(row - city[i] - 1, b, "_");
			b++;
			for (int y = 2; y < city[i] + 1; y++){
				mvaddstr(row - y, b, "|");
			}
		}
		b++;
		i++;
	}
}
//This functions reads the file and puts all the information needed onto the passed variables 
//so they can later be used in the main function.
void read_file(char* filename, char defense[80], char attack[80], int max_missiles, int city[MAXCHAR], int tallestBuilding){
	FILE *fp; 
	int count = 1, index = 0; //keeping track of the current line of the file
	char str[MAXCHAR];
	fp = fopen(filename, "r");
	if (fp == NULL){
		printf("Could not open file %s\n", filename);
		exit(1);
	}
	//has to be removed
	fwrite(city, sizeof(city), 1, fp);
	while (fgets(str, MAXCHAR, fp) != NULL){
		//Checks to see if we are in line 1.
		if (str[0] == '#'){
			continue;
		}
		if (count == 1){
			strcpy(defense, str);
		}
		if (count == 2){
			strcpy(attack, str);
		}
		if (count == 3){
			max_missiles = atoi(str);
		}
		if (count > 3){
			char *pt;
			pt = strtok(str, " ");
			while (pt != NULL){
				int a = atoi(pt);
				if (a > tallestBuilding){
					tallestBuilding = a;
				}
				city[index] = a;
				index++;
				pt = strtok(NULL, " ");
			}
		}
		count++;
	}
	fclose(fp);
}
