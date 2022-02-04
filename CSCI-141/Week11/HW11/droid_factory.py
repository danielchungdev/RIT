"""
Name: Daniel Chung
Date: November 11, 2017
Homework 11: Droid Factory
CSCI - 141
"""

from rit_lib import *
from myQueue import *
from myStack import *
from linked_code import *

Droid = struct_type("Droid",
                    (int,"serial_num"),
                    (bool, "head"),
                    (bool, "body"),
                    (bool, "arms"),
                    (bool, "legs"))

Worker = struct_type("Worker",
                     (str, "name"),
                     (Queue, "belt"),
                     (Stack, "container"))

def empty_droid(serial_num):
    """
    This function is to declare
    what a empty droid should
    look like.
    :param serial_num:
    :return an empty droid:
    """
    return Droid(serial_num, False, False, False, False)

def complete_droid(serial_num):
    """
    This function is to declare
    what a completed droid should
    look like.
    :param serial_num:
    :return a complete droid:
    """
    return Droid(serial_num, True, True, True, True)

def worker_belt(a_file):
    """
    This is a helper function to
    create the worker's belt.
    :param a_file:
    :return a worker's belt with the materials:
    """
    opened = open(a_file)
    empty_belt = mkEmptyQueue()

    for i in opened.readlines():
        i = i.strip()
        enqueue(empty_belt, i)

    return empty_belt

def make_droids(worker, serial_num):
    """
    This function creates one droid, it makes
    sure that the serial number has explicitly
    5 digits. When the droids are finished they
    are pushed into a stack/container.
    :param worker:
    :param serial_num:
    :return this function returns the container/stack in which the droids are placed:
    """
    if len(str(serial_num)) != 5:
        return print("Serial Number should be 5 digits")
    else:
        new_droid = empty_droid(serial_num)
        worker_belt = worker.belt

        print(worker.name, "is building a new dorid with serial number", serial_num)

        while new_droid != complete_droid(serial_num):
            droid_part = front(worker_belt)

            if droid_part == "head" and new_droid.head == False:

                print("   attaching head. . .")

                new_droid.head = True
                dequeue(worker_belt)

            elif droid_part == "body" and new_droid.body == False:

                print("   attaching body. . .")

                new_droid.body = True
                dequeue(worker_belt)

            elif droid_part == "arms" and new_droid.arms == False:

                print("   attaching arms. . .")

                new_droid.arms = True
                dequeue(worker_belt)

            elif droid_part == "legs" and new_droid.legs == False:

                print("   attaching legs. . .")

                new_droid.legs = True
                dequeue(worker_belt)

            else:

                print("   placing unneeded part back on belt:", droid_part)

                dequeue(worker_belt)
                enqueue(worker_belt, droid_part)

        print("   packing droid into shipping container.")
        push(worker.container, new_droid)

        return worker.container

def droid_factory(worker):
    """
    This function creates as many droids
    as it can possible do with the parts
    given. When all the parts are used and
    all the robots have been placed into the
    container, this function then starts to
    unload them.
    :param worker:
    :return this function returns the empty container:
    """
    serial_number = 10000
    workerbelts = worker.belt

    while workerbelts.size > 0:
        make_droids(worker, serial_number)
        serial_number = serial_number + 1

    if workerbelts.size == 0:
        print("a shipment of droids is being unloaded. . .")
        while worker.container.size != 0:
            droids = top(worker.container)
            pop(worker.container)
            print("  ", droids)

    return worker.container

def task_tests():
    """
    This functions is the test function for each
    task of the homework.
    :return:
    """
    print("------------------------------------------------------------------------")
    print("Task 1 Test")
    """
    The first 2 tests should work, because my
    Droid structure only accepts bool. 
    The other tests should fail and return 
    false because python only recognizes 0 and
    True as True, everything else is false.
    """
    droid = empty_droid(10000)
    print(droid.head == True)
    print(droid.head == False)
    print(droid.head == "head")
    print(droid.head == 10)
    print(droid.head == [])
    print(droid.head == {})

    """
    Tests for the empty_droid and the complete
    droid function. If the droid is empty, it
    should print false and if it's complete 
    then it will print True in all their 
    attributes.
    """
    empty = empty_droid(10000)
    complete = complete_droid(1000)
    print(empty)
    print(complete)
    print("------------------------------------------------------------------------")
    print("Task 2 Test")
    """
    This function should work with a_worker,
    but b_worker, c_worker and d_worker will 
    fail because there's a stack, where there 
    should be a Queue and  there is a Queue 
    where there should be a stack.
    """
    a_worker = Worker("Charlie", mkEmptyQueue(), mkEmptyStack())
    # b_worker = Worker("Charlie", mkEmptyStack(), mkEmptyQueue())
    # c_worker = Worker("Charlie", mkEmptyQueue(), mkEmptyQueue())
    # d_worker = Worker("Charlie", mkEmptyStack(), mkEmptyStack())
    print(a_worker)
    enqueue(a_worker.belt, "this")
    push(a_worker.container, "droid")
    print(a_worker)
    # print(b_worker)
    # print(c_worker)
    # print(d_worker)
    print("------------------------------------------------------------------------")
    print("Task 3 Test")
    """
    This test makes sure that the droid only
    takes a 5 digit serial number as specified
    in the homework, each droid should have a
    unique 5 digit serial number.
    """
    worker_name = "Worker name"
    new_worker = Worker(worker_name, mkEmptyQueue(), mkEmptyStack())
    new_worker.belt = worker_belt("droid_parts.txt")
    make_droids(new_worker, 10000)
    make_droids(new_worker, 10)
    make_droids(new_worker, 100)
    make_droids(new_worker, 1000)
    make_droids(new_worker, 100000)
    print("------------------------------------------------------------------------")
    print("Task 4 Test")
    """
    Tests for the droid factory that 
    creates as much droids as it can
    possible create.
    """
    worker_name = "Worker name"
    new_worker = Worker(worker_name, mkEmptyQueue(), mkEmptyStack())
    new_worker.belt = worker_belt("droid_parts.txt")
    print(new_worker.belt)
    print(new_worker.container)
    droid_factory(new_worker)
    print(new_worker.belt)
    print(new_worker.container)
    print("------------------------------------------------------------------------")
    print("Tasks 5 Test")
    """
    This test asks the user to input
    a worker name and a file. Then proceeds to 
    call the whole code.
    """
    filename = input("Enter parts filename:")
    worker_name = input("Enter worker name:")
    print(worker_name, "is starting: a shift at the droid factory!")
    new_worker = Worker(worker_name, mkEmptyQueue(), mkEmptyStack())
    new_worker.belt = worker_belt(filename)
    droid_factory(new_worker)

def main():
    """
    Main function that calls the
    entire, structured code.
    :return:
    """
    filename = input("Enter parts filename:")
    worker_name = input("Enter worker name:")
    print(worker_name, "is starting: a shift at the droid factory!")
    new_worker = Worker(worker_name, mkEmptyQueue(), mkEmptyStack())
    new_worker.belt = worker_belt(filename)
    droid_factory(new_worker)
    #task_tests()

main()