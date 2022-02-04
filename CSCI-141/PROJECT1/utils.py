"""
CSCI - 141
CS1 - Project 1
Name: Daniel Chung
Task 0: Utils
Date: 11/18/17
"""

import sys
from rit_lib import *

Countries = struct_type("Countries",
                        (str, "name"),
                        (str, "code"),
                        (str, "region"),
                        (str,"income_group"),
                        (list, "life_expectancy"))

CountryValue = struct_type("CountryValue",
                           (str, "country"),
                           (float, "value"))

Range = struct_type("Range",
                    (str, "country"),
                    (int, "year1"),
                    (int, "year2"),
                    (float, "value1"),
                    (float, "value2"))

MedianSort = struct_type("MedianSort",
                     (int, "year"),
                     (list, "list"))

Median = struct_type("Median",
                     (int, "year"),
                     (float, "median"))


def read_data(filename):
    """
    Reads the data from both files,
    the data and the metadata.
    It takes every individual value
    from the files and adds it to a
    dictionary with values of a
    rit_lib structure.
    :param filename:
    :return a dictionary with all the country codes as keys.:
    """
    data = open("data/" + filename + "_data.txt")
    metadata = open("data/" + filename + "_metadata.txt")
    data.readline()
    metadata.readline()
    countries_dict = {}
    for line in data:
        life_expectancy = []
        line = line.strip()
        line = line.split(',')
        for i in range(2, 58):
            life_expectancy.append((line[i]))
        countries_dict[line[1]] = Countries(line[0], line[1], "", "", life_expectancy)
    for key in metadata:
        key = key.strip()
        key = key.split(',')
        countries_dict[key[0]].region = key[1]
        countries_dict[key[0]].income_group = key[2]
    return countries_dict

def valid_regions(data):
    """
    This is a helper function for the
    filter region function. This function
    checks for all the valid regions
    that the user can input.
    :param data:
    :return a list with all the regions:
    """
    valid_regions_list = []
    for key in data:
        if data[key].region not in valid_regions_list:
            valid_regions_list.append(data[key].region)
    if '' in valid_regions_list:
        valid_regions_list.remove('')
    valid_regions_list.append("all")
    return valid_regions_list

def filter_region(data, region):
    """
    This function, makes a dictionary with all
    the keys (with values) who's values contain
    the specified region.
    :param data:
    :param region:
    :return a count of the total and a dictionary with the regions:
    """
    region_dict = {}
    count = 0
    if region == "all":
        return data
    elif region not in valid_regions(data):
        return None
    else:
        for code in data:
            if data[code].region == region:
                region_dict[data[code].code] = data[code]
                count += 1
        return region_dict

def region_count(data, region):
    """
    This function, makes a dictionary with all
    the keys (with values) who's values contain
    the specified region.
    :param data:
    :param region:
    :return a count of the total and a dictionary with the regions:
    """
    region_dict = {}
    count = 0
    if region == "all":
        return data
    elif region not in valid_regions(data):
        return None
    else:
        for code in data:
            if data[code].region == region:
                region_dict[data[code].code] = data[code]
                count += 1
        return count

def valid_incomes(data):
    """
    Helper function to look for all
    the available incomes that the
    user can input.
    :param data:
    :return a list with all possible incomes:
    """
    valid_income_list = []
    for key in data:
        if data[key].income_group not in valid_income_list:
            valid_income_list.append(data[key].income_group)
    if '' in valid_income_list:
        valid_income_list.remove('')
    valid_income_list.append("all")
    return valid_income_list

def filter_income(data, income):
    """
    This function filters out the data
    by income. It creates a dictionary
    with only the countries that
    contain a certain income.
    :param data:
    :param income:
    :return a total of countries and a dictionary with the incomes:
    """
    income_dict = {}
    count = 0
    if income == "all":
        return data
    elif income not in valid_incomes(data):
        print(income, "is not a valid income")
        pass
    else:
        for code in data:
            if data[code].income_group == income:
                income_dict[data[code].code] = data[code]
                count += 1
        return income_dict

def income_count(data, income):
    """
    This function filters out the data
    by income. It creates a dictionary
    with only the countries that
    contain a certain income.
    :param data:
    :param income:
    :return a total of countries and a dictionary with the incomes:
    """
    income_dict = {}
    count = 0
    if income == "all":
        return data
    elif income not in valid_incomes(data):
        print(income, "is not a valid income")
        pass
    else:
        for code in data:
            if data[code].income_group == income:
                income_dict[data[code].code] = data[code]
                count += 1
        return count

def total_regions(data):
    """
    This function calculates the
    total amount of entities found
    in the file.
    :param data:
    :return The total amount of entities:
    """
    count = 0
    for i in data:
        count += 1
    return count

def number_of_countries(data):
    """
    This function calculates the total
    count of countries in the given file.
    :param data:
    :return the amount of countries:
    """
    count = 0
    for key in data:
        if data[key].income_group == '':
            count += 1
    return_value = total_regions(data) - count
    return return_value

def count_regions(data):
    """
    This function calculates the total
    count of countries for every single
    region group.
    :param data:
    :return printed sequence with the total amount of countries in each region:
    """
    region_list = valid_regions(data)
    for i in range(len(region_list) - 1):
        print(region_list[i], ":", region_count(data, region_list[i]))

def count_incomes(data):
    """
    This function calculates the total count
    of countries for every single income group.
    :param data:
    :return printed sequence with the total amounts of countries in each income group:
    """
    income_list = valid_incomes(data)
    for i in range(len(income_list) - 1):
        print(income_list[i], ":", income_count(data, income_list[i]))

