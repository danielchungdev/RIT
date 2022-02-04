"""
Daniel Chung
"""

"""
Controls in a single line the amount of shifts
that the encryption should do.
"""
def control_shift():
    return 11
"""
This function is what encrypts the words in the file
"""
def muddle(file):
    encryption = ""

    for letter in file:
        word = ord(letter) + control_shift()
        encryption = encryption + chr(word)

    return encryption
"""
This functions decrypts the words in the encrypted message
"""
def clarify(file):
    encryption = ""

    for letter in file:
        word = ord(letter) - control_shift()
        encryption = encryption + chr(word)

    return encryption
"""
This file asks if its a .txt or a .mud and depending on the extension 
of the file, it will encrypt it or decrypt it. Also if the user wishes to 
save it in a file, it will save the encryption or decryption in a new file with .txt or with 
.mud
"""
def encryption_and_decryption():
    file = input("Select a file")

    if file == "":
        quit()


    else:

        if file[-4:] == ".txt":

            answer = input("Enter 'y' to write results to file")
            if answer[0] == "y" or answer[0] == "Y":
                openedfile = open(file, "r+", encoding='utf-8')
                newfile = open("2.mud", "w+", encoding='utf-8')

                for line in openedfile:
                    newwords = muddle(line)
                    newfile.write(newwords)
                newfile.close()

            else:
                openedfile = open(file, "r+", encoding='utf-8')
                return print(muddle(openedfile.read()))
                openedfile.close()

        elif file[-4:] == ".mud":

            answer = input("Enter 'y' to write results to file")
            if answer[0] == "y" or answer[0] == "Y":
                openedfile = open(file, "r+", encoding='utf-8')
                newfile = open("2.txt", "w+", encoding='utf-8')

                for line in openedfile:
                    newwords = clarify(line)
                    newfile.write(newwords)
                newfile.close()
            else:
                openedfile = open(file, "r+", encoding='utf-8')
                return print(clarify(openedfile.read()))
                openedfile.close()

        else:
            print("File can't be read")
            quit()
"""
Calls the whole code
"""
def main():
    encryption_and_decryption()

main()