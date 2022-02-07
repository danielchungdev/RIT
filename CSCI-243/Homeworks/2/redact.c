/*HOMEWORK 2 BY DANIEL CHUNG
pd: Yes I'm aware of how ugly and
the eyesore that this code is.	*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

int main(int argc, char*argv[]){
	/* Variables used along the 
	horrible coding this is xd 
	(pls send help xdd)  */
	char c;
	int i;
	int lol;
	int j = 0 ;
	int flag = 0;
	int b = 0;
	int temp;
	char text[2049];
	int len = strlen(text);
	char word[2049];
	char newText[2049];
	int count = 0;
	int index;
	int cflag = 0;
	int pflag = 0; 
	int newflag = 0;
	char tempword[2049];
	int ugh;

	/*This loops around the arguments and sees if 
	they contains -c or -p or both of them and turn
	both of the cflag and pflag on. */
	for (count = 0; count < argc; count++){
		if (strcmp(argv[count],"-c") == 0){
			cflag = 1;
		}
		if (strcmp(argv[count],"-p") == 0 ){
			pflag = 1;
		}
	}

	/*Checks if both flags are on
	and sets the word to the argument*/
	if ((cflag == 1) && (pflag == 1)){
		strcpy(word, argv[3]);
	} 
    
	/*Checks if none of the flags are on
	and sets the word to the argument*/
	if ((cflag == 0) && (pflag == 0) ){
		strcpy(word, argv[1]);
	}
	
	/*Chekcs if only the -c flag is on and 
	sets the word to the argument*/
	if ((cflag == 1) && (pflag == 0)){
		strcpy(word, argv[2]);
		for(ugh = 0; word[ugh] != '\0'; ugh++){
			tempword[ugh] = tolower(word[ugh]);
		}
		strcpy(word, tempword);
	}
     
	/*iterates through the stdio and writes it 
	into a string. it checks if both cflag and 
	pflag are on so it just puts it normally and
	not into lower case else the paranoia wouldnt
	work*/
	while ((c = getchar()) != EOF){
		if ( c == 10){
			break;
		}
		if (cflag == 1){
			if(pflag == 1){
				text[index] = c;
				index++;
			}else {
				text[index] = tolower(c);
				index++;
			}
		}
		else {
			text[index] = c;
			index++;
		}
	}
	text[index] = '\0';

	/*This is the regular comparing case where there is no -p or -c 
	on it just sees if the word is in the text*/
	if((cflag == 0) && (pflag == 0)){
		for (i = 0; text[i] != '\0'; i++){
			if (text[i] == word[b]){
				flag = 1;
				temp = i;
				while (text[temp] != ' ' && word[b] != '\0' ){
					if (text[temp] != word[b]){
						flag = 0;
						temp = 0;
						b= 0;	
					}
					b++;
					temp++;	
				}
			}
			if (flag !=  0){
				if (text[i] == ' '){
					flag = 0;
					b = 0;
				}
				else {
					newText[i] = '*';
				}
			}
			if (flag == 0){
				newText[i] = text[i];
			}
		}
 	}	

	/*This is the parania case. It checks if the first letter is a 
	capital letter and if it is. it transforms the capital letter word
	to **** */
	if((cflag == 0) && (pflag == 1)){
		for(i = 0; text[i] != '\0'; i++){
			if( isupper(text[i]) != 0){
				if ( isalpha(text[i -1]) != 0){
					flag = 0;
				}
				else{
					flag = 1;
				}
			}
			if (text[i] == ' '){
				flag = 0;
			}
			if (flag == 1){
				newText[i] = '*';
			}
			else {
				newText[i] = text[i];
			}
		}
	}

	/*This is the case sensitive mode doesnt matter if the word 
	is in caps or not this will sensor the desired word out */
	if((cflag == 1) && (pflag == 0)){
		for (i = 0; text[i] != '\0'; i++){
			if (text[i] == word[b]){
				flag = 1;
				temp = i;
				while (text[temp] != ' ' && word[b] != '\0' ){
					if (text[temp] != word[b]){
						flag = 0;
						temp = 0;
						b= 0;	
					}
					b++;
					temp++;	
				}
			}
			if (flag !=  0){
				if (text[i] == ' '){
					flag = 0;
					b = 0;
				}
				else {
					newText[i] = '*';
				}
			}
			if (flag == 0){
				newText[i] = text[i];
			}
		}
	}

	/*This is for both cases the -c and the -p. It runs the -p first
	and then it transform the rest of the string as tolower and then 
	runs the case sensitive case*/
	if( (cflag == 1) && (pflag == 1)){
		/*Running the paranoia mode*/
		for(lol = 0; text[lol] != '\0'; lol++){
			if( isupper(text[lol]) != 0){
				if ( isalpha(text[lol -1]) != 0){
					newflag = 0;
				}
				else{
					newflag = 1;
				}
			}
			if (text[lol] == ' '){
				newflag = 0;
			}
			if (newflag == 1){
				newText[lol] = '*';
			}
			else {
				newText[lol] = text[lol];
			}
		}
		strcpy(text, newText);
		/*Is taking the string that just passed into paranoia mode
		and parsing it into a lowercase string for the case sensitive
		mode.*/
		for (count = 0; text[count] != '\0'; count++ ){
			newText[count] = tolower(text[count]);
		}
		strcpy(text, newText);
		
		/*Running the case sensitive mode*/
		for (i = 0; text[i] != '\0'; i++){
			if (text[i] == word[b]){
				flag = 1;
				temp = i;
				while (text[temp] != ' ' && word[b] != '\0' ){
					if (text[temp] != word[b]){
						flag = 0;
						temp = 0;
						b= 0;	
					}
					b++;
					temp++;	
				}
			}
			if (flag !=  0){
				if (text[i] == ' '){
					flag = 0;
					b = 0;
				}
				else {
					newText[i] = '*';
				}
			}
			if (flag == 0){
				newText[i] = text[i];
			}
		}
	}

	/*Copies the newly made string from whatever
	case it went though into the text one.*/
	strcpy(text, newText);

	/*Prints out the final string*/
	printf("%s\n", text);
	return 0;
}
