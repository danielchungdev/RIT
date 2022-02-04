"""
CSCI - 141
CS1 - Project 1
Name: Daniel Chung
Task 4: Factors
Date: 11/18/17
"""

from utils import *
import turtle
import random

def draw_axis():
    """
    This functions draws the main axis.
    :return:
    """
    turtle.setworldcoordinates(-10, -5, 50, 30)
    turtle.forward(55/2)
    turtle.home()
    turtle.left(90)
    turtle.forward(35/2)
    turtle.home()

def draw_x_axis():
    """
    This function draws the values
    in the x axis.
    :return:
    """
    #DRAW 1960
    turtle.right(90)
    turtle.up()
    turtle.forward(2)
    turtle.right(90)
    turtle.forward(1)
    turtle.down()
    turtle.write("1960")
    turtle.up()
    turtle.home()
    turtle.down()

    #DRAW YEAR
    turtle.forward(55/4)
    turtle.right(90)
    turtle.up()
    turtle.forward(4)
    turtle.right(90)
    turtle.forward(1)
    turtle.down()
    turtle.write("Year")
    turtle.up()
    turtle.home()
    turtle.down()

    #DRAW 2015
    turtle.forward(55/2)
    turtle.right(90)
    turtle.up()
    turtle.forward(2)
    turtle.right(90)
    turtle.forward(1)
    turtle.down()
    turtle.write("2015")
    turtle.up()
    turtle.home()
    turtle.down

def draw_y_axis():
    """
    This function writes the
    values for the y axis.
    :return:
    """
    count = 1
    number = 10
    turtle.left(90)
    while count != 10:
        turtle.forward(1.94444444)
        turtle.left(90)
        turtle.up()
        turtle.forward(1.5)
        turtle.down()
        if number == 50:
            turtle.write(number)
            turtle.up()
            turtle.forward(2)
            turtle.down()
            turtle.write("Life")
            turtle.up()
            turtle.right(90)
            turtle.forward(1)
            turtle.down()
            turtle.write("Exp.")
            turtle.up()
            turtle.forward(-1)
            turtle.left(90)
            turtle.forward(-2)
        turtle.down()
        turtle.write(number)
        turtle.up()
        turtle.forward(-1.5)
        turtle.right(90)
        turtle.down()
        count += 1
        number += 10
    turtle.home()

def filtered_region(data, region):
    """
    This function creates the list that will
    soon become the coordinates. This one
    takes only the regions.
    :param data:
    :param region:
    :return:
    """
    new_data = filter_only_countries(data)
    updated_data = filter_region(new_data, region)
    current_year = 1960
    initial_year = 1960
    all_countries_list= []
    for i in updated_data:
        a_list = updated_data[i].life_expectancy
        a_list = str_into_0(a_list)
        all_countries_list.append(a_list)
    structure_list = []
    while current_year != 2016:
        year_list = []
        for e in range(len(all_countries_list)):
            year_list.append(all_countries_list[e][current_year - initial_year])
        year_list.sort()
        median = year_list[len(year_list)//2]
        structure = Median(current_year, median)
        current_year += 1
        structure_list.append(structure)
    return structure_list

def filtered_data(data, income):
    """
    This function creates the list that will
    soon become the coordinates. This one
    takes only the regions.
    :param data:
    :param region:
    :return:
    """
    new_data = filter_only_countries(data)
    updated_data = filter_income(new_data, income)
    current_year = 1960
    initial_year = 1960
    all_countries_list= []
    for i in updated_data:
        a_list = updated_data[i].life_expectancy
        a_list = str_into_0(a_list)
        all_countries_list.append(a_list)
    structure_list = []
    while current_year != 2016:
        year_list = []
        for e in range(len(all_countries_list)):
            year_list.append(all_countries_list[e][current_year - initial_year])
        year_list.sort()
        median = year_list[len(year_list)//2]
        structure = Median(current_year, median)
        current_year += 1
        structure_list.append(structure)
    return structure_list

def make_into_coordinates(income_median):
    """
    This function takes the list
    into the coordinates.
    :param income_median:
    :return:
    """
    x_axis = 0.5
    y_axis = 0.19444444
    count = 0
    coordinates_list = []
    for i in income_median:
        i = i.median
        coords = (count * x_axis, i * y_axis)
        coordinates_list.append(coords)
        count += 1
    return coordinates_list

def coordinates_for_region(data, region):
    """
    This function makes the coordinates
    based on the region.
    :param data:
    :param region:
    :return:
    """
    region_median = filtered_region(data, region)
    coordinate = make_into_coordinates(region_median)
    return coordinate

def coordinates_for_income(data, income):
    """
    This function gets the coordinates
    values for income.
    :param data:
    :param income:
    :return:
    """
    income_median = filtered_data(data, income)
    coordinate = make_into_coordinates(income_median)
    return coordinate

def graphing(coordinates, color):
    """
    This function writes the line
    with turtles, it takes the coordinates
    and picks a color.
    :param coordinates:
    :param color:
    :return:
    """
    for i in range(len(coordinates)):
        if coordinates[i][0] == 0.0:
            turtle.pencolor("black")
        else:
            turtle.pencolor(color)
            turtle.down()
        turtle.setpos(coordinates[i][0], coordinates[i][1])
    turtle.up()
    turtle.home()
    turtle.down()

def write_key(string, color, current):
    """
    This function writes the keys
    for the graph.
    :param string:
    :param color:
    :param current:
    :return:
    """
    turtle.pencolor(color)
    turtle.up()
    turtle.setpos(6.875, current)
    turtle.down()
    turtle.forward(3)
    turtle.up()
    turtle.forward
    turtle.down()
    turtle.write(string)
    turtle.up()

def complete_axis_incomes(data):
    """
    This codes puts together the
    everything to draw the graph
    of the incomes axis.
    :param data:
    :return:
    """
    draw_axis()
    draw_x_axis()
    draw_y_axis()
    incomes = ['Upper middle income', 'High income', 'Lower middle income', 'Low income']
    colors = ["blue","brown","red", "green","orange","turquoise","pink"]
    current = 25
    for i in incomes:
        color = random.choice(colors)
        colors.remove(color)
        write_key(i, color, current)
        current -= 1
        coordinates = coordinates_for_income(data, i)
        graphing(coordinates, color)
        turtle.pencolor("black")

def complete_axis_region(data):
    """
   This codes puts together the
   everything to draw the graph
   of the region axis.
   :param data:
   :return:
   """
    draw_axis()
    draw_x_axis()
    draw_y_axis()
    incomes = ['North America', 'South Asia', 'East Asia & Pacific', 'Sub-Saharan Africa', 'Europe & Central Asia', 'Middle East & North Africa', 'Latin America & Caribbean']
    colors = ["blue", "brown", "red", "green", "orange", "turquoise", "pink"]
    current = 27
    for i in incomes:
        color = random.choice(colors)
        colors.remove(color)
        write_key(i, color, current)
        current -= 1
        coordinates = coordinates_for_region(data, i)
        graphing(coordinates, color)
        turtle.pencolor("black")


def main():
    filename = "worldbank_life_expectancy"
    data = read_data(filename)
    complete_axis_incomes(data)
    user_input = input("Press Enter to continue")
    if user_input == "":
        turtle.resetscreen()
        complete_axis_region(data)
        turtle.exitonclick()
    else:
        turtle.bye()

main()