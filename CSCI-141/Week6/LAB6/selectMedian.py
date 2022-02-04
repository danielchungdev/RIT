"""
Name: Daniel Chung
Lab 06: Store Locator
Date: 10/20/17
CSC1-141
"""
import math
import time
a = [0, 1, 20, 30]
"""
Code for Quick Sor
"""
def partition(pivot, a_list):
    (less, more) = ([], [])
    for e in a_list:
        if e < pivot:
            less.append(e)
        elif e > pivot:
            more.append(e)
    return (less, more)

def quick_select(a_list, k):
    if a_list == []:
        return []
    else:
        count = 0
        pivot = a_list[len(a_list) // 2]
        (less, more) = partition(pivot, a_list)
        for i in a_list:
            if i == pivot:
                count = count + 1
        m = len(less)
        if k >= m and k < m + count:
            return pivot
        elif m > k:
            return quick_select(less, k)
        else:
            return quick_select(more, k - m - count)
"""
Implements the quick select to find the median
"""
def median_quick_select(a_list):
    k = len(a_list) // 2
    if len(a_list) % 2 != 0:
        return k
    else:
        median = (quick_select(a_list, k) + quick_select(a_list, k - 1)) // 2
        return median
"""
Finds the distance
"""
def distance(a_list):
    best = median_quick_select(a_list)
    count = 0
    for item in a_list:
        count = count + math.fabs(best - item)
    return count
"""
Makes the file into a list.
"""
def fix_list(a_list):
    usable_list = a_list.split()
    usable_list = usable_list[1::2]
    intlist = [int(i) for i in usable_list]
    return intlist
"""
Calls the whole food.
"""
def main():
    opened = open(input("Enter Data File:"))
    list_str = opened.read()
    the_list = fix_list(list_str)
    start = time.clock()
    print("Optimum new store location:", median_quick_select(the_list))
    elapsed = (time.clock() - start)
    print("Sum of distances to new store:", distance(the_list))
    print("Elapsed time:", elapsed)
    opened.close()

main()