"""
CSCI - 141
CS1 - Project 1
Name: Daniel Chung
Task 3: Drop
Date: 11/18/17
"""

from utils import *

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

def partition(pivot, alist):
    """
    Function necessary for quicksort
    :param pivot:
    :param alist:
    :return 3 lists.:
    """
    (less, same, more) = ([], [], [])
    for i in alist:
        if i.value > pivot:
            more.append(i)
        elif i.value < pivot:
            less.append(i)
        else:
            same.append(i)
    return (more, same, less)

def filtered_data(data):
    """
    This function makes sure to
    filter the data, for it to
    only take into consideration
    those countries that have
    at least 2 life expectancy
    data.
    :param data:
    :return filtered data part 1:
    """
    new_data = {}
    for i in data:
        life_exp_list = data[i].life_expectancy
        life_exp_list = str_into_float(life_exp_list)
        if len(life_exp_list) < 2:
            pass
        else:
            new_data[i] = data[i]
    return new_data

def finding_year(str_list, value):
    """
    This function is used to
    find the year of a specific
    value.
    :param str_list:
    :param value:
    :return a year:
    """
    current_year = 1960
    if value == 0:
        return 0
    else:
        for i in str_list:
            if i == str(value):
                return current_year
            else:
                current_year += 1

def search_for_drop(a_list):
    """
    This function looks for
    the biggest possible drop.
    In case that there is no
    drop then it takes the
    smallest positive number.
    :param a_list:
    :return drop_values [total, value1, value2]:
    """
    drop_values = []
    biggest_drop = 100
    value1_top = 0
    value2_top = 0
    for i in range(len(a_list)):
        value1 = a_list[i]
        for e in range(len(a_list)):
            value2 = a_list[e]
            new_drop = value2 - value1
            if new_drop < biggest_drop and e > i:
                biggest_drop = new_drop
                value1_top = value1
                value2_top = value2
    drop_values.append(biggest_drop)
    drop_values.append(value1_top)
    drop_values.append(value2_top)
    return drop_values


def drop_sort(data):
    """
    This function takes the data and
    filters it, then it proceeds to
    take one of the lits and create
    the data structure for Range.
    :param data:
    :return unsorted Range data structures:
    """
    data_to_sort = []
    this_data = filter_only_countries(data)
    new_data = filtered_data(this_data)
    for i in new_data:
        current_lifexp = new_data[i].life_expectancy
        a_list =  str_into_float(current_lifexp)
        drops = search_for_drop(a_list)
        year1 = finding_year(current_lifexp, drops[1])
        year2 = finding_year(current_lifexp, drops[2])
        data_struct = Range(new_data[i].name, int(year1), int(year2), float(drops[1]), float(drops[2]))
        data_to_sort.append(data_struct)
    return data_to_sort

def sorting(data_to_sort):
    """
    This function puts the country
    and the value into the CountryValue
    structure. Then proceeds to append it
    to a list, and then sorts it with my
    implemented way of quicksort.
    :param data_to_sort:
    :return sorted list of CountryValue functions:
    """
    sorted_list = []
    for i in data_to_sort:
        value1 = i.value1
        value2 = i.value2
        value = value2 - value1
        sorted_list.append(CountryValue(i.country, value))
        sorted_list = quicksort(sorted_list)
    return sorted_list

def sorting_list_of_data(data_to_sort, sorted_list):
    """
    This function takes the previous function,
    and takes the unsorted data list with
    range functions and orders it in the exact
    same way that sorted list is sorted, therefor
    making the unsorted list sorted.
    :param data_to_sort:
    :param sorted_list:
    :return complete sorted list of Range Values:
    """
    completed_sort = []
    for i in sorted_list:
        for e in data_to_sort:
            if i.country == e.country:
                completed_sort.append(e)
    return completed_sort

def sorted_list_values(data):
    """
    This function is used only for the
    standalone of the program. It just
    makes sure to return the filtered
    sorted list of values.
    :param data:
    :return sorted list of CountryValue structures:
    """
    data_to_sort = drop_sort(data)
    sorted_list = sorting(data_to_sort)
    return sorted_list

def sorted_drop_data(data):
    """
    This function is used for the standalone
    part of the program. It just makes sure
    to return the filtered sorted list with
    Ranged structures.
    :param data:
    :return Sorted list of Ranged structures:
    """
    data_to_sort = drop_sort(data)
    sorted_list = sorting(data_to_sort)
    finished_sort = sorting_list_of_data(data_to_sort, sorted_list)
    return finished_sort

def main():
    filename = "worldbank_life_expectancy"
    data = read_data(filename)
    sorted_data = sorted_drop_data(data)
    sorted_list = sorted_list_values(data)
    print("Worst life expectancy drops: 1960 to 2015")
    number = 0
    count = 1
    while count != 11:
        print(count, ":", sorted_data[number].country, "from", sorted_data[number].year1, "(",
              sorted_data[number].value1, ")", "to",
              sorted_data[number].year2, "(", sorted_data[number].value2, "):", sorted_list[number].value)
        number += 1
        count += 1

if __name__ == '__main__':
    main()