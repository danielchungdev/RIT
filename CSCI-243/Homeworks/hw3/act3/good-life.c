/// bad-life.c
///
/// This is derived from faulty code downloaded by copy-paste in 2015, and 
/// modified to use curses for screen display.
///
/// This code needs serious work in these areas:
/// <ol>
/// <li>  syntax: there are a number of warnings that cause errors.
/// </li>
/// <li>  semantics: the game algorithm implementation is incorrect.
/// </li>
/// <li>  design: the implementation needs function and value refactoring.
/// </li>
/// <li>  style: formatting is poor; the mix of TAB and spaces indentation
/// needs correction, and spacing between tokens is inconsistent.
/// The course style puts the '{' at the end of the function header, not
/// on a line of its own, and function headers belong at the left margin.
/// </li>
/// <li>  documentation is almost non-existent.
/// </li>
/// <li>  proper, public C documentation uses /// or /** ... */ format comments.
/// </li>
/// </ol>
///
#define _DEFAULT_SOURCE 
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <ncurses.h>
#include <string.h>
#define maxRownCol 19
#define arraySize 20
//
// curses programs need to link with the -lcurses flag 
// For manual pages, run 'man curses' and
// also see https://github.com/tony/NCURSES-Programming-HOWTO-examples/
//


void header(void){
	printf("\n\t..Welcome to the Game of life..\n");
}	

void ultimateRule(int size, char life[][size]){
	int row;
	int col; 
	int neighbors = 0;
	char templife[size][size]; 
	//Copy into a temp 2d array so it wont affect when changing life[][].
	memcpy(templife, life, size * size);
	for (row = 0; row < maxRownCol; row++){
		for (col = 1; col < maxRownCol; col++){
			/*Counts for the neighbors regardless 
			if its alive or dead because there is 
			one rule that checks if its dead*/
			if(templife[row - 1][col - 1] == '*')
				neighbors++;
			if(templife[row - 1][col] == '*')
			  	neighbors++;
		   	if(templife[row - 1][col + 1] == '*')
			  	neighbors++;
		 	if(templife[row][col - 1] == '*')
			  	neighbors++;
		   	if(templife[row][col + 1] == '*')
				neighbors++;
			if(templife[row + 1][col - 1] == '*')
			  	neighbors++;
		   	if(templife[row + 1][col] == '*')
			  	neighbors++;
			if(templife[row + 1][col + 1] == '*')
			  	neighbors++;
			/*Checks to see if the cell is alive. If it is 
			it will check for the first 3 rules of conway's
			game of life.*/
			
			if ((neighbors < 2 || neighbors > 4) && life[row][col] == '*')
				life[row][col] = ' ';
			if (( neighbors == 2 || neighbors == 3) && life[row][col] == '*')
				life[row][col] =  '*';		 
			/*Checks to see if the cell is dead. If it is
			it will check for the 4th rule of conway's
			game of life.*/
			if (neighbors == 3 && life[row][col] == ' '  )
				life[row][col] = '*' ;
		}
	}
}

int main(void){	  
	//FIX 16 unused gen variable.
	int orgs;
	//FIX 15 undused variable b, a
	int i, row, col;
	int count = 0;
	int numrows; 
	int numcols;
	//FIX 13 unused variable
	//FIX 12 unused variable
	char life[arraySize][arraySize];
   	header();
	printf("\nPlease enter the initial number of organisms: ");
	scanf("%i", &orgs);
	// start curses environment and find boundaries of window
	initscr();                            // Start curses mode 
	getmaxyx( stdscr, numrows, numcols);  // this is a MACRO; no & needed
	mvprintw( numrows - 1, numcols / 4, "screen is %d wide by %d high\n",numcols, numrows);
        refresh();
	srand( 31 );
	//sets the life  in random locations that 
	for(i = 0; i<orgs; i++){
		row = rand();
		row %= arraySize;
		col = rand();
		col %= arraySize;
		//fix3 
		life[row][col] = '*';
	}
	//sets deadlife wherever there is no life.
	for(row = 0; row<arraySize; row++){
		for(col = 0; col<arraySize; col++){
			if(life[row][col] != '*')
				//fix3 
				life[row][col] = ' ';
			}
		}
	//Main loop of the program
	while ( 1 ) {
		move( 0, 0);
		for(row = 0; row<arraySize; row++){
			for(col = 0; col<arraySize; col++){
				//fix2 %c instead of %s 
				printw("%c", life[row][col]);
			}
			printw( "\n");
		 }
		move( 20, 0);	
		refresh();
		printw("generation: %d\n", count);
		count++;
		ultimateRule(arraySize, life);
		usleep(820181L);
	}
	endwin(); // End curses mode at the end of the program
	return 0;
}
