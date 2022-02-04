"""
Daniel Chung
"""
def match(string1, string2):
    best_length = 0
    # for all possible string1 start points
    for idx1 in range(len(string1)-1):
        # for all possible string2 start points
        for idx2 in range(len(string2)-1):
            # check if these characters match
            if string1[idx1] == string2[idx2]:
                this_match_count = 1
                # see how long the match continues
                while string1[idx1 + this_match_count] == string2[idx2 + this_match_count]:
                    this_match_count += 1
                    """ Had to add this into the code, because my test of \"aab\", \"aaab\" would not work
                    So this makes sure that even if the first one is small than the second one it will
                    still return the correct value."""
                    word1 = len(string1)
                    word2 = len(string2)

                if word1 < word2:
                    return this_match_count


                # compare to best so far
                if this_match_count > best_length:
                    best_length = this_match_count

    # now return the result
    return best_length

def test_match():
    print(match("established", "ballistic"))
    print(match("aab", "aaab"))
    print(match("that", "the"))
    print(match("them", ""))

def main():
    test_match()

main()