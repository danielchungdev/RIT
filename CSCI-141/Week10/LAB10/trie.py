"""
Name: Daniel Chung
Final Lab!!
CSCI - 141
Date: 12/3/2017
"""

from rit_lib import *

Trie = struct_type("Trie",
                   ((NoneType, "Trie"), "left"),
                   (object, "value"),
                   ((NoneType, "Trie"), "right"))

def insert_helper(trie, st, acc):
    """
    This is the insertion helper function,
    takes trie structures and combines
    them,
    :param trie:
    :param st:
    :param acc:
    :return:
    """
    if trie is None:
        return Trie(None, st, None)
    elif trie.value == st:
        return False
    elif trie.left == None and trie.right == None:
        old_value = trie.value
        trie.value = None
        if old_value[acc] == "1":
            trie.right = Trie(None, old_value, None)
            if st[acc] == "0":
                trie.left = insert_helper(trie.left,st,acc + 1)
            else:
                trie.right = insert_helper(trie.right, st, acc + 1)
        else:
            trie.left = Trie(None, old_value, None)
            if st[acc] == "0":
                trie.left = insert_helper(trie.left, st, acc + 1)
            else:
                trie.right = insert_helper(trie.right, st, acc + 1)
    else:
        old_value = trie.value
        trie.value = None
        if old_value[acc] == "1":
            trie.right = Trie(None, old_value, None)
            if st[acc] == "0":
                trie.left = insert_helper(trie.left, st, acc + 1)
            else:
                trie.right = insert_helper(trie.right, st, acc + 1)
        else:
            trie.left = Trie(None, old_value, None)
            if st[acc] == "0":
                trie.left = insert_helper(trie.left, st, acc + 1)
            else:
                trie.right = insert_helper(trie.right, st, acc + 1)

def insert(trie, st):
    """
    This is the Insert function.
    :param trie:
    :param st:
    :return:
    """
    if insert_helper(trie, st, 0) == False:
        return False
    else:
        return True

def trie_to_list_helper(trie, a_list):
    """
    This is a helper function for the
    trie to list function, goes through
    the trie struct via infix.
    :param trie:
    :param a_list:
    :return:
    """
    if trie is not None:
        trie_to_list_helper(trie.left, a_list)
        if trie.value == "":
            pass
        else:
            a_list.append(trie.value)
        trie_to_list_helper(trie.right, a_list)
    return a_list

def trie_to_list(trie):
    """
    This is the trie to list function,
    takes a trie and makes it into a list.
    :param trie:
    :return:
    """
    a_list = trie_to_list_helper(trie, [])
    return a_list

def largest_helper(trie, a_list):
    """
    This is a helper function that
    takes only the biggest values.
    :param trie:
    :param a_list:
    :return:
    """
    if trie is not None:
        largest_helper(trie.right, a_list)
        if trie.value == "":
            pass
        else:
            a_list.append(trie.value)

    return a_list

def largest(trie):
    """
    Finds the biggest values.
    :param trie:
    :return:
    """
    right_list = largest_helper(trie, [])
    return right_list[-1]

def smallest_helper(trie, b_list):
    """
    This is the smallest helper function,
    this function takes only the smallest values.
    :param trie:
    :param b_list:
    :return:
    """
    if trie is not None:
        smallest_helper(trie.left, b_list)
        if trie.value == "":
            pass
        else:
            b_list.append(trie.value)
        smallest_helper(trie.right, b_list)
    return b_list

def smallest(trie):
    """
    This function takes only the smallest values.
    :param trie:
    :return:
    """
    smallest_list = []
    a_list = smallest_helper(trie, [])
    for i in a_list:
        smallest_list.append(i)
    if smallest_list == []:
        return None
    else:
        return smallest_list[0]

def search_helper(trie, st, acc):
    """
    This is the search helper function,
    takes looks for the longest value.
    (incomplete).
    :param trie:
    :param st:
    :param acc:
    :return:
    """
    if trie.left == None and trie.right == None:
        return trie.value
    else:
        if st[acc] == "1":
            if trie.right == None:
                print(trie.value, "PART1, 1")
                search_helper(trie.left, st, acc + 1)
            else:
                print(trie.value, "PART 1, NOT 1")
                search_helper(trie.right, st, acc + 1)
        else:
            if trie.left == None:

                search_helper(trie.right, st, acc + 1)
            else:
                search_helper(trie.left, st, acc + 1)

def search(trie, st):
    """
    Search Functions. (Incomplete)
    :param trie:
    :param st:
    :return:
    """
    return search_helper(trie, st, 0)

def size(trie):
    """
    This function gets
    the total size of the trie.
    :param trie:
    :return:
    """
    if trie == None:
        return 0
    else:
        a_list = trie_to_list(trie)
        return len(a_list)

def height_helper(trie):
    """
    This is a helper function to,
    calculate the height of the
    trie.
    :param trie:
    :return:
    """
    if trie == None:
        return -1
    else:
        right_child = height_helper(trie.right)
        left_child = height_helper(trie.left)
        return (max(right_child, left_child) + 1)

def height(trie):
    """
    This function calls the helper
    function to get the height
    of the trie.
    :param trie:
    :return:
    """
    biggest_one = height_helper(trie)
    if biggest_one == -1:
        return 0
    else:
        return biggest_one

def main():
    trie = Trie(None, "10010", None)
    insert(trie, "00101")
    print(trie)
    print(search(trie, "00011"))
    # trie = Trie(None, "01", None)
    # print(trie)
    # insert(trie, "10")
    # print(trie)
    # insert(trie, "001")
    # print(trie)

if __name__ == '__main__':
    main()