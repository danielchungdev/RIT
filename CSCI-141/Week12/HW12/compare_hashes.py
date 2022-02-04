"""
Name: Daniel Chung
Homework 12: Compare Hashing
Class:CSCI - 141
Date: 12/9/17
"""

import time
from rit_lib import *
from test_hash_func import *
from hashtable import *
import math

def keys_into_list(user_input_file, capacity):
    key_list = []
    count = 0
    for i in user_input_file:
        if count >= int(capacity):
            break
            return key_list
        else:
            i = i.strip()
            key_list.append(i)
            count += 1
    return key_list

def put_count(hTable, key, value):

    index = hTable.hash_func(key) % hTable.capacity
    startIndex = index
    count = 0
    while hTable.table[index] != None and hTable.table[index].key != key:
        index = (index + 1) % hTable.capacity
        count += 1
        if index == startIndex:
            raise Exception("Hash table is full.")
    if hTable.table[index] == None:
        hTable.table[index] = Entry(key, value)
        hTable.size += 1
    else:
        hTable.table[index].value = value
    return count

def always_hashes_to_zero(key):
    """
    This implement the terrible
    hashing that will always
    has with a value of 0
    :param key:
    :return:
    """
    return 0

def add_ordinal_values(key):
    """
    This implements the hashing
    that takes the ASCII value
    of every single letter and
    adds it up.
    :param key:
    :return:
    """
    value = 0
    for i in key:
        i = ord(i)
        value += i
    return value

def exponential_ordinal_values(key):
    """
    Thi function takes the hashing
    that takes its own ASCII value
    and raises it to itself.
    :param key:
    :return:
    """
    value = 0
    for i in key:
        i = ord(i)
        value += i ** i
    return value

def good_hashing_function(key):
    """
    This function implements the
    good_hashing_functions.
    :param key:
    :return:
    """
    value = 0
    add = 1
    n = len(key)
    for i in key:
        i = ord(i)
        value += (i) * 31 ** (n - add)
        add += 1
    return value

def my_hash_func(key):
    """
    This function implements my own
    hashing function. It basically
    takes the ASCII value and then
    raises it to the ASCII value of
    the next one. If it's at the end
    of the key, then the ASCII value
    will be raised by the ASCII value
    of the first one.
    :param key:
    :return:
    """
    value = 0
    for i in range(len(key)):
        if i + 1 >= len(key):
            value += ord(key[i]) ** ord(key[0])
            return value
        else:
            value += ord(key[i]) ** ord(key[i + 1])

def separate_helper(key_list, hashfunc):
    """
    This function separates
    :param key_list:
    :param hashfunc:
    :return:
    """
    print("Evaluating", hashfunc.__name__)
    test_hash_func(hashfunc)
    new_hashtable = createHashTable(hashfunc, len(key_list))
    count = 0
    start = time.clock()
    for i in key_list:
        value = hashfunc(i)
        count += put_count(new_hashtable, i, value)
    end = time.clock()
    total_time = end - start
    average_length = count/len(key_list)
    print("   Inserted", len(key_list), "items into the hashtable in", total_time, "seconds")
    print("   Average lenght of linear search:", average_length)

def separate_task2(key_list):
    """
    This function does task2 for the
    always return 0 hashing function.
    :param key_list:
    :return:
    """
    return separate_helper(key_list, always_hashes_to_zero)

def separate_task3(key_list):
    """
    This function does task2 for the
    ordinal_values.
    :param key_list:
    :return:
    """
    return separate_helper(key_list, add_ordinal_values)

def separate_task4(key_list):
    """
    This function does task2 for the
    exponential_ordinal_values.
    :param key_list:
    :return:
    """
    return separate_helper(key_list, exponential_ordinal_values)

def separate_task5(key_list):
    """
    This function does task2 for the
    good_hashing..
    :param key_list:
    :return:
    """
    return separate_helper(key_list, good_hashing_function)

def separate_task6(key_list):
    """
    This function does task2 for
    my_hashing_function.
    :param key_list:
    :return:
    """
    return separate_helper(key_list, my_hash_func)

def separate(key_list):
    """
    This function takes in all of
    the separates_helper and puts
    them together.
    :param key_list:
    :return:
    """
    separate_task2(key_list)
    separate_task3(key_list)
    separate_task4(key_list)
    separate_task5(key_list)
    separate_task6(key_list)

def main():
    a_file = open(input("Enter a file name:"))
    a_capacity = input("Select a capacity:")
    the_capacity = a_capacity
    key_list = keys_into_list(a_file, the_capacity)
    separate(key_list)

if __name__ == '__main__':
    main()