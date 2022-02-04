"""
Name: Daniel Chung
Homework 7: priorityqueue.py
Date: 10/16/17

Answers to Question

1. O(c) because my code orders the list so it goes in a
descending order, therefor the highest priority will ALWAYS
be of index [-1].

2.O(logN) because it uses binary search to look for the spot
where the number has to be inserted.

3. O(n) because you have to swap it. Also because if the
number added is of index 0, my code will make you swap one
number to the position it should be.

4.O(c) since my code orders the list in descending order, the
dequeue function that does list.pop() only has to go to the
end of the list and return that number, making it one operation.

5. My priority queue would be worst, because an unsorted list will
cause my code to break, due to the use of binary search (because we
assume that the list is empty), to make it work I would first have
to do sort function, and by doing a sort function, there there is
really no point in using binary search, since it will already be
sorted.
"""

"""
This implementation of binary search, returns to you the position
in which the number should be inserted into the list. It is also
in descending order to make sure that the last number is the 
smallest one.
"""
lista = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
def binary_search(data, target, start, end):

    if start > end:
        return start

    mid_index = (start + end) // 2
    mid_value = data[mid_index]

    if target == mid_value:
        return mid_index

    elif mid_value < target:
        return binary_search(data, target, start, mid_index - 1)

    else:
        return binary_search(data, target, mid_index + 1, end)

"""
Enqueue function takes the result that binary search got, adds a 
number at the end of the list and swapps it where it's supposed
to go. If the number is at the end, then it makes sure that 
everything stays in order.
"""
def enqueue(list, number):
    idx = binary_search(list, number, 0, len(list) - 1)

    if list == []:
        list += [number]


    else:

        idx = binary_search(list, number, 0, len(list) - 1)
        list += [number]
        placeholder = list[idx]
        list[idx] = list[-1]
        list[-1] = placeholder
        a = 0
        b = 0

        while list[-1 - a] > list[-2 - b]:
            temp = list[-2 - b]
            list[-2 - b] = list[-1 - a]
            list[-1 - a] = temp
            a += 1
            b += 1

"""
This is another solution I came up with,but when I posed on the 
discussions wasn't sure if it was the correct way, but this one 
also uses binary search to look for the place where the number 
should be, the difference is that this code slices the string, to
add a "placeholder" and then swaps the place holder with the 
number making sure it stays in place. The only problem with this 
function is that when doing the test cases you have to do 
pq = enqueue(pq, 5) or else it won't overwrite the list.
"""
def maybe(list, number):
    idx = binary_search(list, number, 0, len(list) - 1)
    if number <= 1:
        list = list[0:] + ["none"]
        list[len(list) - 1] = number
        return list
    elif idx > len(list) - 1:
        list = ["none"] + list[0:]
        list[0] = number
        return list
    else:
        list = list[0:idx] + ["none"] + list[idx:]
        list[int(idx)] = number
        return list
"""
This function checks that if the list is blank [] then it makes
sure to do nothing or else the code would crash. If the list has
something then it will return the last number(Highest priority) 
because my enqueue function makes sure that everything is in 
descending order making sure that it stays as O(c) and not O(n).
"""
def dequeue(list):
    if list == []:
        return "None"
    else:
        return list.pop()
"""
This is are the test function testing out that my code works. 
It does output the correct answer.
"""
def sampleTest():
    pq = list()
    print("Highest priority item is", dequeue(pq))
    print("Next highest priority task is", dequeue(pq))

def sampleTest2():
    pq = list()
    enqueue(pq, 0)
    enqueue(pq, 1)
    enqueue(pq, 2)
    enqueue(pq, 3)
    enqueue(pq, 4)
    enqueue(pq, 5)
    enqueue(pq, 6)
    enqueue(pq, 7)
    enqueue(pq, 8)
    enqueue(pq, 9)
    enqueue(pq, 10)
    print("Highest priority item is", dequeue(pq))
    print("Next highest priority task is", dequeue(pq))

def sampleTest3():
    pq = list()
    enqueue(pq, 10)
    enqueue(pq, 9)
    enqueue(pq, 8)
    enqueue(pq, 7)
    enqueue(pq, 6)
    enqueue(pq, 5)
    enqueue(pq, 4)
    enqueue(pq, 3)
    enqueue(pq, 2)
    enqueue(pq, 1)
    print("Highest priority item is", dequeue(pq))
    print("Next highest priority task is", dequeue(pq))

def sampleTest4():
    pq = list()
    enqueue(pq, 5)
    enqueue(pq, 9)
    enqueue(pq, 1)
    enqueue(pq, 50)
    enqueue(pq, 25)
    print("Highest priority item is", dequeue(pq))
    print("Next highest priority task is", dequeue(pq))


"""
Calls the whole code
"""

def main():
    print("This is Test1")
    sampleTest()
    print("This is Test2")
    sampleTest2()
    print("This is Test3")
    sampleTest3()
    print("This is Test4")
    sampleTest4()

main()