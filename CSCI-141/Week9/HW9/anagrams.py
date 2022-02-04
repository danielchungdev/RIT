"""
Name: Daniel Chung
Homework 09: Anagrams and the Word Jumble
Date: 10/28/17
CSCI-141
"""
"""
Function to order the user input input lexicographical
"""
def lexicographical(a_string):
    sort_list = []
    for char in a_string:
        sort_list.append(char)
        sort_list.sort()
    return ''.join(sort_list)
"""
Function that makes the whole file into a dictionary with
its respective keys and values.
"""
def task_1():
    stripped_list = []
    dictionary = {}
    opened = open("american-english.txt")
    read_dictionary = opened.readlines()
    for word in read_dictionary:
        stripped = word.strip()
        stripped_list.append(stripped),
    for word in stripped_list:
        key = lexicographical(word)
        value = []
        for words in stripped_list:
            values = lexicographical(words)
            if values == key:
                value.append(words)
        dictionary[key] = value
    return dictionary
"""
Takes a key in the dictionary and returns all its values.
"""
def task_2(dictionary, a_string):
    dict_inuse = dictionary
    input_key = lexicographical(a_string)
    if input_key in dict_inuse.keys():
        return print("Corresponding words:", dict_inuse[input_key])
    else:
        return print("No words can be formed from:", a_string)
"""
Returns the key with the biggest value.
"""
def task_3pt2(dictionary, lenght):
    dict_inuse = dictionary
    key_of_lenght = []
    current_list = [""]
    for key in dict_inuse.keys():
        if len(key) == int(lenght):
            key_of_lenght.append(key)
    for values in key_of_lenght:
        compare_list = []
        compare_list.append(dict_inuse[values])
        if len(compare_list[0]) >= len(current_list[0]):
            current_list = compare_list
    return current_list
"""
Determines how many words have the same key.
"""
def task_3_pt1(dictionary, lenght):
    dict_inuse = dictionary
    key_of_lenght = []
    for key in dict_inuse.keys():
        if len(key) == int(lenght):
            key_of_lenght.append(key)
    return len(key_of_lenght)
"""
Combines the part 1 with the part 2 into 
a single function.
"""
def task3_complete(dictionary, desired_lenght):
    if int(desired_lenght) > 0:
        print("Max anagrams for length", desired_lenght, ":", task_3_pt1(dictionary, desired_lenght))
        print(task_3pt2(dictionary, desired_lenght))
    else:
        print("Must be a positive integer")
"""
Determines how many word jumble can be used.
"""
def task_4(dictionary, lenght):
    dict_inuse = dictionary
    usable_keys = []
    count = 0
    for key in dict_inuse.keys():
        if len(key) == int(lenght):
            usable_keys.append(key)
    for key in usable_keys:
        if len(dict_inuse[key]) == 1:
            count = count + 1
    return count
"""
Prints the 4th function.
"""
def task4_complete(dictionary, length):
    print("Number of jumble usable words of length",length,":",(task_4(dictionary, length)))
"""
Main file that makes each task repeat itself until the user inputs
a blank input.
"""
def main():
    dictionary = task_1()
    user1 = input("Enter input string (hit enter key to go to task 3):")
    while user1 != "":
        task_2(dictionary, user1)
        user1 = input("Enter input string (hit enter key to go to task 3):")
    user2 = input("Enter word length (hit enter key to go to task 4):")
    while user2 != "":
        task3_complete(dictionary, user2)
        user2 = input("Enter word length (hit enter key to go to task 4):")
    user3 = input("Enter word length: (hit enter key to quit):")
    while user3 != "":
        task4_complete(dictionary, user3)
        user3 = input("Enter word length: (hit enter key to quit):")

main()
