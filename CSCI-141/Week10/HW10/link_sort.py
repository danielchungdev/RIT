"""
Name: Daniel Chung
Homework 10: Link Sort
Date: November 4, 2017
Class: CSCI - 141
"""

from rit_lib import *
from linked_code import *

def find_current_value(lns):
    """
    Helper functions, takes the
    current value of the node.
    This then gets passed on to
    find the min value as a value
    to compare
    """
    return lns.value

def find_min_value(lns, current):
    """
    Finds the smallest value in
    the nodes.
    """
    if lns == None:
        return current
    elif lns.value < current:
        current = lns.value
    return find_min_value(lns.rest, current)

def link_sort(lns):
    """
    This code is the one that takes
    a unsorted linked node and sorts
    it.
    """
    linked = lns
    new_node = None
    for i in range(lengthIter(linked)):
        min_value_sort = find_min_value(linked, find_current_value(linked))
        linked = remove(min_value_sort, linked)
        new_node = insertAt(i,min_value_sort, new_node)
    return new_node

def list_like(lns,  alst=""):
    """
    helper function for pretty_print
    that takes a linked node and place
    its  values in a string.
    """
    if lns == None:
        return alst
    else:
        alst += str(lns.value) + str(", ")
        return list_like(lns.rest, alst)

def brackets(a_str):
    """
    Helper function for pretty prints
    that makes sure the answer is
    between brackets.
    NOTE: IT SAID IN THE DISCUSSIONS
    THAT IT WAS NOT SUPPOSED TO BE A LIST.
    """
    return print("[", a_str, "]")

def pretty_print(lns):
    """
    Calls both helper functions
    to create the print out the
    values like "a python list".
    """
    if lns == None:
        return None
    else:
        return brackets(list_like(lns))


