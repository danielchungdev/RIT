"""
Name: Daniel Chung
Lab 06: Store Locator
Date: 10/20/17
CSC1-141
"""
import math
import time
"""
Code for the Quick Sort algorithm.
"""
def partition(pivot, a_list):
    (less, same, more) = ([], [], [])
    for e in a_list:
        if e < pivot:
            less.append(e)
        elif e > pivot:
            more.append(e)
        else:
            same.append(e)
    return (less, same, more)

def list_sort(a_list):
    if a_list == []:
        return []
    else:
        pivot = a_list[0]
        (less, same, more) = partition(pivot, a_list)
        return list_sort(less) + same + list_sort(more)
"""
This finds the median from the list.
"""
def median_search(a_list):
    a_list = list_sort(a_list)

    if len(a_list) % 2 != 0:
        return a_list[len(a_list) // 2]

    else:
        x = len(a_list) // 2
        y = a_list[x - 1]
        z = a_list[x]
        return (y + z) / 2
"""
This calculates the total distance between the new location
and the other stores.
"""
def distances(a_list):
    best = median_search(a_list)
    count = 0
    for item in a_list:
        count = count + math.fabs(best - item)
    return count
"""
This makes the .txt file to be able to be made into a new 
list that is formed in int instead of strings.
"""
def fix_list(a_list):
    usable_list = a_list.split()
    usable_list = usable_list[1::2]
    intlist = [int(i) for i in usable_list]
    return intlist
"""
The main function.
"""
def main():
    opened = open(input("Enter Data File:"))
    list_str = opened.read()
    start = time.clock()
    print("Optimum new store location:", median_search(fix_list(list_str)))
    elapsed = (time.clock() - start)
    print("Sum of distances to new store:", distances(fix_list(list_str)))
    print("Elapsed time:", elapsed)
    opened.close()
main()