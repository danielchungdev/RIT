//
// rong_race.c shows a thread race condition for use of a global data variable.
// cs @ rit.edu

#define _DEFAULT_SOURCE

#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

static const int COUNT = 40;

static int global = 0;     ///< global data is vulnerable to a race condition

/// thread_function accesses shared global

void *thread_function(void *arg) {

    int i,j;
    for ( i = 0; i < COUNT; i++ ) {
        j = global;
        j = j + 1;
        printf(".");
        fflush(stdout);
        usleep(10);
        global = j;
    }
    return NULL;
}

/// main spawns the thread and also manipulates the shared global

int main(void) {

    pthread_t mythread;
    int i;

    if ( pthread_create( &mythread, NULL, thread_function, NULL) != 0 ) {
        printf("error creating thread.");
        return 1;
    }

    for ( i = 0; i < COUNT; i++) {

        global = global + 1;
        printf("o");
        fflush(stdout);
        usleep(10);
    }

    if ( pthread_join ( mythread, NULL ) != 0 ) {
        printf("error joining thread.");
        return (1);
    }

    printf("\nshared global equals %d\n",global);
    return 0;
}
