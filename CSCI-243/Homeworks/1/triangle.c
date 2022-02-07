/// 
/// File: triangle.c 
/// 
/// A program to print out multiple "pyramid" triangles of the form:
/// <pre>
///   *
///  ***
/// *****
/// </pre>
/// 
/// @author Daniel Chung
/// 
// // // // // // // // // // // // // // // // // // // // // // // // 

// TODO_ADD_#INCLUDES_ HERE

#include <stdio.h>
#include <stdlib.h>

/**
 *  Function: drawTriangle
 *
 *  Description: Draw a 'pyramid' triangle whose base width is 'size'.
 *  Note: If size is even, the function makes a 'size + 1' pyramid triangle. 
 * 
 *  @param size  the width of the base of the triangle to draw
 */
// TODO_WRITE_DRAWTRIANGLE_FUNCTION HERE

void drawTriangle(int size){
	/* checks to see if it 
	 * the desired size is 
	 * even or odd. */
	if (size % 2 == 0){
		size = size + 1; 
	}
	//Variables
	int numbers_of_stars = size; 
	int printed_star = 1; 
	int numbers_of_rows = ((numbers_of_stars - 1) / 2) + 1;
	int spaces = numbers_of_rows;
	int stars, row, space;
	// loop for the rows that contain stars
	for (row = 1; row <= numbers_of_rows; row++){
		// loop for the spaces before a star
		for(space = 1; space < spaces; space++){;
			printf(" ");
		}
		// loop for the amount of stars in a line.
		for (stars = 1; stars <= printed_star; stars++){
			printf("*");
		}

		printed_star = printed_star + 2;
		spaces = spaces - 1;
		printf("\n");
	}
	printf("\n");
}

/**
 *  Function: main 
 *
 *  Description: draws pyramid triangles of size 1, 5, and '6'.
 *
 *  @returns errorCode  error Code; EXIT_SUCCESS if no error
 */
// TODO_WRITE_MAIN_FUNCTION HERE
int main(void){
	printf("\n");
	drawTriangle(1);
	drawTriangle(5);
	drawTriangle(6);
	return (EXIT_SUCCESS);

}

