/* 
FILE: tm.c
AUTHOR: DANIEL CHUNG
PROJECT: PROJECT 1 LEXICAL ANALYSIS.
CLASS: CS243 
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "classes.h"

typedef struct Node{
	//struct to represent a certain node in the
	//transition matrix.
	int number;
	char action[1];
}Node;

void printHeaders(){
	//prints the headers.
	printf("Scanning using the following matrix:\n");
	printf("     0    1    2    3    4    5    6    7    8    9   10   11\n");
}

int getAccept(char filename[]){
	//This function returns the accepts 
	//that are in the TM description file.
	FILE *fp;
	int count = 0;
	int accept;
	char *ptr, buf[256], *token;
	fp = fopen(filename, "r");
	while ((ptr = fgets(buf, 256, fp)) != NULL ){
		token = strtok(ptr, " ");
		while (token != NULL){
			if (count == 1 ){
				accept = atoi(token);
				return accept;
			}
			if ( strcmp(token, "accept") == 0){
				count++;
			}
			token = strtok(NULL, " ");
		}
	}
	fclose(fp);
	return accept;
}

int getStart(char filename[]){
	//This function returns the starting point 
	//in the TM description file.
	FILE *fp;
	int count = 0;
	int start;
	char *ptr, buf[256], *token;
	fp = fopen(filename, "r");
	while ((ptr = fgets(buf, 256, fp)) != NULL ){
		token = strtok(ptr, " ");
		while (token != NULL){
			if (count == 1 ){
				start = atoi(token);
				return start;
			}
			if ( strcmp(token, "start") == 0){
				count++;
			}
			token = strtok(NULL, " ");
		}
	}
	fclose(fp);
	return start;
}

int getStates(char filename[]){
	//This function returns the amount of states 
	//that are in the TM description file.
	FILE *fp;
	int count = 0;
	int states;
	char *ptr, buf[256], *token;
	fp = fopen(filename, "r");
	while ((ptr = fgets(buf, 256, fp)) != NULL ){
		token = strtok(ptr, " ");
		while (token != NULL){
			if (count == 1 ){
				states = atoi(token);
				return states;
			}
			if ( strcmp(token, "states") == 0){
				count++;
			}
			token = strtok(NULL, " ");
		}
	}
	fclose(fp);
	return states;
}

void printTm(struct Node transitionMatrix[][N_CC], int states){
	int ro, co;
	printHeaders();
	for (ro = 0; ro < states; ro++){
		printf(" %d  ", ro);
		for (co = 0; co < N_CC; co++){
			//printf("ACTION:%s", transitionMatrix[ro][co].action);
			if (transitionMatrix[ro][co].number < 10){
				printf(" %d%s  ", transitionMatrix[ro][co].number, transitionMatrix[ro][co].action);
			}
			else{
				if (co == N_CC - 1){
					printf("%d%s", transitionMatrix[ro][co].number, transitionMatrix[ro][co].action);
				}
				else {
					printf("%d%s  ", transitionMatrix[ro][co].number, transitionMatrix[ro][co].action);
				}
			}
		}
		printf("\n");
	}
	
}
