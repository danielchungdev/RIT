"""
Name: Daniel Chung
Homework8: Comparing Sorts
Date: 10/21/17
"""

import random
import time

"""
Insertion Sort
"""
def insertionSort( lst ):
   for mark in range( len( lst )-1 ):
       insert( lst, mark )


def insert( lst, mark ):
   for index in range( mark, -1, -1 ):
      if lst[index] > lst[index+1]:
         swap( lst, index, index+1 )
      else:
         return


def swap( lst, i, j ):
   ( lst[i], lst[j] ) = ( lst[j], lst[i] )

"""
Merge Sort
"""

def mergeSort(L):
    if L == []:
        return []
    elif len(L) == 1:
        return L
    else:
        (half1, half2) = split(L)
        return merge(mergeSort(half1), mergeSort(half2))

def split(L):
    evens = []
    odds = []
    isEvenIndex = True
    for e in L:
        if isEvenIndex:
            evens.append(e)
        else:
            odds.append(e)
        isEvenIndex = not isEvenIndex
    return (evens, odds)

def merge(sorted1, sorted2):
    result = []
    index1 = 0
    index2 = 0
    while index1 < len(sorted1) and index2 < len(sorted2):
        if sorted1[index1] <= sorted2[index2]:
            result.append(sorted1[index1])
            index1 = index1 + 1
        else:
            result.append(sorted2[index2])
            index2 = index2 + 1
    if index1 < len(sorted1):
        result.extend(sorted1[index1:])
    elif index2 < len(sorted2):
        result.extend(sorted2[index2:])
    return result

"""
Quick Sort
"""

def quickSort( L ):
    if L == []:
        return []
    else:
        pivot = L[0]
        ( less, same, more ) = partition( pivot, L )
        return quickSort( less ) + same + quickSort( more )

def partition( pivot, L ):
    ( less, same, more ) = ( [], [], [] )
    for e in L:
        if e < pivot:
            less.append( e )
        elif e > pivot:
            more.append( e )
        else:
            same.append( e )
    return ( less, same, more )

"""
Test Functions
Insertion Sort
"""
def test_insertion_sort(alist):
    sorted_list = sorted(alist)
    insertionSort(alist)
    algorithm_sorted = alist
    if sorted_list == algorithm_sorted:
        return print("True")
    else:
        return print("False")
"""
Merge Sort
"""
def test_merge_sort(alist):
    sorted_list = sorted(alist)
    algorithm_sorted = mergeSort(alist)
    if sorted_list == algorithm_sorted:
        return print("True")
    else:
        return print("False")
"""
Quick Sort
"""
def test_quick_sort(alist):
    sorted_list = sorted(alist)
    algorithm_sorted = quickSort(alist)
    if sorted_list == algorithm_sorted:
        return print("True")
    else:
        return print("False")
"""
Call the  whole function
"""
def main():
    timeout = int(input("What is the timeout (in seconds)? "))
    listlenght = int(input("Select how many numbers"))
    minvalue = int(input("What is the min possible value of an item in the list:"))
    maxvalue = int(input("What is the max possible value of an item in the list"))

    size = listlenght
    """
    Creates the copy of the list
    """
    random_list = random.sample(range(minvalue, maxvalue), listlenght)
    copylist1 = random_list[:]
    copylist2 = random_list[:]
    copylist3 = random_list[:]

    total1 = 0
    total2 = 0
    total3 = 0
    """
    Makes the loop to run the program until the conditions are met.
    """
    while size > 0:
        """
        Conditions that have to be met to break the code
        """
        if total1 > timeout and total2 > timeout or total3 > timeout and total2 > timeout or total1 > 1 and total3> 1:
            break
        print("****************************")
        print("Size:", size)
        print("****************************")
        """
        If its bigger than the timeout, it won't run
        """
        if total1 < timeout:
            start = time.time()
            insertionSort(copylist1)
            end = time.time()
            total1 = (end - start)
            print("Insertion Sort:", total1, "seconds")
        """
            If its bigger than the timeout, it won't run
        """
        if total2 < timeout:
            start = time.time()
            mergeSort(copylist2)
            end = time.time()
            total2 = (end - start)
            print("Merge Sort:", total2, "seconds")
        """
        If its bigger than the timeout, it won't run
        """
        if total3 < timeout:
            start = time.time()
            quickSort(copylist3)
            end = time.time()
            total3 = (end - start)
            print("Quick Sort:", total3, "seconds")
        """
        Increases the number by ten
        """
        size = size * 10
        """
        Update the list other wise it will return the total as none
        """
        random_list = random.sample(range(minvalue, maxvalue), size)
        copylist1 = random_list[:]
        copylist2 = random_list[:]
        copylist3 = random_list[:]

main()