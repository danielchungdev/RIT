/*
** rpt - security log report generator
**
** Author:       A. N. Onymous
** Contributor:  YOUR_NAME_HERE
*/

#include <stdio.h>

#include "emp.h"
#include "log.h"

int main( int argc, char *argv[] ) {
    FILE *fp;

    if( argc < 2 ) {
        fprintf( stderr, "usage:  %s employee_file\n", argv[0] );
	return( 1 );
    }

    fp = fopen( argv[1], "r" );

    if( fp == NULL ) {
        perror( argv[1] );
	return( 1 );
    }

    if( load_employees(fp) > 0 ) {

        (void) load_logfile( stdin );
        generate_report();

    }

    fclose( fp );

    delete_all_employees();
    delete_all_logentries();

    return( 0 );
}

