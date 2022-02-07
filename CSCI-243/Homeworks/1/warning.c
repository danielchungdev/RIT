/// file: warning.c
/// description: faulty code example that needs fixing.
/// @author RIT CS Dept.

#include <stdio.h>

/// compute a value.
int compute(int a) {
    int b = a * 5;
    return b;
}

#define SIZE 0

/// main function should print 2018 and return 0 as the process return status.
int main(void) {
    int x = 1918;
    int y = compute(20);
    int z[10];

    printf("%d\n", y+x);
    printf("\n");

    if ( y > 100)
	    printf("%d", z[10]);
    return 0;
}

