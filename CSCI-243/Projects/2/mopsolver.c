/*
NAME: DANIEL CHUNG
CLASS: CS243
DATE: 11/3/2018
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include<unistd.h>
#include <sys/types.h>
#include <getopt.h>

//Structre declaration for a single node.
typedef struct Node{
	int value;
	int row; 
	int col; 
	int visited;
	int path;
	struct Node* up;
	struct Node* down; 
	struct Node* right; 
	struct Node* left;
}Node;
//Structure declaration for the queue.
typedef struct Queue{
	int size;
	int rear;
	int front;
	unsigned capacity; 
	struct Node** contents;
}Queue;
//This functions initializes a brand new
//queue structure.
Queue* createQueue(unsigned capacity){
	struct Queue* aQueue = (struct Queue*) malloc(sizeof(struct Queue));
	aQueue->capacity = capacity;
	aQueue->front = aQueue->size = 0;
	aQueue->rear = capacity - 1;
	aQueue->contents = malloc(aQueue->capacity * sizeof(struct Node));
	return aQueue;
}
//Implementation of the enqueue function
//for the queue implementation.
void enqueue(Node *aNode, Queue *aQueue){
	aQueue->rear = (aQueue->rear + 1) % aQueue->capacity;
	aQueue->contents[aQueue->rear] = aNode;
	aQueue->size = aQueue->size + 1;
}
//Implementation of the dequeue function
//for the queue implementation.
void dequeue(Queue *aQueue){
	Node *aNode = aQueue->contents[aQueue->front];
	aQueue->front = (aQueue->front + 1) % aQueue->capacity;
	aQueue->size = aQueue->size - 1;
}

int sizeofQueue(Queue *aQueue){
	return aQueue->rear - aQueue->front;
}

//This functions initializes a new a nodeStructure.
Node createNode(int value, int row, int col){
	Node aNode;
	aNode.value = value;
	aNode.row = row; 
	aNode.col = col;
	aNode.path = 0;
	aNode.visited = -1;
	aNode.up = NULL;
	aNode.down = NULL;
	aNode.right = NULL;
	aNode.left =NULL;
	return aNode;
}
/*
Failed test of insertion function.
void insertNeighbors(int a, int b, Node array[b][a]){
	for (int i = 0; i < b; i++){
		for (int j = 0; j < a; j++){
			if (j == 0){
			//left
				if (i == 0){
					array[i][j].east = &array[i][j + 1];
					array[i][j].south = &array[i + 1][j];
				}
				else if(i == 4){
					array[i][j].east = &array[i][j + 1];
					array[i][j].north = &array[i - 1][j];
				}
				else {
					array[i][j].north = &array[i - 1][j];
					array[i][j].east = &array[i][j + 1];
					array[i][j].south = &array[i + 1][j];
				}
			}
			else if(i == 0){
			//top
				if (j == 0){
					array[i][j].east = &array[i][j + 1];
					array[i][j].south = &array[i + 1][j];
				}
				else if (j == 4){
					array[i][j].west = &array[i][j - 1];
					array[i][j].south = &array[i + 1][j];
				}
				else {
					array[i][j].east = &array[i][j + 1];
					array[i][j].west = &array[i][j - 1];
					array[i][j].south = &array[i + 1][j];
				}
			}
			else if (i == 4){
			//right
				if (j == 0){
					array[i][j].east = &array[i][j + 1];
					array[i][j].north = &array[i - 1][j];
				}
				else if (j == 4){
					array[i][j].west = &array[i][j - 1];
					array[i][j].north = &array[i - 1][j];
				}
				else {
					array[i][j].west = &array[i][j - 1];
					array[i][j].east = &array[i][j + 1];
					array[i][j].north = &array[i - 1][j];
				}
			}
			else if (j == 4){
			//bottom
				if (i == 0){
					array[i][j].west = &array[i][j - 1];
					array[i][j].south = &array[i + 1][j];
				}
				else if(i == 4){
					array[i][j].west = &array[i][j - 1];
					array[i][j].north = &array[i - 1][j];
				}
				else {
					array[i][j].north = &array[i - 1][j];
					array[i][j].west = &array[i][j - 1];
					array[i][j].south = &array[i + 1][j];
				}
			}
			else {
			//middle
				array[i][j].north = &array[i - 1][j];
				array[i][j].south = &array[i + 1][j];
				array[i][j].west = &array[i][j - 1];
				array[i][j].east = &array[i][j + 1];
			}
		}
	}
}
*/
//This function inserts all the neighbors that are 
//not walls so only adds if they are a possible path.
//then it checks for all 4 boundaries so it doesn't
//go out of bounds. THen adds to up down left or right.
void insertNeighbors(int b, int a, Node array[b][a]){
	for (int i = 0; i < b; i++){
		for (int j = 0; j < a; j++){
			if (array[i][j].value == 0){
				if( i != 0 && array[i - 1][j].value != 1){
					array[i][j].up = &array[i - 1][j];
					//printf("Node[%d][%d] UP Inserting: Node[%d][%d]\n",i, j, array[i - 1][j].row, array[i - 1][j].col);
				}
				if (i != b - 1 && array[i + 1][j].value != 1){
					array[i][j].down = &array[i + 1][j];
					//printf("Node[%d][%d] DOWN Inserting: Node[%d][%d]\n",i, j, array[i + 1][j].row, array[i + 1][j].col);
				}
				if (j != 0 && array[i][j - 1].value != 1){
					array[i][j].left = &array[i][j - 1];
					//printf("Node[%d][%d] LEFT Inserting: Node[%d][%d]\n",i, j, array[i][j - 1].row, array[i][j - 1].col);
				}
				if (j != a - 1 && array[i][j + 1].value != 1){
					array[i][j].right = &array[i][j + 1];
					//printf("Node[%d][%d] RIGHT Inserting: Node[%d][%d]\n",i, j, array[i][j + 1].row, array[i][j + 1].col);
				}
			}
		}
	}
}
//This function format prints for the -h flag.
void optionH(){
	printf("USAGE:\nmopsolver [-hdsp]  [-i INFILE] [-o OUTFILE]\n");
	printf("Options:\n \t-h\t	Print this helpful message to stdout and exit.\n \t-d\t	Pretty print (display) the maze after reading.\n \t-s\t	Print shortest Solution steps.\n \t-p\t	Print an optimal path.\n \t-i INFILE\t	Read maze from INFILE.\n \t-o OUTFILE\t 	Write all output to OUTFILE.\n");
}
//Finds the amount of the columns. 
//it takes a file name.
int sizeofcols(char filename[]){
	FILE *file = fopen(filename, "r");
	int count = 1, size;
	char *ptr, buf[256];
	while ((ptr = fgets(buf, 256, file)) != NULL){
		if (count == 1){
			size = strlen(buf)/2;
			count++;
		}
	}
	return size;
}
//Finds the the amount of rows. 
//takes a file name
int sizeofrows(char filename[]){
	FILE *file = fopen(filename, "r");
	int size = 0; 
	char *buf = NULL;
	size_t len = 0;
	while ( getline(&buf, &len, file) > 0 ){
		size++;
	}
	return size;
}
//This function initialize the array with nodes 
// makes sure that it is completetly on the array. 
//Takes the file, and the size of the row.
void intoMatrix(char filename[], int a, Node array[][a]){
	FILE *file = fopen(filename, "r");
	int size = a;
	int col = 0, row = 0, count = 0;
	char *ptr, buf[256], *token;
	while ((ptr = fgets(buf, 256, file)) != NULL){
		token = strtok(ptr, " ");
		while (token != NULL){
			if(count < size){
				array[row][col] = createNode(atoi(token), row, col);
				//printf("array[%d][%d]: %d\n",row, col, array[row][col].value);
				col++;
				count++;
			}
			else{
				count = 0;
				col = 0;
				row++;
				array[row][col] = createNode(atoi(token), row, col);
				//printf("array[%d][%d]: %d\n",row, col, array[row][col].value);
				count++;
				col++;
			}
			token = strtok(NULL, " ");
		}
	}

}
//This function does BFS throughout the array which nodes are connected
//between themselves. It searches to see what nodes are 0 and then adds
//them to the queue and then dequeue the next node and repeats. Until it 
//has found the optimal path.
int BreathFirstSearch(int b, int a, Node array[b][a]){
	int queueSize = (a * b);
	Node *currentNode = &array[0][0];
	Queue *aQueue = createQueue(queueSize);
	enqueue(currentNode, aQueue);
	currentNode->visited = 1;
	currentNode->path = 1;
	while (aQueue->size != 0 ){		
		if (currentNode->up != NULL && currentNode->up->value == 0 && currentNode->up->visited == -1){
			enqueue(currentNode->up, aQueue);
			currentNode->up->visited = currentNode->visited + 1;
		}
		if (currentNode->down != NULL && currentNode->down->value == 0 && currentNode->down->visited == -1){
			enqueue(currentNode->down, aQueue);
			currentNode->down->visited = currentNode->visited + 1;
		}
		if (currentNode->right != NULL && currentNode->right->value == 0 && currentNode->right->visited == -1){
			enqueue(currentNode->right, aQueue);
			currentNode->right->visited = currentNode->visited + 1;
		}
		if (currentNode->left != NULL && currentNode->left->value == 0 && currentNode->left->visited == -1){
			enqueue(currentNode->left, aQueue);
			currentNode->left->visited = currentNode->visited + 1;
		}
		//printf("Size before dequeue: %d and Node[%d][%d].visited: %d\n",aQueue->size, b - 1, a - 1, array[b - 1][a - 1].visited);
		dequeue(aQueue);
		//printf("Size after dequeue: %d and Node[%d][%d].visited: %d\n",aQueue->size, b - 1, a - 1, array[b - 1][a - 1].visited);
		currentNode = aQueue->contents[aQueue->front];
		/*
		if (aQueue->size == 0 && array[b - 1][a - 1].visited == -1){
			printf("Here");
			break;
		}*/
	}
	return array[b - 1][a - 1].visited;
}
//printing function to make sure that the 2d array had a north value.
void check(int b, int a, Node array[b][a]){
	for (int i = 0; i < b; i++){
		for (int j = 0; j < a; j++){
			if ( (i == 0)|| ( i == 3) || (j == 0) || (j == 3)){
				printf("NULL\t\t\t");
			}
			else {
				printf("Node[%d][%d].north->value: %d\t", i, j, array[i][j].up->value);
			}
		}
		printf("\n");
	}
}
//This function pretty prints the maze with # as walls and 
// . as all the possible paths.
void printMaze(int b, int a, Node array[b][a], FILE* out){
	int i, j, p, x;
	int number;
	fprintf (out, "|");	
	for (p = 0; p < (a * 2) + 1; p++){
		fprintf(out, "-");
	}
	fprintf(out, "|");
	fprintf(out, "\n");
	for (i = 0; i < b; i++){
		if ( (i == 0) && (array[0][0].value == 0)){
			fprintf(out, "  ");
		}else {
			fprintf (out, "| ");
		}
		for (j = 0; j < a; j++){
			if (array[i][j].value == 1){
				if (j == a - 1){
					fprintf(out, "# ");
				}else {
					fprintf(out, "# ");
				}
			}
			else {
				if (j == a-1){
					fprintf(out, "* ");
					//fprintf(out, "%d ", array[i][j].visited);
				}else {
					fprintf(out, "* ");
					fprintf(out, "%d ", array[i][j].visited);
				}
			}
		}
		if (( i == a - 1) && array[b - 1][a - 1].value == 0){
			fprintf(out, " ");
		}
		else {
			fprintf (out, "|");
		}
		fprintf(out, "\n");
	}
	fprintf (out, "|");	
	for (p = 0; p < (a * 2) + 1; p++){
		fprintf(out, "-");
	}
	fprintf(out, "|");
	fprintf(out, "\n");
}
//This function backtracks the BFS aka the shortest path
//It is doing so marking the path on its way making sure 
//that it goes the shortest way by comparing it to the visited/
//It also starts from the end of the list up to the start.
//So if a solution takes 100 steps it will start from step 
//number 100 and go down to step 1.
void backtracking(int b, int a, Node array[b][a]){
	int i, end = 0;
	Node* currentNode = &array[b - 1][a - 1];
	int firstNode = array[b - 1][a - 1].visited;
	for (i = firstNode; i != end; i--){
		//printf("iteration: %d\n", i);
		if ((currentNode->left != NULL) && currentNode->left->visited == i){
			currentNode->path++;
			currentNode = currentNode->left;
		}
		if ((currentNode->right != NULL) && currentNode->right->visited == i){
			currentNode->path++;
			currentNode = currentNode->right;
		}
		if ((currentNode->up != NULL) && currentNode->up->visited == i){
			currentNode->path++;
			currentNode = currentNode->up;
		}
		if ((currentNode->down != NULL) && currentNode->down->visited == i){
			currentNode->path++;
			currentNode = currentNode->down;
		}
	}
}
//This functions takes a node and prints all 
//its neighboirs. Used to make sure that my add 
//neighbors was working properly.
void neighbors(Node node){
		printf("Node[%d][%d] ", node.row, node.col);
		printf("val: %d ", node.value);
		if (node.up != NULL){
			printf("UP: Node[%d][%d] ", node.up->row, node.up->col);
		}
		if (node.down != NULL){
			printf("DOWN: Node[%d][%d] ", node.down->row, node.down->col);
		}
		if (node.right != NULL){
			printf("RIGHT: Node[%d][%d] ", node.right->row, node.right->col);
		}
		if (node.left != NULL){
			printf("LEFT: Node[%d][%d] ", node.left->row, node.left->col);
		}
		printf("\n");
}
//This function determines wether there is a solution or not.
//prints out the result.
void solution(int b, int a, Node array[b][a], FILE* out){
	if (array[0][0].value == 1 || array[b - 1][a - 1].value == 1){
		fprintf(out, "No solution.\n");
	}
	else {
		int i = BreathFirstSearch(b, a, array);
		if (i != -1){
			fprintf(out, "Solution in %d steps.\n", i);
		}else {
			fprintf(out, "No solution.\n");
		}
	}
}
//This function traverses the 2d array of nodes and wherever it 
//sees that the backgtracking has marked the node as a optimal 
//path it changes to a + instead of a '.' .
void printOptimal(int b, int a, Node array[b][a], FILE *out){
	int i, j, p, x;
	int number;
	fprintf (out,"|");	
	for (p = 0; p < (a * 2) + 1; p++){
		fprintf(out,"-");
	}
	fprintf(out, "|");
	fprintf(out, "\n");
	for (i = 0; i < b; i++){
		if ( (i == 0) && (array[0][0].value == 0)){
			fprintf(out, "  ");
		}else {
			fprintf (out, "| ");
		}
		for (j = 0; j < a; j++){
			if (array[i][j].value == 1){
				if (j == a - 1){
					fprintf(out,"# ");
				}else {
					fprintf(out,"# ");
				}
			}
			else {
				if(array[i][j].path != 0){
					if (j == a - 1){
						fprintf(out,"+ ");
					}
					else {
						fprintf(out,"+ ");
					}
				}
				else {
					if (j == a - 1){
						fprintf(out,". ");
					}
					else {
						fprintf(out,". ");
					}
				}
			}
		}
		if (( i == a-1) && array[b-1][a-1].value == 0){
			fprintf(out," ");
		}
		else {
			fprintf (out,"|");
		}
		fprintf(out, "\n");
	}
	fprintf (out,"|");	
	for (p = 0; p < (a * 2) + 1; p++){
		fprintf(out, "-");
	}
	fprintf(out, "|");
	fprintf(out, "\n");
}
//Hlper function to print the neighbors.
void printneighbors(int b, int a, Node array[b][a]){
	for (int i = 0; i < b; i++){
		for (int j = 0; j < a; j++){
			neighbors(array[i][j]);
		}
	}
}
//main functions where it takes all of the code and 
//processes the command line.
int main(int argc, char * argv[]){	
	int opt;	
	int cols = sizeofcols(argv[3]);
	int rows = sizeofrows(argv[3]);
	int dflag = 0, sflag = 0, pflag = 0;
	FILE *OUT = stdout; 
	Node array[rows][cols];
	intoMatrix(argv[3], cols, array);
	insertNeighbors(rows, cols, array);
	BreathFirstSearch(rows, cols, array);
	backtracking(rows, cols, array);
	while ( (opt = getopt(argc, argv, "hdsp:i:o:") ) != -1 ){
		switch(opt){
		//O case in which it will see what has been flagged
		//and write to the file.
			case 'o':
				OUT = fopen(optarg, "w");
				if (dflag == 1){
					printMaze(rows, cols, array, OUT);
				}
				if (sflag == 1){
					solution(rows, cols, array, OUT);
				}
				if (pflag == 1){
					printOptimal(rows, cols, array, OUT);
				}
				break; 
		//H case shows the help commands.
			case 'h':
				optionH();
				break;
		//D case pretty prints the maze out.
			case 'd':
				dflag = 1;
				printMaze(rows, cols, array, OUT);
				break; 
		//S case prints out the amount of steps for a solution.
			case 's':
				sflag = 1;
				solution(rows, cols, array, OUT);
				break;
		//P case prints out the optimal path in the maze.
			case 'p':
				pflag = 1;
				printOptimal(rows, cols, array, OUT);
				break; 
			case 'i':		
				break; 
		}
	}
	return 0;
}
