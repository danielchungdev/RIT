import turtle as t


def set_up():
    t.getscreen()


def draw_hexagon():
    t.pendown()
    t.forward(60)
    t.left(60)
    t.forward(60)
    t.left(60)
    t.forward(60)
    t.left(60)
    t.forward(60)
    t.left(60)
    t.forward(60)
    t.left(60)
    t.forward(60)
    t.left(60)

def draw_all_hexagons():
    draw_hexagon()
    t.penup()
    t.forward(60)
    t.right(60)
    draw_hexagon()
    t.penup()
    t.forward(60)
    t.right(60)
    draw_hexagon()
    t.penup()
    t.forward(60)
    t.right(60)
    draw_hexagon()
    t.penup()
    t.forward(60)
    t.right(60)
    draw_hexagon()
    t.penup()
    t.forward(60)
    t.right(60)
    draw_hexagon()
    t.right(60)

def re_setup():
    t.left(60)
    t.forward(60)
    t.right(60)
    t.forward(60)

def main():
    set_up()
    draw_all_hexagons()
    re_setup()
    draw_all_hexagons()
    t.done()

if __name__ == "__main__":
    main()