"""
Name: Daniel Chung
Lab: Moving Day
Date: 10/30/2017
CSCI-141
"""

from rit_lib import *
import random
import math
import time
"""
Structures used for this lab created
with RIT_lib
"""
Box = struct_type("Box",
                  (int, "number"),
                  (int, "weight"),
                  (int, "current"),
                  (list, "items"),
                  (list, "value"))

Obj = struct_type("Obj",
                  (str, "name"),
                  (int, "weight"))
"""
Prompts the user for a file and opens it
creates the two lists, the box list and 
item list.
"""
def open_boxes():
    box_count = 0
    current = 0
    file = open("Select a File")
    opened = file.readline()
    boxes = []
    for line in opened.split():
        box_count += 1
        interger = int(line)
        boxes.append(Box(box_count, interger, current, [], []))
    items = []
    for line in file:
        line = line.strip()
        line = line.split()
        items.append(Obj(line[0], int(line[1])))
    file.close()
    return boxes, items
"""
Used to set the sorting by weight
on the item list.
"""
def key_obj(items):
    return items.weight
"""
This function sorts the item list
from biggest to smallest.
"""
def sort_items():
    boxes, items = open_boxes()
    items.sort(key=key_obj, reverse=True)
    return items
"""
Used for the first greedy algorith,
finds the box in which the items 
should be place.
"""
def box_finder(a_list):
    best_box = 0
    boxes = a_list
    for i in range(len(boxes)):
        if boxes[i].weight - boxes[i].current > boxes[best_box].weight - boxes[best_box].current:
            best_box = i
    return best_box
"""
Greedy Algorithm 1, looks for the
box with the most space and puts the items
into it.
"""
def roomiest():
    box_list = open_boxes()[0]
    items = sort_items()
    box = box_finder(box_list)
    left_out = []
    left_out_values = []
    for item in range(len(items)):
        if items[item].weight <= box_list[box].weight - box_list[box].current:
            box_list[box].current += items[item].weight
            box_list[box].value.append(items[item].weight)
            box_list[box].items.append(items[item].name)
            box = box_finder(box_list)
        else:
            left_out.append(items[item].name)
            left_out_values.append(items[item].weight)
    if left_out == []:
        print("Results from Greedy Strategy 1")
        print("All items successfully packed into boxes!")
        for box in range(len(box_list)):
            print("Box", box_list[box].number, "of weight capacity", box_list[box].weight, "contains:")
            b = 0
            for x in box_list[box].items:
                print("   ",x ,"of weight", box_list[box].value[b])
                b += 1
    else:
        print("Results from Greedy Strategy 1")
        print("Unable to pack all items!")
        for box in range(len(box_list)):
            print("Box", box_list[box].number, "of weight capacity", box_list[box].weight, "contains:")
            b = 0
            for x in box_list[box].items:
                print("   ",x ,"of weight", box_list[box].value[b])
                b = b + 1
        v = 0
        for item in left_out:
            print(item, "of weight",left_out_values[v], "got left behind.")
            v = v + 1
    return box_list, left_out
"""
Second Greedy Algorithm, looks for the box with
the least capacity and chooses it to place the
items in it.
"""
def box_finder_smaller(a_list):
    best_box = 0
    boxes = a_list
    for i in range(len(boxes)):
        if boxes[i].weight - boxes[i].current > boxes[best_box].weight - boxes[best_box].current:
            best_box = i
    return best_box
"""
Second Greedy Algorithm, looks for the box
with the least capacity but that still accepts the 
item and puts it into it.
"""
def TightestFit():
    box_list = open_boxes()[0]
    items = sort_items()
    box = box_finder_smaller(box_list)
    left_out = []
    left_out_values = []
    for item in range(len(items)):
        if items[item].weight <= box_list[box].weight - box_list[box].current:
            box_list[box].current += items[item].weight
            box_list[box].value.append(items[item].weight)
            box_list[box].items.append(items[item].name)
            box = box_finder_smaller(box_list)
        else:
            left_out.append(items[item].name)
            left_out_values.append(items[item].weight)
    if left_out == []:
        print("Results from Greedy Strategy 2")
        print("All items successfully packed into boxes!")
        for box in range(len(box_list)):
            print("Box", box_list[box].number, "of weight capacity", box_list[box].weight, "contains:")
            b = 0
            for x in box_list[box].items:
                print("   ",x ,"of weight", box_list[box].value[b])
                b += 1
    else:
        print("Results from Greedy Strategy 2")
        print("Unable to pack all items!")
        for box in range(len(box_list)):
            print("Box", box_list[box].number, "of weight capacity", box_list[box].weight, "contains:")
            b = 0
            for x in box_list[box].items:
                print("   ",x ,"of weight", box_list[box].value[b])
                b = b + 1
        v = 0
        for item in left_out:
            print(item, "of weight",left_out_values[v], "got left behind.")
            v = v + 1
    return box_list, left_out
"""
Greedy Algorithm 3. Goes box by box putting
items into it until nothing fits in any box.
"""
def One_Box_at_a_Time():
    items_list = sort_items()
    box_list = open_boxes()[0]
    left_out = []
    left_out_values = []
    for box in box_list:
        for item in items_list:
            if item.weight <= box.weight - box.current:
                box.items.append(item.name)
                box.current += item.weight
                box.value.append(item.weight)
                items_list.remove(item)
    for missing_item in items_list:
        left_out.append(missing_item.name)
        left_out_values.append(missing_item.weight)
    if left_out == []:
        print("Results from Greedy Strategy 2")
        print("All items successfully packed into boxes!")
        for box in range(len(box_list)):
            print("Box", box_list[box].number, "of weight capacity", box_list[box].weight, "contains:")
            b = 0
            for x in box_list[box].items:
                print("   ",x ,"of weight", box_list[box].value[b])
                b += 1
    else:
        print("Results from Greedy Strategy ")
        print("Unable to pack all items!")
        for box in range(len(box_list)):
            print("Box", box_list[box].number, "of weight capacity", box_list[box].weight, "contains:")
            b = 0
            for x in box_list[box].items:
                print("   ", x, "of weight", box_list[box].value[b])
                b = b + 1
        v = 0
        for item in left_out:
            print(item, "of weight", left_out_values[v], "got left behind.")
            v = v + 1
    return box_list, left_out
"""
Main function that calls
the whole code. 
NOTE: FOR SOME REASON IT ALWAYS ASK FOR A FILE TWICE...
"""
def main():
    roomiest()
    TightestFit()
    One_Box_at_a_Time()

main()
