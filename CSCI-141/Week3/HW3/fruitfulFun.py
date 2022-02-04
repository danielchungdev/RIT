'''
Daniel Chung
'''
'''
-----------------------------------------------------------------------------TASK 1--------------------------------------------------------------------------------------
'''
'''
Function for squares
'''
def sumSquare(n):
    if n == 0:
        return 0
    else:
        return n**2 + sumSquare(n - 1)
'''
sumsquare but this one uses tail recursion
'''
def sumsquareaccum(n, accumulation):
    if n == 0:
        return 0 + accumulation
    else:
        return sumsquareaccum(n - 1, n**2 + accumulation)
'''
Calls back sumSquareAccum but with only one variable.
'''
def sumsquareTR(n):
    return sumsquareaccum(n, 0)
'''
Testing the 3. sumsquare, sumsquareaccum, sumsquareTR.
'''
def sumSquaresTest():
    print("Sum of square Test")
    print(sumSquare(0)), print(sumsquareTR(0))
    print(sumSquare(1)), print(sumsquareTR(1))
    print(sumSquare(2)), print(sumsquareTR(2))
    print(sumSquare(3)), print(sumsquareTR(3))
    print(sumSquare(4)), print(sumsquareTR(4))
    print(sumSquare(5)), print(sumsquareTR(5))
    print(sumSquare(6)), print(sumsquareTR(6))
'''
----------------------------------------------------------------------------TASK 2---------------------------------------------------------------------------------------
'''
'''
Main recursive function for stairs.
'''
def stairclimb(n):
    if n == 1:
        return 1
    if n == 2:
        return 2
    if n == 3:
        return 4
    else:
        return stairclimb(n-1)+stairclimb(n-2)+stairclimb(n-3)
'''
Tail recursive form of stairs.
'''
def stairclimbAccum(n, a, b, c):
    if n == 1:
         return a
    if n == 2:
        return b
    if n == 3:
        return c
    else:
        return stairclimbAccum((n - 1), b, c, (a + b + c))

def StairclimbAccumTR(n):
    return stairclimbAccum(n, 1, 2, 4)

def stairclimbTest():
    print("Stairs Climbing Test")
    print(StairclimbAccumTR(4)), print(stairclimb(4))
    print(StairclimbAccumTR(5)), print(stairclimb(5))
    print(StairclimbAccumTR(6)), print(stairclimb(6))
    print(StairclimbAccumTR(7)), print(stairclimb(7))
    print(StairclimbAccumTR(8)), print(stairclimb(8))
    print(StairclimbAccumTR(9)), print(stairclimb(9))

'''
Calls the whole code.
'''
def main():
    sumSquaresTest()
    stairclimbTest()


main()