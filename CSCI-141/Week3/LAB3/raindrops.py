"""
Daniel Chung
"""
import turtle
import math
import random
"""
Sets the Length and width for the pond
"""
def PONDDIMENSIONS():
    return 600
"""
Determine de limit where the drop can be in the pond
"""
def PONDLIMIT():
    return 300
"""
The minimum amount of drops that can be used as an input
"""
def MINIMUMDROPS():
    return 0
"""
The maximum amount of drops that can be used as an input
"""
def MAXIMUMDROPS():
    return 100
"""
Sets the size of the pond.
"""
def pond():
    turtle.speed(0)
    turtle.up()
    turtle.right(90)
    turtle.forward(PONDDIMENSIONS()/2)
    turtle.right(90)
    turtle.down()
    turtle.fillcolor("light sky blue")
    turtle.begin_fill()
    turtle.forward(PONDDIMENSIONS()/2)
    turtle.right(90)
    turtle.forward(PONDDIMENSIONS())
    turtle.right(90)
    turtle.forward(PONDDIMENSIONS())
    turtle.right(90)
    turtle.forward(PONDDIMENSIONS())
    turtle.right(90)
    turtle.forward(PONDDIMENSIONS()/2)
    turtle.right(90)
    turtle.end_fill()
    turtle.color("black")
    turtle.up()
    turtle.forward(PONDDIMENSIONS()/2)
    turtle.right(90)
    turtle.speed(1)
"""
Draws the raindrops with the ripples(non-tail recursive)
"""
def raindrops(r, n):
    if n < MINIMUMDROPS() or n > MAXIMUMDROPS():
        return ("Raindrops must be between 1 and 100")
    elif n == 0:
        return 0
    else:
        turtle.setpos(random.randint(-PONDLIMIT() + 7 * r, PONDLIMIT() - 7 * r), random.randint(-PONDLIMIT() + 7 * r, PONDLIMIT() - 7 * r))
        turtle.down()
        turtle.fillcolor(random.random(), random.random(), random.random())
        turtle.begin_fill()
        turtle.circle(r)
        turtle.end_fill()
        turtle.up()
        return (2 * math.pi * r) + ripples(r, 5, 0, 2) + raindrops(random.randint(1, 15), n - 1)
"""
Draws the ripples for the raindrops (Tail-recursive)
"""
def ripples(r, n, e, acc):
    if n == 0:
        return e
    turtle.speed(0)
    turtle.up()
    turtle.right(90)
    turtle.forward(r)
    turtle.left(90)
    turtle.down()
    turtle.circle(acc * r)
    turtle.up()
    return ripples(r, n - 1, 2 * math.pi * acc * r + e, acc + 1)
"""
Main function embodies the whole code
"""
def main():
    pond()
    """print(ripples(10, 5, 0)) THIS IS A TEST"""
    print(raindrops(random.randint(1,15), int(input("Number of raindrops"))))
    print("Close the window")
    turtle.done()
"""
Calls the code
"""
main()