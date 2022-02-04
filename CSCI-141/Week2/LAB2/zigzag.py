'''
Daniel Chung
'''
import turtle
'''
Allows the user to set the speed of the turtle, and get her in the middle.
'''
def init_turtle():
    turtle.speed(0)
    turtle.home()
'''
This is what will draw the zigzag(s)
'''
def zigzag(L, D):
    if D == 0:
        pass
    else:
        '''
        Draws the top half of the zig, depending if it's even it will draw red and if it's odd it will draw green0
        '''
        if D % 2 == 0:
            turtle.pencolor("red")
        else:
            D % 2 != 0
            turtle.pencolor("green")
        turtle.down()
        turtle.left(90)
        turtle.forward(L/2)
        turtle.right(90)
        turtle.forward(L)
        turtle.left(45)
        '''
        If depth >1 will draw the top half of the mini zigs
        '''
        zigzag(L/2, D - 1)
        '''
        Draws the bottom part of the zig
        '''
        turtle.right(45)
        turtle.up()
        turtle.back(L)
        turtle.left(90)
        turtle.back(L/2)
        turtle.down()
        turtle.back(L/2)
        turtle.right(90)
        turtle.back(L)
        turtle.left(45)
        '''
        If depth >1 will draw the bottom half of the mini zags
        '''
        zigzag(L/2, D-1)
        '''
        Gets the turtle back into the initial position and traces over with the correct color.
        '''
        if D % 2 == 0:
            turtle.pencolor("Red")
        else:
            D % 2 != 0
            turtle.pencolor("green")
        turtle.right(45)
        turtle.forward(L)
        turtle.left(90)
        turtle.forward(L/2)
        turtle.right(90)
'''
Contains the whole code
'''
def main():
    init_turtle()
    zigzag(int(input("Input a length")), int(input("Input a depth")))
    turtle.done()
'''
Calls the code.
'''
main()