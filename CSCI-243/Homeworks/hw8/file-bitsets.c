#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

const size_t SETSIZE = sizeof( uint64_t) << 3 ;
const size_t BUFSIZE = 256;


uint64_t file_set_encode( FILE * fp ) ;

uint64_t set_encode( char * st ){
	char hex[20];
	for (int i = 0; i < strlen(st); i++){
		char temp[5];
		sprintf(temp, "%X\n", st[i]);
		strcat(hex, temp);
	}
	(uint64_t)hex;
	return hex;
}

uint64_t set_intersect( uint64_t set1, uint64_t set2 ) ;

uint64_t set_union( uint64_t set1, uint64_t set2 ) ;

uint64_t set_complement( uint64_t set1 ) ;

uint64_t set_difference( uint64_t set1, uint64_t set2 ) ;

uint64_t set_symdifference( uint64_t set1, uint64_t set2 ) ;

size_t set_cardinality( uint64_t set ) ;

char * set_decode( uint64_t set ) ;

int main(int argc, char* argv[]){
	if (argc < 2){
		fprintf(stderr, "Usage: file-bitsets string1 string2\n");
		exit(1);
	}
	printf("string1:\t%s\tEnconding the string:\t%s\n", argv[1], argv[1]);
	printf("string2:\t%s\tEnconding the string:\t%s\n", argv[2], argv[2]);
	printf("set1: %lX", set_encode(argv[1]));
	return 0;
}
