"""
Daniel Chung
Lab 4
Inscribing Polygons
"""
import turtle
import math
import sys
"""
This functions stets the turtle in the correct place to fit the canvas
"""
def init_turtule():
    turtle.up()
    turtle.right(90)
    turtle.forward(250)
    turtle.left(90)
    turtle.speed(0)
"""
This function makes sure that the color alternates every 3 polygons
"""
def color(u):
    p = u
    if p == 1:
        turtle.pencolor("blue")
        p + 1
    elif p == 2:
        turtle.pencolor("red")
        p + 1
    elif p == 3:
        turtle.pencolor("green")
        p - 2
"""
This is the main iterative function that makes the polygon
with circles inside of them.
"""
def polygon(r, n, acc):
    if n < 2:
        print("Polygon doesn't exist")
        sys.exit()
        pass
    else:
        """
        Variables
        """
        k = 0
        l = n
        a = 360 / n
        c = math.sqrt(2 * r ** 2 - 2 * r ** 2 * math.cos(math.radians(a)))
        t = (180 - a) / (n - 2)
        newradius = math.sqrt(r ** 2 - (c / 2) ** 2)
        """
        Draws polygon
        """
        turtle.pendown()
        turtle.pencolor("black")
        turtle.circle(r)
        turtle.left(t)
        if acc > 3:
            acc = acc - 3
        while l > 0:
            color(acc)
            turtle.forward(c)
            turtle.left(a)
            k = c * n + k
            l = l - 1
        turtle.forward(c / 2)
        n = n - 1
        if n == 2:
            pass
        else:
            polygon(newradius, n, acc + 1)
        return k
    return 0
"""
This is the function tests what happens when print and no print does,
and what happens when there is less than 3 sides in a polygon
and also negative numbers
"""
def test_polygon():
    polygon(250, 10, 1)
    print(polygon(250, 10, 1))
    polygon(250, 3, 1)
    print(polygon(250, 3, 1))
    polygon(250, 2, 1)
    print(polygon(250, 2, 1))
    polygon(250, 0, 1)
    print(polygon(250, 0, 1))
    polygon(250, -1, 1)
    print(polygon(250, -1, 1))
    polygon(250, 1000, 1)
    print(polygon(250, 1000, 1))
"""
This is the function where everything is stored and also loops
twice to draw 2 polygons.
"""
def main():
    p = 2
    init_turtule()
    while p > 0:
        print(polygon(250, int(input("Select a number of sides")), 1))
        #test_polygon()
        p = p - 1
    turtle.done()
"""
This calls the whole code.
"""
main()