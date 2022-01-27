"""
Name: Daniel Chung
Class: CSCI-141
Assignment: Homework 4
Date: Jan 25, 2022
"""
import turtle
import math 

PEN_WIDTH = 2
SIDE = 100

def init():
    turtle.up()
    turtle.setpos( -50, -200 )
    turtle.down()
    turtle.speed( 0 )
    turtle.pensize( PEN_WIDTH )
    turtle.down()

########## STUDENTS ####################
#
# You must fill in the code,
# _and_documentation_,
# for the following four functions.
#
########################################

def draw_baobab_1( side ):
    turtle.forward(side)
    turtle.left(90)
    turtle.forward(side)
    turtle.left(90)
    turtle.forward(side)
    turtle.left(90)
    turtle.forward(side)
    turtle.forward(-side)
    turtle.left(90)
    turtle.left(45)
    turtle.forward(side)
    turtle.right(90)
    turtle.forward(side)
    turtle.left(45)

def draw_baobab_2( side ):
    new_lenght = side * (math.sqrt(2)/ 2)
    turtle.forward(new_lenght)
    turtle.left(90)
    turtle.forward(new_lenght)
    turtle.left(90)
    turtle.forward(new_lenght)
    turtle.left(90)
    turtle.forward(new_lenght)

def draw_baobab_3( side ):
    pass


def draw_baobab_rec( side, depth ):
    pass


def main():
    init()
    print( "Drawing a depth-1 baobab drawing." )
    draw_baobab_1( SIDE )
    input( "Hit ENTER to proceed to depth 2:" )
    draw_baobab_2( SIDE )
    input( "Hit ENTER to proceed to depth 3:" )
    turtle.clear()
    draw_baobab_3( SIDE )
    input( "Hit ENTER to proceed to recursive code:" )
    turtle.clear()
    depth = int( input( "depth? " ) )
    draw_baobab_rec( SIDE, depth )
    print( "Close the window to end the program." )
    turtle.done()


if __name__ == '__main__':
    main() 
