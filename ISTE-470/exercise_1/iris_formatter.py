'''
Author: Daniel Chung
Class: ISTE - 470
Date: Aug 29th 2022.
'''

import sys
import re
import time

def check_output_file(output_file) -> bool:
    output_file = output_file.split(".")
    if len(output_file) == 2:
        if output_file[1] == "arff":
            return True
    return False

def validate_params(params) -> bool:
    if len(params) < 3:
        print("Usage: python3 iris_formatter.py infile_name outfile_name")
        return False
    else: 
        return True

def check_valid_file(file_name) -> bool:
    file_extension = file_name.split(".")
    if len(file_extension) != 2:
        return False
    else:
        return True

def check_file_format(file_name) -> str:
    if check_valid_file(file_name):
        file_extension = file_name.split(".")[1].lower()
        if file_extension == "arff":
            return "arff" 
        elif file_extension == "csv":
            return "csv"
        else:
            print("{} extension is not valid".format(file_extension))
            return "error_file"
    else:
        print("This is an invalid file name")
        return "error_invalid"

def replace_line(line, word) -> str:
    return re.sub(r".$", word, line)

def read_csv(input_file_name) -> list:
    f = open(input_file_name, "r")
    return_list = []
    for line in f:
        last_char = line.strip()[-1]
        line = line.strip()
        if last_char == "1":
            line = replace_line(line, "Iris-setosa")
            return_list.append(line)
        elif last_char == "2":
            line = replace_line(line, "Iris-versicolor")
            return_list.append(line)
        else:
            line = replace_line(line, "Iris-virginica")
            return_list.append(line)
    return return_list

def read_arff(input_file_name) -> list:
    f = open(input_file_name, "r")
    return_list = []
    start_flag = False
    for line in f: 
        line = line.strip()
        print(line)
        if start_flag == True:
            return_list.append(line)
        if line == "@DATA":
            start_flag = True
    return return_list

def write_data(input_file_name, output_file_name, data_list) -> None:
    if check_output_file(output_file_name) == False:
        output_file_name += ".arff"
    f = open(output_file_name, "w")
    f.write("@RELATION iris\n")
    f.write("\n")
    f.write("@ATTRIBUTE sepallength \tREAL\n")
    f.write("@ATTRIBUTE sepalwidth \tREAL\n")
    f.write("@ATTRIBUTE petallength \tREAL\n")
    f.write("@ATTRIBUTE petalwidth \tREAL\n")
    f.write("@ATTRIBUTE class \t{Iris-setosa,Iris-versicolor,Iris-virginica}\n")
    f.write("\n")
    f.write("@DATA\n")
    for line in data_list:
        f.write(line + "\n")
    f.close()
    print("Successfully converted {} -> {}.".format(input_file_name, output_file_name))

def main() -> None:
    start_time = time.time()
    args = sys.argv
    if validate_params(args):
        input_file = args[1]
        output_file = args[2]
        print("Starting conversion {} -> {}".format(input_file, output_file))
        file_format = check_file_format(input_file)
        data_list = None
        if file_format == "csv":
            data_list = read_csv(input_file)
        elif file_format == "arff":
            data_list = read_arff(input_file)
        else:
            sys.exit(1)
        write_data(input_file, output_file, data_list)
        print(" ---- in {} seconds ----".format(time.time() - start_time))
        sys.exit(0)
    else:
        sys.exit(1)

if __name__ == "__main__":
    main()