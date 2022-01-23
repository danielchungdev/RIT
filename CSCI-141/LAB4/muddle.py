'''
File: muddle.py
Author: Daniel Chung
Date: 10/07/2021
Class: CSCI-141
'''

def muddle(txt_string):
    new_string = ""
    for txt_letter in txt_string:
        ascii_value = ord(txt_letter)
        ascii_value = ascii_value + 10
        letter = chr(ascii_value)
        new_string = new_string + letter    
    return new_string

def clarify(mud_string):
    new_string = ""
    for mud_letter in mud_string:
        ascii_value = ord(mud_letter)
        ascii_value = ascii_value - 10
        letter = chr(ascii_value)
        new_string = new_string + letter
    return new_string

def ask_new_file(file_name, ending, string):
    f = None
    muddle_string = None

    user_answer = input("Enter ’y’ to write results to file hello.mud: ")

    if ending == ".txt":
        muddle_string = muddle(string)
    if ending == ".mud":
        muddle_string = clarify(string)

    if user_answer[0].upper() == "Y":
        if ending == ".txt":
            f = open(file_name[:-4] + ".mud", "w")
            f.write(muddle_string)
            f.close()
        else:
            f = open(file_name[:-4] + "2.txt", "w")
            f.write(muddle_string)
            f.close()
    print(muddle_string)

def check_file_suffix(file_name):
    if file_name[-4:] == ".txt":
        opened_file = open(file_name)
        string_to_muddle = opened_file.readline().rstrip()
        ask_new_file(file_name, ".txt", string_to_muddle)
    elif file_name[-4:] == ".mud":
        opened_file = open(file_name)
        string_to_muddle = opened_file.readline().rstrip()
        ask_new_file(file_name, ".mud", string_to_muddle)
    else:
        print("File name must have ’.txt’ or ’.mud’ suffix")

def main():
    user_file_name = input("Enter file name: ")
    if user_file_name == "":
        return
    check_file_suffix(user_file_name)

if __name__ == "__main__":
    main()