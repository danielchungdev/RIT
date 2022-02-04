"""
Daniel Chung
"""
def caesar(original, codeword):
    """This gets the shift that should always be 5. So if something is a different shift than 5
    it means that is wrong and it will just jump straight to return negative 1. So that is basically what we are checking"""
    shift = ord(codeword[0]) - ord(original[0])
    currentletter = 0
    while currentletter < len(original):
        if ord(codeword[currentletter]) - ord(original[currentletter]) == shift or ord(codeword[currentletter]) - ord(original[currentletter]) == (shift + 26):
            if currentletter == (len(original) - 1):
                if shift < 0:
                    print(shift + 26)
                else:
                    print(shift)
            currentletter = (currentletter + 1)
        else:
            return print(-1)


def test_caesar():
    print("VERY")
    caesar("VERY", "AJWD")
    caesar("VERY", "KJWD")
    caesar("VERY", "ATWD")
    caesar("VERY", "AJSD")
    caesar("VERY", "AJWN")
    caesar("VERY", "WADF")
    caesar("VERY", "WADF")
    caesar("VERY", "AJwD")
    print("HAPPY")
    "Error here, don't really get why. Tried printing and debugging but can't get another word to work"
    caesar("HAPPY", "MFUUD")
    caesar("HAPPY", "MQUUD")
    caesar("HAPPY", "MFWUD")
    caesar("HAPPY", "MFUYD")
    caesar("HAPPY", "MFUUJ")
    caesar("HAPPY", "MFAD")
    print("SAD")
    caesar("SAD", "XFI")
    caesar("SAD", "XKI")
    caesar("SAD", "XFP")

def main():
    test_caesar()

main()
