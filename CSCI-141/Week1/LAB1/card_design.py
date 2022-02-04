'''
Daniel Chung
'''
import turtle
'''
Initializes the turtle, makes sure it's at its starting point.
'''
def initializeturtle():
    turtle.home()
    turtle.up()
    turtle.speed(10)
    turtle.pensize(3)
'''
Draws the main diamond
'''
def biddiamond():
    turtle.begin_fill()
    turtle.color("Red")
    turtle.forward(125)
    turtle.left(90)
    turtle.forward(50)
    turtle.down()
    turtle.right(30)
    turtle.forward(75)
    turtle.left(60)
    turtle.forward(75)
    turtle.left(120)
    turtle.forward(75)
    turtle.left(60)
    turtle.forward(75)
    turtle.up()
    turtle.right(390)
    turtle.forward(50)
    turtle.right(90)
    turtle.forward(125)
    turtle.right(180)
    turtle.end_fill()
'''
Draws the cards with a grey background
'''
def border():
    turtle.color("Black")
    turtle.begin_fill()
    turtle.color("lightgrey")
    turtle.pensize(3)
    turtle.down()
    turtle.forward(250)
    turtle.left(90)
    turtle.forward(350)
    turtle.left(90)
    turtle.forward(250)
    turtle.left(90)
    turtle.forward(350)
    turtle.left(90)
    turtle.up()
    turtle.end_fill()
'''
This is the function that draws the 7 on the first card
'''
def seven():
    turtle.pensize(8)
    turtle.forward(125)
    turtle.left(90)
    turtle.forward(200)
    turtle.down()
    turtle.right(25)
    turtle.forward(100)
    turtle.left(115)
    turtle.forward(50)
    turtle.up()
    turtle.forward(-50)
    turtle.right(115)
    turtle.forward(-100)
    turtle.left(25)
    turtle.forward(-200)
    turtle.left(90)
    turtle.forward(125)
    turtle.left(180)
'''
Positions the little diamonds at each corner
'''
def positionsmalldiamond1():
    turtle.up()
    turtle.left(90)
    turtle.forward(310)
    turtle.right(90)
    turtle.forward(20)
'''
Draws the little diamonds
'''
def smalldiamond():
    turtle.begin_fill()
    turtle.color("Red")
    turtle.pensize(3)
    turtle.down()
    turtle.left(60)
    turtle.forward(10)
    turtle.left(60)
    turtle.forward(10)
    turtle.left(120)
    turtle.forward(10)
    turtle.left(60)
    turtle.forward(10)
    turtle.up()
    turtle.end_fill()
'''
Brings back the turtle back to the starting spot
'''
def backhome1():
    turtle.right(480)
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(310)
    turtle.left(90)
'''
Positions the second diamond
'''
def positionsmalldiamond2():
    turtle.forward(230)
    turtle.left(90)
    turtle.forward(20)
    turtle.right(90)
    smalldiamond()
'''
Returns the turtle back to starting point
'''
def backhome2():
    turtle.right(30)
    turtle.forward(20)
    turtle.right(90)
    turtle.forward(230)
    turtle.right(180)
'''
Positions where the second card is going to be
'''
def positionforborder2():
    turtle.forward(300)
    border()
'''
Draws the main club
'''
def clubs():
    turtle.forward(100)
    turtle.left(90)
    turtle.forward(50)
    turtle.down()
    turtle.begin_fill()
    turtle.color("black")
    turtle.right(90)
    turtle.forward(50)
    turtle.left(120)
    turtle.forward(50)
    turtle.left(120)
    turtle.forward(50)
    turtle.left(120)
    turtle.up()
    turtle.forward(25)
    turtle.left(90)
    turtle.forward(26)
    turtle.right(90)
    turtle.forward(25)
    turtle.down()
    circle()
    turtle.up()
    turtle.forward(-50)
    turtle.left(90)
    turtle.right(90)
    turtle.down()
    circle()
    turtle.up()
    turtle.forward(50)
    turtle.left(90)
    turtle.forward(55)
    turtle.down()
    circle()
    turtle.end_fill()
    turtle.up()
    turtle.right(90)
    turtle.forward(-25)
    turtle.right(90)
    turtle.forward(130.3012702)
    turtle.left(90)
    turtle.forward(-125)
'''
Draws the corner clubs
'''
def smallclub():
    turtle.down()
    turtle.begin_fill()
    turtle.color("black")
    turtle.pensize(2)
    turtle.left(90)
    turtle.right(90)
    turtle.forward(10)
    turtle.left(120)
    turtle.forward(10)
    turtle.left(120)
    turtle.forward(10)
    turtle.left(120)
    turtle.up()
    turtle.forward(5)
    turtle.left(90)
    turtle.forward(4)
    turtle.right(90)
    turtle.forward(5)
    turtle.down()
    turtle.circle(5)
    turtle.up()
    turtle.forward(-10)
    turtle.down()
    turtle.circle(5)
    turtle.end_fill()
    turtle.up()
    turtle.begin_fill()
    turtle.color("black")
    turtle.forward(10)
    turtle.left(90)
    turtle.forward(13)
    turtle.down()
    turtle.circle(5)
    turtle.end_fill()
    turtle.up()
'''
Writes the A for the second card
'''
def A():
    turtle.pensize(6)
    turtle.forward(112)
    turtle.left(90)
    turtle.forward(250)
    turtle.right(90)
    turtle.down()
    turtle.forward(25)
    turtle.left(105)
    turtle.forward(-30)
    turtle.forward(30)
    turtle.forward(50)
    turtle.left(150)
    turtle.forward(80)
    turtle.forward(-30)
    turtle.left(105)
    turtle.up()
'''
Draws a circle with radius 25
'''
def circle():
    turtle.circle(25)
'''
Calls the whole code.
'''
def main():
    initializeturtle()
    border()
    biddiamond()
    seven()
    positionsmalldiamond1()
    smalldiamond()
    backhome1()
    positionsmalldiamond2()
    backhome2()
    positionforborder2()
    border()
    clubs()
    A()
    turtle.color("black")
    turtle.forward(-112)
    turtle.right(90)
    turtle.forward(250)
    turtle.left(180)
    turtle.forward(310)
    turtle.right(90)
    turtle.forward(20)
    smallclub()
    turtle.color("black")
    turtle.forward(-20)
    turtle.left(90)
    turtle.forward(30)
    turtle.left(90)
    turtle.forward(307)
    turtle.left(90)
    turtle.forward(220)
    turtle.left(90)
    turtle.forward(10)
    turtle.right(90)
    smallclub()
    turtle.done()

main()