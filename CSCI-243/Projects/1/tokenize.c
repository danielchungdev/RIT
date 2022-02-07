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
	//prints the transition matrix
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

//Takes a character and compares it to its
//ascii value and depending on the result 
//it returns a specific number/state.
int classification(int character){
	if (character >= 0 && character <= 127){
		//white space character = 0
		if (character == 32 || character == 9){
			return CC_WS; 
		}
		//newline chracter = 1
		if (character == 10){
			return CC_NEWLINE;
		}
		//alphabetic and underscore = 2
		if ((character > 64 && character < 91) || (character == 95) || (character > 96 && character < 123)){
			return CC_ALPHA;
		}
		//character 0 = 3
		if (character == 48){
			return CC_DIG_0;
		}
		//digits from 1 to 7 = 4
		if (character > 48 && character < 56){
			return CC_DIG_1_7;
		}
		//digits from 8 to 9 = 5
		if ((character == 56) || (character == 57) ){
			return CC_DIG_8_9;
		}
		//division operator = 6
		if (character == 47){
			return CC_CHAR_SLASH;
		}
		//multiplication operator = 7
		if (character == 42){
			return CC_CHAR_STAR;
		}
		//operators +. -, % = 8
		if ((character == 43) || (character == 45) || (character == 37) ){
			return CC_ARITH_OP;
		}
		//any other ascii value. = 9
		return CC_OTHER;
	}
	else if (character == EOF){
		//end of file = 10
		return CC_EOF;
	}
	else{
		//any non-ascii value = 11
		return 11;
	}
}

void scannerTm(Node transitionMatrix[][N_CC], int start, int accept){
	char ch;
	int nextState;
	int count = 0;
	int currentState = start;
	char buff[256] = {'\0'};
	//keeps reading tokens in.
	while(1 > 0){
		ch = getchar();	
		printf("%d ",currentState);
		nextState = classification(ch);
		currentState = transitionMatrix[currentState][nextState].number;
		//checks if the state is not an error state.
		if (currentState != 99){
			if (ch == EOF){
				printf("%d ", currentState);
				printf("EOF\n");
				break;
			}
			//keeps adding to to the recognized string
			if (currentState != accept){
				buff[count] = ch;
				count++;
			}
			//checks if you are in an accept state
			if (currentState == accept){
				printf("%d ", currentState);
				printf("recognized ");
				printf("'");
				printf("%s", buff);
				printf("'\n");
				strcpy(buff, "");
				currentState = start;
				count = 0;
			}
		}
		else {
			while (ch != ' '){
				ch = getchar();
			}
			printf("%d ", currentState);
			printf("rejected\n");
			strcpy(buff, "");
			count = 0;
			currentState = start;
		}
	}
}

void startMatrix(struct Node transitionMatrix[][N_CC], int states, char filename[]){
	//Opens the file and checks that its not NULL
	FILE *fp;
	fp = fopen(filename, "r");
	if (fp == NULL){
		perror(filename);
		exit( EXIT_FAILURE );
	}
	//Variables that will be used along the program
	int count = 0;
	int r, c, row, column, number;
	char *ptr, *token;
	char buf[256], action[2];
	//This initializes the 2d array with the 99d for 
	//all of them which will later be updated on how
	//the tm file says so.
	for (r = 0; r < states; r++){
		for(c = 0; c < N_CC; c++){	
			transitionMatrix[r][c].number = 99;
			strcpy(transitionMatrix[r][c].action, "d");
		}
	}
	//Main loop to get through all the tokens of the information
	//that the matrix will contain.
	while ((ptr = fgets(buf, 256, fp)) != NULL ){
		token = strtok(ptr, " ");
		while (token != NULL){
			//these count if cases are used to 
			//iterate through the first 6 tokens 
			//as they are states, start and accept.
			if (count == 1 ){
				count++;
			}
			else if (count == 3){
				count++;
			}
			else if( count == 5){
				count++;
			}
			else if (count > 5){
				//2d array is modified here.
				if (strlen(token) < 3){
					row = atoi(token);
				}else {
					sscanf(token, "%d/%d%c", &column, &number, action);
					transitionMatrix[row][column].number = number;
					strcpy(transitionMatrix[row][column].action, action);
				}
			}
			if ((strcmp(token, "states") == 0 ) || (strcmp(token, "start") == 0) || (strcmp(token, "accept")) == 0 ){
				count++;
			}
			token = strtok(NULL, " ");	
		}
	}
	//Closing the file.
	fclose(fp);
}


int main(int argc, char *argv[] ){
	if (argc == 1){
		printf("Usage: ./tokenize tmfile\n");
		exit(EXIT_FAILURE);
	}
	int states = getStates(argv[1]);
	int start = getStart(argv[1]);
	int accept = getAccept(argv[1]);
	struct Node transitionMatrix[states][N_CC];
	startMatrix(transitionMatrix, states, argv[1]);
	printTm(transitionMatrix, states);
	scannerTm(transitionMatrix, start, accept);
	return 0;
}
