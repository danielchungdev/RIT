//AUTHOR: DANIEL CHUNG
//CLASS: CS243
//HOMEWORK 5
//10/18/2018
#include <stdio.h>
#include <stdlib.h>
#include<string.h>
#include "tree.h"
//Global variable that saves the current line.
static int currentLine = 1;

//Insert word function.
void insert_word( TreeNode** root, const char *word){ 
	//chekcs if the root is null meaning that the you have to 
	//create a new treenode and add it.
	if (*root == NULL){
		//Allocates memory for the node.
		TreeNode *new = (TreeNode* ) malloc(sizeof(TreeNode));
		new->word = strdup(word);
		new->frequency = 1;
		new->refLength = 1;
		new->references = malloc(sizeof(int));
		new->left = NULL;
		new->right = NULL;
		new->references[0] = currentLine;
		//sets the root equal to the node we just made.
		*root = new;
	}
	//sees if the word is already inside the the tree
	// and if it is then just add once to its frequency 
	//and add it to its references list.
	else if(strcasecmp((*root)->word, word) == 0){
		(*root)->frequency++;
		(*root)->refLength++;
		(*root)->references[(*root)->refLength - 1] = currentLine;
	//checks if the word is less and puts it on the left side of the 
	//tree
	}
	else if(strcasecmp((*root)->word, word) < 0){
		insert_word(&(*root)->left, word);
	}
	//else it puts it on the right side.
	else {
		insert_word(&(*root)->right, word);
	}
}

//traverses and prints the tree.
void traverse_tree(const TreeNode* root){ 
	int i;
	//basecase for the recursion.
	if (root == NULL){
		return; 
	}
	//will go to the last node.
	else {
		int listlen = root->refLength;
		//goes all the way to the right side of the tree
		traverse_tree(root->right);
		//prints out the last root node. Word and its frequency
		printf("%s (%d): ", root->word, root->frequency);
		//prints the references
		for (i = 0; i < listlen; i++){
			if (i + 1 == listlen){
				//checks if it's the last member of the list.
				printf(" %d", root->references[i]);
			}else {
				//prints out when its not the last character.
				printf(" %d,", root->references[i]);
			}
		}
		printf("\n");
		//traverses down the left side.
		traverse_tree(root->left);
	}
}
//Freeing the tree.
void cleanup_tree(TreeNode* root){
	if (root == NULL){
		return;
	}
	else{
		//calls the recursion
		cleanup_tree(root->left);
		cleanup_tree(root->right);
		//free's all pointers.
		free(root->word);
		free(root->references);
		free(root);
	}
}

//main functions 
int main(int argc, char *argv[]){
	char *buf = NULL;
	int numwords = 0;
	char ch;
	int i;
	int o = 0;
	size_t len = 0;
	TreeNode **root = (TreeNode**)malloc(sizeof(TreeNode));
	*root = NULL;
	//if the arg is more than just one the it will print out and error.
	if (argc > 1){
		printf("usage: concordance\n");
		exit(EXIT_FAILURE);
	}
	//gets line by line.
	while (getline(&buf, &len, stdin) > 0){
		char *aWord = calloc(len, sizeof(char));
		//separate line by character by character.
		for (i = 0; i < (int)len; i++){
			ch = buf[i];
			//as long as the character is not a " ", or any other non alphabetical 
			//ascii values then it will start adding to a string.
			if ( (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122) || (ch >= 48 && ch <= 57) || (ch == 45) || ch ==39 ){
				aWord[o] = ch;
				o++;
			}
			else{
				//checks that it is not an empty string.
				if (aWord[0] == '\0'){
					numwords++;
				}
				else {
					//inserts the word into the tree.
					insert_word(root, aWord);
					//resets the word back to empty.
					aWord = memset(aWord, 0, strlen(aWord));
					//also resets the index back to empty.
					o = 0;
				}
			}
		}
		//adds to the current line.
		currentLine++;
		//resets the buff each line it does.
		buf = memset(buf, 0, (int)len);
		//free's the memory that aWord is taking.
		free(aWord);
	}
	if (buf != NULL){
		free(buf);
	}
	//calls to functions.
	traverse_tree(*root);
	cleanup_tree(*root);
	free(root);

	return 0;
}