def countries_in(data, region):
    """
    This function takes the filter income
    and prints the name with the code in
    parenthesis.
    :param data:
    :param region:
    :return printed output:
    """
    dictionary = filter_region(data, region)
    for key in dictionary:
        print(dictionary[key].name, "(", dictionary[key].code, ")")

def income_category(data, income):
    """
    This function takes the filter income
    and prints the name with the code
    in parenthesis.
    :param data:
    :param income:
    :return printed output.:
    """
    dictionary = filter_income(data, income)
    for key in dictionary:
        print(dictionary[key].name, "(", dictionary[key].code, ")")

def user_region(data, region):
    """
    This function checks for the region that
    the user was asked to input, if it's not
    valid it will print it's appropriate message.
    :param data:
    :param region:
    :return the printed region countries.:
    """
    if region not in valid_regions(data):
        print("Not a valid region")
        pass
    else:
        print("Countries in the", region, "region:")
        countries_in(data, region)

def user_income(data, income):
    """
    Checks for the income that the user
    input, if it's not in the valid
    incomes it will print the appropriate
    message.
    :param data:
    :param income:
    :return the printed income countries:
    """
    if income not in valid_incomes(data):
        print("Not a valid income")
        pass
    else:
        print("Countries in the", income, "income category:")
        income_category(data, income)

def countries_codes(data):
    """
    Returns a dictionary with the country name
    as a key and the country code as a value.
    :param data:
    :return a dictionary with names as keys:
    """
    countries_codes_dict = {}
    for i in data:
        countries_codes_dict[data[i].name] = data[i].code
    return countries_codes_dict

def all_countries_codes(data):
    """
    This is a helper function that makes a list
    of every single country code in the file.
    :param data:
    :return a list of all country codes.:
    """
    countries_codes = []
    count = 0
    for i in data:
        if data[i].income_group == '':
            count += 1
        else:
            countries_codes.append(i)
    return countries_codes

def all_countries_names(data):
    """
    This is a helper function that makes a list
    of every single country in the file.
    :param data:
    :return a list of all countries:
    """
    countries_list = []
    count = 0
    for i in data:
        if data[i].income_group == '':
            count += 1
        else:
            countries_list.append(data[i].name)
    return countries_list

def year_and_life_expectancy(data, code):
    """
    This function prints the format for the year and
    the life expectancy of that year. This file checks
    for both, if its a code, or if its a name of a
    country or if it's a code.
    :param data:
    :param code:
    :return the printed sequence:
    """
    if str(code) in all_countries_codes(data) or all_countries_names(data):
        if len(code) > 3:
            if code in countries_codes(data):
                code = countries_codes(data)[code]
                country_life_expectancy = data[code].life_expectancy
                initial_year = 1959
                for i in country_life_expectancy:
                    if i == '':
                        initial_year += 1
                    else:
                        initial_year += 1
                        print("Year:", initial_year, "   ", "Life expectancy:", i)
        else:
            if code not in all_countries_codes(data):
                print(code, "is not a valid code.")
                pass
            else:
                country_life_expectancy = data[code].life_expectancy
                initial_year = 1959
                for i in country_life_expectancy:
                    if i == '':
                        initial_year += 1
                    else:
                        initial_year += 1
                        print("Year:", initial_year, "   " ,"Life expectancy:", i)
    else:
        print(code, "is not a valid country code or country name")
        pass

def filename():
    """
    This function is the function that
    reads the data.
    :return:
    """
    filename = "worldbank_life_expectancy"
    data = read_data(filename)
    return data

def complete_filter(data, region, income):
    """
    This function makes a dictionary
    filtered with both, region and income.
    :param data:
    :param region:
    :param income:
    :return:
    """
    complete_dict = {}
    filter_1_data = filter_region(data, region)
    filter_2_data = filter_income(data, income)
    for i in filter_1_data:
        if i in filter_2_data:
            complete_dict[i] = data[i]
    if complete_dict == {}:
        return None
    else:
        return complete_dict

def filter_only_countries(data):
    """
    This function returns a dictionary
    with only the countries in it.
    :param data:
    :return:
    """
    usable_dict = {}
    for i in data:
        if data[i].region == '':
            pass
        else:
            usable_dict[i] = data[i]
    return usable_dict

def str_into_float(a_list):
    """
    This function takes a list of
    strings and turns it into a
    list of floats.
    :param a_list:
    :return a list of floats:
    """
    float_list = []
    for i in a_list:
        if i == '':
            pass
        else:
            i = float(i)
            float_list.append(i)
    return float_list

def str_into_0(a_list):
    """
    Makes all the "" into 0.0
    :param a_list:
    :return:
    """
    float_list = []
    for i in a_list:
        if i== '':
            float_list.append(0.0)
        else:
            i = float(i)
            float_list.append(i)
    return float_list

def main():
    """
    This is the main function that
    calls the whole code and
    structures it the way that it's
    supposed to look like.
    :return correctly printed sequence:
    """
    data = filename()
    print("Total number of entities:", total_regions(data))
    print("Number of countries/territories:", number_of_countries(data))
    print("")
    print("Regions and their country count:")
    count_regions(data)
    print("")
    print("Income categories and their country count:")
    count_incomes(data)
    print("")
    print(valid_regions(data))
    user_input_region = input("Enter a region name:")
    user_region(data, user_input_region)
    print("")
    user_input_income = input("Enter income category:")
    user_income(data, user_input_income)
    print("")
    user_input_life = input("Enter a name of country or country code (Enter to quit):")
    print("Data for", user_input_life)
    while user_input_life != "":
        year_and_life_expectancy(data, user_input_life)
        print("")
        user_input_life = input("Enter a name of country or country code(Enter to quit):")
        if user_input_life == '':
            sys.exit()
        else:
            print("Data for", user_input_life)


if __name__ == '__main__':
    main()