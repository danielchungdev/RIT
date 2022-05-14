""" 
Polynomials
In this homework, polynomials are represented using linked lists. Each
term is stored in a node, and the nodes are ordered in descending
order according to their degree. Functions are implemented:
To create a polynomial linked list from file data
To insert a term node to a polynomial linked list
To pretty-print a polynomial
To add two polynomials

Author: RITCS
Author: << YOUR NAME HERE >>
"""
from typing import Union
from dataclasses import dataclass

@dataclass( frozen=False )
class TermNode:
    """
    A mutable link node containing a coefficient, exponent, and
    reference to next node
    """
    coefficient: int
    exponent: int
    next: Union[ "TermNode", None ]

@dataclass( frozen=False )
class LinkedList:
    """
    For mutable linked lists, we 'encapsulate' the list nodes in a wrapper
    class. This will allow functions that work with mutable lists to not
    worry about whether the list is empty or not. An empty list is still an
    instance of this LinkedList class; it's just that its head is None.
    The size of the list is stored here, too, as an example of the tradeoff
    between computing something every time you need it and using extra
    memory to store the value so that it does not have to be recomputed.
    """
    head: Union["TermNode", None] = None
    size: int = 0

def make_empty_list():
    """
    Make an empty list
    :return: a LinkedList object with next = None
    """
    return LinkedList(None, 0)

def insert_poly(lst, coefficient, exponent):
    """
    Creates a term node with parameter data. Inserts the node in the linked
    list according to its degree.
    :param lst: linked list structure
    :param coefficient: int
    :param exponent: int
    """
    lst.head = TermNode(coefficient, exponent, lst.head)
    
def print_poly(lst):
    """
    Pretty-prints the polynomial represented in a linked list
    :param lst: linked list structure
    :return: None
    """
    pretty_string = "";
    curr = lst.head
    count = 0
    while (curr != None):
        if (count == 0): 
            pretty_string += curr.coefficient + " * x ^ " + curr.exponent + " "
        else: 
            if (int(curr.coefficient) < 0):
                pretty_string += "- " + curr.coefficient[1:] + " * x ^ " + curr.exponent + " "
            else: 
                pretty_string += "+ " + curr.coefficient + " * x ^ " + curr.exponent + " "
        count += 1
        curr = curr.next
    print(pretty_string)

        
def add_poly(poly1, poly2):
    """
    Creates a linked list that contains the sum of two polynomials
    :param poly1: linked list structure
    :param poly2: linked list structure
    :return: linked list structure
    """
    newList = make_empty_list()
    pol1Head = poly1.head
    pol2Head = poly2.head
    values = {}
    while(pol1Head != None or pol2Head != None):
        if (pol1Head.exponent > pol2Head.exponent):
            values[pol1Head.exponent] = pol1Head.coefficient
            pol1Head = pol1Head.next
        if (pol1Head.exponent < pol2Head.exponent):
            values[pol2Head.exponent] = pol2Head.coefficient
            pol2Head = pol2Head.next
        if (pol1Head.exponent == pol2Head.exponent): 
            total_sum = int(pol1Head.coefficient) + int(pol2Head.coefficient)
            if (total_sum > 0): 
                values[pol1Head.exponent] = str(total_sum)
            pol1Head = pol1Head.next
            pol2Head = pol2Head.next
    ordered_keys = sorted(values)
    for key in ordered_keys:
        insert_poly(newList, values[key], key)
    return newList

def poly_load(filename):
    """
    Creates a linked list to store data from a file containing
    a coefficient and exponent on each line corresponding
    to a term. insert_poly() is called to create a term node
    and to insert it on the list.
    :param file: string
    :return: linked list structure
    """
    file = open(filename)
    file_contents = {}
    linkedlist = make_empty_list();
    for line in file:
        values = line.split()
        file_contents[values[1]] = values[0]
    ordered_keys = sorted(file_contents)
    for key in ordered_keys:
        if int(file_contents[key]) != 0:
            insert_poly(linkedlist, file_contents[key], key)
    return linkedlist

def main():
    """
    Main function implements four tasks.
    1)  Prompts for two file names
    2)  Creates two polynomial linked lists from files
    3)  Pretty-prints the polynomials
    4)  Adds the polynomials 
    5)  Pretty-prints the result polynomial
    :return: None
    """


    # file1 = input('Enter name of first file: ')
    # file2 = input('Enter name of second file: ')

    poly1 = poly_load('poly1.txt')
    poly2 = poly_load('poly2.txt')

    print("Polynomial in file1:")
    print_poly(poly1)
    print("Polynomial in file2:")
    print_poly(poly2)

    poly3 = add_poly(poly1,poly2)

    print("Sum polynomial:")
    print_poly(poly3)


if __name__ == "__main__":
    main()
