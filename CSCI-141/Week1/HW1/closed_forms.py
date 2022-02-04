'''
Daniel Chung
'''

import turtle
'''
Puts the turtle in the wanted initial position
'''
def initialize():
    turtle.speed(10)
    turtle.home()
    turtle.left
'''
Draws the square
'''
def square():
    turtle.down()
    turtle.forward(50)
    turtle.left(90)
    turtle.forward(50)
    turtle.left(90)
    turtle.forward(50)
    turtle.left(90)
    turtle.forward(50)
    turtle.left(90)
'''
draws the octagon
'''
def octagon():
    turtle.up()
    turtle.forward(100)
    turtle.down()
    turtle.right(45)
    turtle.forward(100)
    turtle.right(45)
    turtle.forward(100)
    turtle.right(45)
    turtle.forward(100)
    turtle.right(45)
    turtle.forward(100)
    turtle.right(45)
    turtle.forward(100)
    turtle.right(45)
    turtle.forward(100)
    turtle.right(45)
    turtle.forward(100)
    turtle.right(45)
    turtle.forward(100)
'''
Draws the hexagon
'''
def hexagon():
    turtle.up()
    turtle.left(90)
    turtle.forward(100)
    turtle.down()
    turtle.forward(50)
    turtle.left(60)
    turtle.forward(50)
    turtle.left(60)
    turtle.forward(50)
    turtle.left(60)
    turtle.forward(50)
    turtle.left(60)
    turtle.forward(50)
    turtle.left(60)
    turtle.forward(50)
    turtle.left(60)
    turtle.up()
'''
Contains all 3 drawings
'''
def mainpicture():
    square()
    octagon()
    hexagon()
'''
calls the whole code into one.
'''
def main():
    initialize()
    mainpicture()
    turtle.forward(50)
    turtle.left(60)
    mainpicture()
    turtle.left(140)
    turtle.forward(-200)
    mainpicture()
    turtle.done()

main()