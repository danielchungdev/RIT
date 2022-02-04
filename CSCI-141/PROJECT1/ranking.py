"""
CSCI - 141
CS1 - Project 1
Name: Daniel Chung
Task 1: Ranking
Date: 11/18/17
"""

import sys
from utils import *


def sorted_ranking_data(data, year):
    """
    This function takes the data and puts it into
    the CountryValue structure inside a list.
    :param data:
    :param year:
    :return an ordered list with all values in CountryValue:
    """
    if data  == None:
        return []
    else:
        country_list = []
        init_year = 1960
        current_year = int(year) - init_year
        for i in data:
            if data[i].life_expectancy[current_year] == '':
                pass
            else:
                country_list.append(CountryValue(data[i].name, float(data[i].life_expectancy[current_year])))
        country_list = quicksort(country_list)
        return country_list

def partition(pivot, alist):
    """
    Function necessary for quicksort
    :param pivot:
    :param alist:
    :return 3 lists.:
    """
    (less, same, more) = ([], [], [])
    for i in alist:
        if i.value < pivot:
            more.append(i)
        elif i.value > pivot:
            less.append(i)
        else:
            same.append(i)
    return (more, same, less)

def quicksort(alist):
    """
    I use quicksort for my implementation.
    I changed quicksort so it was compatible
    with rit_lib.
    :param alist:
    :return a sorted list:
    """
    if alist == []:
        return []
    else:
        pivot = alist[0].value
        (less, same, more) = partition(pivot, alist)
        return quicksort(more) + same + quicksort(less)

def top_10(ordered_list):
    """
    This function gets the top 10
    countries with best life expectancy.
    :param ordered_list:
    :return printed output for the top 10:
    """
    if ordered_list == []:
        return []
    else:
        number = 1
        for i in ordered_list:
            if number == 11:
                break
            else:
                print("", number, ":" , i.country, i.value)
                number += 1

def bottom_10(ordered_list):
    """
    This function gets the bottom 10
    countries with worst life expectancy.
    :param ordered_list:
    :return printed output for the bottom 10:
    """
    if ordered_list == []:
        return []
    else:
        number = len(ordered_list)
        count = 0
        index = len(ordered_list) - 1
        while count != 10:
            if index < 0:
                break
            else:
                print("", number, ":", ordered_list[index].country, ordered_list[index].value)
                count += 1
                index -= 1
                number -= 1

def valid_years():
    """
    This function makes a list with
    all the available years from
    1960 to 2015.
    :return a list with a list of years 1960 to 2015:
    """
    valid_years_list = []
    current_year = 1960
    while current_year <= 2015:
        valid_years_list.append(str(current_year))
        current_year += 1
    return valid_years_list

def looped_function(data):
    """
    This gets the function in a looped function.
    When the user inputs -1, it will exit the
    program.
    :param data:
    :return printed output:
    """
    while 1 > 0:
        user_input_year = input("Enter year of interest(-1 to quit):")
        if str(user_input_year) == "-1":
            sys.exit()
        else:
            if user_input_year not in valid_years():
                print("Valid years are 1960 - 2015")
                print("")
                looped_function(data)
            else:
                user_input_region = input("Enter region(type 'all' to consider all):")
                if user_input_region == "-1":
                    sys.exit()
                else:
                    if user_input_region not in valid_regions(data):
                        print(user_input_region, "not a valid region")
                        print("")
                        looped_function(data)
                    else:
                        user_input_income = input("Enter income category (type 'all' to consider all):")
                        if user_input_income == "-1":
                            sys.exit()
                        else:
                            if user_input_income not in valid_incomes(data):
                                print(user_input_income, "not a valid income")
                                print("")
                                looped_function(data)
                            else:
                                filtered_data = complete_filter(data, user_input_region, user_input_income)
                                the_list = sorted_ranking_data(filtered_data, user_input_year)
                                print("")
                                print("Top 10 Life Expectancy for", user_input_year)
                                top_10(the_list)
                                print("")
                                print("Botoom 10 Life Expectancy for", user_input_year)
                                bottom_10(the_list)
                                print("")
                                looped_function(data)

def main():
    """
    Main function for the whole code.
    :return the correct output:
    """
    filename = "worldbank_life_expectancy"
    data = read_data(filename)
    looped_function(data)

if __name__ == '__main__':
    main()