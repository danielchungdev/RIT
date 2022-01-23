'''
File: scenery.py
Author: Daniel Chung
Date: 10/7/2021
Class: CSCI-141
'''

import turtle as t

def setup():
    t.getscreen()
    t.penup()
    t.right(90)
    t.forward(200)
    t.left(90)
    t.forward(-200)

def draw_house(): 
    t.pendown()
    t.forward(100)
    t.left(90)
    t.forward(100)
    t.left(90)
    t.forward(100)
    t.left(90)
    t.forward(100)
    

def draw_windows():
    pass

def draw_tree():
    t.pendown()
    t.color("#6F4E37")
    t.begin_fill()
    t.left(90)
    t.forward(100)
    t.left(90)
    t.forward(20)
    t.left(90)
    t.forward(100)
    t.right(90)
    t.forward(-20)
    t.right(180)
    t.end_fill()
    t.forward(-10)
    t.left(90)
    t.forward(100)
    t.right(90)
    t.color("green")
    t.begin_fill()
    t.circle(50)
    t.end_fill()
    t.right(90)
    t.penup()
    t.forward(100)
    t.left(90)

def draw_door():
    pass

def draw_sun():
    pass

def main():
    setup()
    draw_tree()
    t.forward(90)
    t.done()

if __name__ == "__main__":
    main()