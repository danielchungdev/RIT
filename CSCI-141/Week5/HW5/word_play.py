"""
Daniel Chung
Homework 4
Wordplay
"""

"""This is the function that slices de string and checks if the sliced string is found in the txt document"""
def check_deletions(word, open_file):
    count = 0
    start = 0
    letters = len(word)
    while letters > 0:
        jump = start + 2
        """Checks if the slice goes over the lenght of the word. If it does it will just last word it found and the count"""
        if jump > len(word):
            count = count + 1
            print(str(count) + "  " + using_word)
        """Had to add a start == 0 conditions because, couldn't find a way to make the slicing just delete de first letter"""
        if start == 0:
            using_word = word[1:]
            for thisword in open_file:
                if thisword.strip() == using_word:
                    count = count + 1
                    print(str(count) + "  " + thisword.strip())
            """Slices de string into all possibilities and if its in the file it will add to the count and then print."""
        else:
            using_word = word[:start + 1] + word[jump:]
            for thisword in open_file:
                if thisword.strip() == using_word:
                    count = count + 1
                    print(str(count) + "  " + thisword.strip())
        open_file.seek(0)
        """Maker sure that the basecase eventually hits"""
        letters = letters - 1
        start = start + 1
        """Return us the total amount of words that it found"""
    return print(str(count) + "  " + "Results were found")
"""
Calls the whole function
"""
def main():
    word = input("Word you want to change")
    file = input("input a file name")
    opened = open(file)
    check_deletions(word, opened)

main()