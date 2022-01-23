import turtle as turtle

def main():
    #code goes here
    draw_stick_figure()
    draw_it_again()
    draw_it_again()
    draw_it_again()
    draw_it_again()
    #code ends here
    turtle.done()


def draw_stick_figure():
    draw_torso()
    draw_head()
    draw_arms()
    draw_legs()

def draw_torso():
    turtle.right(90)
    turtle.forward(100)
    turtle.back(100)
    turtle.left(90)
def draw_legs():
    turtle.forward(22)
    turtle.right(90)
    turtle.forward(70)
    turtle.left(45)
    turtle.forward(25)
    turtle.back(25)
    turtle.right(90)
    turtle.forward(25)
def draw_arms():
    turtle.right(90)
    turtle.forward(30)
    turtle.left(90)
    turtle.forward(22)
    turtle.back(44)
def draw_head():
    turtle.circle(20)

def draw_it_again():
    turtle.up()
    turtle.right(45)
    turtle.forward(100)
    turtle.right(90)
    turtle.forward(117)
    turtle.right(90)
    turtle.down()
    draw_stick_figure()
    
main()

