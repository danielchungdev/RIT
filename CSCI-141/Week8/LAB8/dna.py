"""
Name: Daniel Chung
Lab 8: DNA Toolkit
Date: November 5, 2017
CSCI - 141
"""

from rit_lib import *
from linked_code import *

def convert_to_nodes(dna_string):
    """
    This functions takes a string,
    then it continues to make each
    letter of the string into a value
    of a new node, checks if the
    string input is "", and returns
    None to it.
    """
    if dna_string == "":
        return None
    else:
        return Node(dna_string[0], convert_to_nodes(dna_string[1:]))

def convert_to_string(dna_seq):
    """
    This functions takes a Node
    sequence, and adds the values
    of each Node to an originally
    empty string. At the end it
    returns the string.
    This function checks if the
    Node is empty it returns an
    empty string.
    """
    a_string = ""
    if dna_seq == None:
        return ""
    else:
        while dna_seq is not None:
            a_string += dna_seq.value
            dna_seq = dna_seq.rest
        return a_string

def is_match(dna_seq1, dna_seq2):
    """
    This function takes 2 nodes, it
    compares if they are identically
    the same. This function goes
    through every value individually
    making sure that they are the same.
    This function checks if they are
    the same lenght and if they are
    different.
    """
    if dna_seq1 == None and dna_seq2 == None:
        return True
    else:
        if dna_seq1 == None or dna_seq2 == None:
            return False
        elif dna_seq1 != dna_seq2:
            return False
        else:
            return is_match(dna_seq1.rest, dna_seq2.rest)

def is_pairing(dna_seq1, dna_seq2):
    """
    This function takes 2 Nodes
    sequences, and goes through
    both of them and compares if
    they are with their pair. If
    they're not, then it will
    return Fl
    """
    if dna_seq1 == None and dna_seq2 == None:
        return True
    elif dna_seq1 == None or dna_seq2 == None:
        return False
    else:
        if dna_seq1.value == "A" and dna_seq2.value == "T":
            return is_pairing(dna_seq1.rest, dna_seq2.rest)
        elif dna_seq1.value == "T" and dna_seq2.value == "A":
            return is_pairing(dna_seq1.rest, dna_seq2.rest)
        elif dna_seq1.value == "G" and dna_seq2.value == "C":
            return is_pairing(dna_seq1.rest, dna_seq2.rest)
        elif dna_seq1.value == "C" and dna_seq2.value == "G":
            return is_pairing(dna_seq1.rest, dna_seq2.rest)
        else:
            return False

def is_palindrome(dna_seq):
    """
    This function checks if the Linked
    list is the same if gone through
    the front and through the end.
    If it is, then returns True if it's
    not then returns False.
    """
    compare = dna_seq
    compare_to = reverseRec(dna_seq)
    if compare == compare_to:
        return True
    else:
        return False

def substitution(dna_seq1, idx, base):
    """
    This function takes a sequence, a index
    and a base which is what's getting
    substituted. Creates the copy of the
    original sequence. Returns the modified
    sequence.
    This functions checks if the sequence is
    empty and if the idx is greater than 0.
    """
    dna_seq1_copy = dna_seq1
    if dna_seq1 == None and idx > 0:
        raise IndexError("Index not valid")
    else:
        out = removeAt(idx, dna_seq1_copy)
        return insertAt(idx, base, out)

def insertion(dna_seq1, dna_seq2, idx):
    """
    This function takes 2 sequences and
    an index where the sequence will be
    inserted in.
    This function returns the combined
    sequence.
    """
    if idx == 0:
        return cat(dna_seq2, dna_seq1)
    elif dna_seq1 == None:
        raise IndexError("")
    else:
        return Node(dna_seq1.value, insertion(dna_seq1.rest, dna_seq2, idx - 1))

def deletion(dna_seq, idx, segment_size):
    """
    This function takes a sequence, and index
    where the deletion starts and how mane
    bases it deletes.
    This function raises errors if the idx is
    bigger than the sequence and if the sum
    of the idx plus the segment size is
    bigger than the sequence.
    This function uses cat to join the 2
    parts that were separated by the deleted
    part.
    """
    dna_seq_copy = dna_seq
    new_node = None
    if segment_size == 0:
        return dna_seq_copy
    if idx > lengthIter(dna_seq_copy):
        raise IndexError("Out of range")
    else:
        while idx != 0:
            new_node = Node(dna_seq_copy.value, new_node)
            dna_seq_copy = dna_seq_copy.rest
            idx -= 1
        new_node = reverseIter(new_node)
        rest_size = idx + segment_size
        if rest_size > lengthIter(dna_seq_copy):
            raise IndexError("Out of range")
        else:
            while rest_size != 0:
                dna_seq_copy = remove(dna_seq_copy.value,dna_seq_copy)
                rest_size -= 1
            deletion_node = cat(new_node, dna_seq_copy)
            return deletion_node

def duplication(dna_seq, idx, segment_Size):
    """
    This function takes a sequence an index
    and the amount of bases that have to be
    copied. This function gets the part of
    the sequence that has to be copied, and
    then uses the insertion function as a
    helper function to get the duplication
    inserted.
    """
    dna_seq_copy = dna_seq
    dna_seq_copy2 = dna_seq
    plugin_value = idx + segment_Size
    if segment_Size == 0:
        return dna_seq_copy
    if idx > lengthIter(dna_seq_copy) or idx + segment_Size > lengthIter(dna_seq_copy):
        raise IndexError("Out of range")
    else:
        while idx != 0:
            dna_seq_copy = remove(dna_seq_copy.value, dna_seq_copy)
            idx -= 1
        dna_string = None
        while segment_Size != 0:
            dna_string = Node(dna_seq_copy.value, dna_string)
            dna_seq_copy = dna_seq_copy.rest
            segment_Size -= 1
        segment = reverseIter(dna_string)
        return insertion(dna_seq_copy2, segment, plugin_value)