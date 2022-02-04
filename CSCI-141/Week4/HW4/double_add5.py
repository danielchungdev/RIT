"""
Daniel Chung
"""
import math
"""
This is the recursive function that prints every result of N * 2 + 5 depending on how many "counts" or times 
you want.
"""
def print_sequence_rec(start, count):
    if count < 0:
        return print(" ")
    if count < 0:
        return print(" ")
    print(start, end=' ')
    if count <= 0:
        return start
    else:
        n = (start * 2 + 5)
        start = print_sequence_rec(n, count - 1)
        return start
"""
This is the iterative function that prints every result of N * 2 + 5 depending on how many "counts" or times 
you want.
"""
def print_sequence_inter(start, count):
    print(start, end=' ')
    while count >= 1:
        n = start * 2 + 5
        count = count - 1
        print_sequence_inter(n, count)
        break
"""
This function finds the initial value that has to be inserted in N * 2 + 5 to get
the "goal" number in the "count" steps
"""
def find_start_forward(goal, count):
    c = count
    y = 0
    x = y
    while c >= 0:
        if x >= goal:
            print(y)
            break
        elif c <= 0:
            y = y + 1
            c = count
            x = y
        else:
            c = c - 1
            x = x * 2 + 5
"""
Recursive function to search backwards to return the number N that has to go in the formula
n * 2 + 5 in the amount of "count" steps to get the nbr
"""
def find_back_limit_rec(nbr, count):
        if count < 1:
            print(math.ceil(nbr), end=' ')
        else:
            n = ((nbr - 5) / 2)           #FIX
            nbr = find_back_limit_rec(n, count - 1)

"""
Iterative function to search backwards to return the number N that has to go in the formula
n * 2 + 5 in the amount of "count" steps to get the nbr
"""
def find_back_limit_iter(nbr, count):
    while count > 0:
        nbr = ((nbr - 5) / 2)
        count = count - 1
    else:
        print(math.ceil(nbr))

"""
I added the print("") so it would print in a nicer way easier to grade
This function calls the whole code
"""
def main():
    print_sequence_rec(0, -1)
    print_sequence_rec(0, 0)
    print("")
    print_sequence_rec(1, 2)
    print("")
    print_sequence_inter(2, 5)
    print("")
    print_sequence_rec(1, 0)
    print("")
    print_sequence_inter(1, 1)
    print("")
    find_start_forward(7, 3)
    find_start_forward(8, 3)
    find_start_forward(9, 2)
    find_start_forward(100, 1)
    find_start_forward(100, 3)
    print_sequence_inter(9, 3)
    print("")
    find_start_forward(1000, 3)
    print_sequence_inter(121, 3)
    print("")
    find_back_limit_rec(1000, 3)
    print("")
    find_back_limit_iter(1003, 3)
    print_sequence_inter(3, 19)
    print("")
    find_back_limit_iter(4194299, 3)
"""
Calls the whole function
"""
main()