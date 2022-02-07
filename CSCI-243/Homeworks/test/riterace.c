//
// rite_race.c - uses mutex lock to fix the race condition accessing a global

#define _DEFAULT_SOURCE     // for usleep

#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

static const int COUNT = 40;    ///< count of cycles a thread will run

static int global = 0;          ///< shared global variable needs protection

/// intitalize mutex (mutual exclusion) synchronization

pthread_mutex_t mymutex = PTHREAD_MUTEX_INITIALIZER;

/// thread adds to the global in a loop.
/// this thread uses mutex locks to ensure mutually exclusive access.

void *thread_function(void *arg) {

    int i,j;
    for ( i = 0; i < COUNT; i++ )
    {
    	// lock this section -- only one thread may execute 
    	pthread_mutex_lock(&mymutex);

    	j = global;
    	j = j+1;
    	global = j;

    	// unlock this section letting other threads in
    	pthread_mutex_unlock(&mymutex);
    	printf(".");
    	fflush(stdout);
    	usleep(10);

    	//pthread_mutex_unlock(&mymutex);   // what will this do?
    }
    return NULL;

}

/// main creates and competes with a thread that
/// uses mutex locks to ensure mutually exclusive access.

int main(void) {

    pthread_t mythread;
    int i;

    if ( pthread_create( &mythread, NULL, thread_function, NULL) ) {

    	fprintf(stderr, "error creating thread\n");
    	exit(1);
    }

    for ( i = 0; i < COUNT; i++) {

    	/* lock */
    	pthread_mutex_lock(&mymutex);

    	global = global+1;

    	pthread_mutex_unlock(&mymutex);
    	printf("o");
    	fflush(stdout);
    	usleep(10);

    	// place unlock here instead, and run again
    	//pthread_mutex_unlock(&mymutex);
    }  

    if ( pthread_join ( mythread, NULL ) ) {

    	printf("error joining thread.");
    	return 1;
    }
  
    printf("\nshared global equals %d\n",global);
    return 0;
}
