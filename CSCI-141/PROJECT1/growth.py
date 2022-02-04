"""
CSCI - 141
CS1 - Project 1
Name: Daniel Chung
Task 2: Growth
Date: 11/18/17
"""

import sys
from utils import *


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

def sorted_growth_data(data, year1, year2):
    if data == None:
        return []
    else:
        growth_list = []
        initial_year = 1960
        index_1 = int(year1) - initial_year
        index_2 = int(year2) - initial_year
        for i in data:
            value_1 = data[i].life_expectancy[index_1]
            value_2 = data[i].life_expectancy[index_2]
            if value_1 == '' or value_2 =='':
                pass
            else:
                growth = float(value_2) - float(value_1)
                growth_list.append(CountryValue(data[i].name, growth))
        growth_list = quicksort(growth_list)
        return growth_list

def top_10_growth(ordered_list):
    count = 1
    for i in ordered_list:
        if count == 11:
            break
        else:
            print("", count, ":", i.country, i.value)
            count += 1

def bottom_10_growth(ordered_list):
    idx = len(ordered_list) - 1
    number = len(ordered_list)
    count = 0
    while count != 10:
        if idx < 0:
            break
        else:
            print("", number, ":", ordered_list[idx].country, ordered_list[idx].value)
            idx -= 1
            number -= 1
            count += 1

def valid_years_growth():
    valid_years_list = []
    initial_year = 1960
    while initial_year < 2016:
        valid_years_list.append(str(initial_year))
        initial_year += 1
    return valid_years_list

def looped_output(data):
    while 1 > 0:
        user_input_1 = input("Enter starting year of interest (-1 to quit):")
        if user_input_1 == "-1":
            sys.exit()
        elif user_input_1 not in valid_years_growth():
            print("Valid years are 1960 - 2015")
            looped_output(data)
        else:
            user_input_2 = input("Enter ending year of interest (-1 to quit):")
            if user_input_2 == "-1":
                sys.exit()
            elif user_input_2 not in valid_years_growth():
                print("Valid years are 1960 to 2015")
                looped_output(data)
            else:
                user_input_region = input("Enter region (type 'all' to consider all):")
                if user_input_region == "-1":
                    sys.exit()
                elif user_input_region not in valid_regions(data):
                    print(user_input_region, "is not a valid region")
                    looped_output(data)
                else:
                    user_input_income = input("Enter income category (type 'all' to consider all):")
                    if user_input_income == "-1":
                        sys.exit()
                    elif user_input_income not in valid_incomes(data):
                        print(user_input_income, "is not a valid income")
                        looped_output(data)
                    else:
                        filtered_data = complete_filter(data, user_input_region, user_input_income)
                        sorted_list = sorted_growth_data(filtered_data, user_input_1, user_input_2)
                        print("")
                        print("Top 10 Life Expectancy Growth:", user_input_1, "to", user_input_2)
                        top_10_growth(sorted_list)
                        print("")
                        print("Bottom 10 Life Expectancy Growth", user_input_1, "to", user_input_2)
                        bottom_10_growth(sorted_list)
                        print("")
                        looped_output(data)

def main():
    filename = "worldbank_life_expectancy"
    data = read_data(filename)
    looped_output(data)

if __name__ == '__main__':
    main()

