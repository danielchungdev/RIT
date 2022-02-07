/*
CREATOR: DANIEL CHUNG
DATE: 10/22/18
CLASS: CS243

COMMENT: I've been having trouble with git for some reason
it won't let me do git add or any other git command therefore 
I am not able to link the revisions.txt because I can't retrieve
it.

*/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"HeapADT.h"
//Structures of the Heap.
 struct Heap_S{
 	size_t capacity;
	size_t length; 
	void** array;
	int (*compFun)(const void *lhs, const void * rhs);
	void(*dumpEntry)(const void * item, FILE * outfp);
 };
//Creates an empty heap.
Heap createHeap(size_t capacity,
 		int (*compFun)(const void * lhs, const void * rhs),
		void (*dumpEntry)(const void * item, FILE * outfp)){
		Heap aHeap = (Heap)malloc(sizeof(struct Heap_S));
		aHeap->capacity = capacity; 
		aHeap->length = 0;
		aHeap->array = (void**)calloc(capacity, sizeof(void*));
		aHeap->compFun = compFun;
		aHeap->dumpEntry = dumpEntry;
		return aHeap;
		}
//it free's the array and then it proceeds to 
//free the heap memory.
void destroyHeap(Heap aHeap){
	free(aHeap->array);
	free(aHeap);
}
//returns the size of the heap.
size_t sizeHeap(Heap aHeap){
	return aHeap->length;
}

int parent(int index){
	return (index - 1) / 2;
}

int leftchild(int index){
	return 1 + (index * 2);
}

int rightchild(int index){
	return 2 + (index * 2);
}
//Returns the top of the heap but it 
//doesn't remove it.
const void * topHeap(const Heap aHeap){
	return aHeap->array[0];
}
//shifts down the value whenever a new item is removed
//to the array .
void siftDown(Heap aHeap, int startIndex){
	int currentIndex = startIndex;  
	int swap = first_of_3(aHeap, currentIndex);
	while(swap != currentIndex){
		void* temp = aHeap->array[currentIndex];
		aHeap->array[currentIndex] = aHeap->array[swap];
		aHeap->array[swap] = temp; 
		currentIndex = swap; 
		swap = first_of_3(aHeap, currentIndex);
	}
}
//shifts up the value whenever a new item is added
//to the array.
void siftUp(Heap aHeap, int startIndex){
	int i = startIndex;
	while (i > 0 && !aHeap->compFun(aHeap->array[parent(i)], aHeap->array[i])){
		void* temp = aHeap->array[parent(i)];
		aHeap->array[parent(i)] = aHeap->array[i];
		aHeap->array[i] = temp;
		i = parent(i);
	}
}
//Compares the value of the leftchild 
//and the value of the right child.
//and then returns the value.
int first_of_3(Heap aHeap, int index){
	unsigned int lt = leftchild(index);
	unsigned int rt = rightchild(index);
	void**array = aHeap->array[index];
	if (aHeap->length > rt){
		void**left = aHeap->array[lt];
		void**right = aHeap->array[rt];
		if (aHeap->compFun(left, array) || aHeap->compFun(right, array)){
			if (aHeap->compFun(left, right)){
				return lt; 
			}
			else{
				return rt;
			}
		}
		else {
			return index;
		}
	}
	else if(aHeap->length > lt){
		void**left = aHeap->array[lt];
		if (aHeap->compFun(left, array)){
			return lt;
		}
		else {
			return index;
		}
	}
	else{
		return index;
	}

}
//Returns the top element of the heap,
//In other words it returns the first element of 
//the array. It takes a heap as a parameter.
void * removeTopHeap(Heap aHeap){
	void **top = aHeap->array[0];
	aHeap->length = aHeap->length - 1;
	aHeap->array[0] = aHeap->array[aHeap->length];
	aHeap->array[aHeap->length] = 0;
	//aHeap->length = aHeap->length - 1;
	siftDown(aHeap, 0);
	return top;
}
//Inserts the passited into the passed heap.
//it reallocates the array so it can add a new item to the array
//then adds the item to the array and updates the length.
void insertHeapItem(Heap aHeap, const void *item){
		if (aHeap->length == aHeap->capacity){
			void **tempArray = realloc(aHeap->array, (int)(aHeap->capacity * 2 * sizeof(void*)));
			aHeap->capacity = aHeap->capacity*2;
			aHeap->array = tempArray;
		}
		aHeap->array[aHeap->length] = (void*)item;
		siftUp(aHeap, aHeap->length);
		aHeap->length = aHeap->length+1;

}
//Prints out with the function dumpEntry.
void dumpHeap(Heap aHeap, FILE * outfp){
	for (int i = 0; i < (int)aHeap->length; i++){
		aHeap->dumpEntry(aHeap->array[i], outfp);
	}
}
