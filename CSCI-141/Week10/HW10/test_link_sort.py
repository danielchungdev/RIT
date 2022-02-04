"""
Name: Daniel Chung
Homework 10: Link Sort
Date: November 4, 2017
Class: CSCI - 141
"""

import sys
"""
Imports the Link_sort code,
Imports rit_lib
Imports linked_code
"""
from link_sort import *

def make_node(a_list, a_idx=0):
    """
    Helper function to create a node,
    given the values that are inside
    the opened file.
    """
    if a_idx > len(a_list) - 1:
        return None
    else:
        return Node(a_list[a_idx], make_node(a_list, a_idx + 1))

def read_file(filename):
    """
    This functions takes a file,
    opens the files and read through
    its lines, puts them on a list,
    and then proceeds to call the
    helper function to create the
    nodes.
    """
    a_file = open(filename)
    opened = a_file.readlines()
    a_list = []
    for i in opened:
        i = int(i)
        a_list.append(i)
    a_file.close()
    return make_node(a_list)

def main():
    """
    Main Function that calls the whole code,
    it asks for a file, if the string is empty
    it will automatically end the program.
    """
    filename = input("Enter a data set:")
    if filename == "":
        sys.exit()
    else:
        nodes = read_file(filename)
        print("Unsorted Nodes:",nodes)
        nodes_sorted = link_sort(nodes)
        print("Sorted Nodes:", nodes_sorted)
        pretty_print(nodes)
        pretty_print(nodes_sorted)

if __name__ == '__main__':
    main()