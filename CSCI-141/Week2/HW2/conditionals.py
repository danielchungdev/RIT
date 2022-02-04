'''
Daniel Chung
'''

'''
Determines if number a is divisible by number b.
'''
def divisible(a, b):
    if a < 0 or b <0:
        print("Inputs must be positive integers!")
    elif a == b:
        print("Those integers are equal!")
    elif a > b and a % b == 0:
        print(a,"is evenly divisible by",b)
    elif b > a and b % a == 0:
        print(b,"is evenly divisible by",a)
    elif a > b and a % b !=0:
        print(a,"is not evenly divisible by",b)
    elif b > a and b % a !=0:
        print(b,"is not evenly divisible by",a)
'''
Determines if number a^2 is equal to b.
'''
def squared(a, b):
    if a*a == b:
        print(a,"squared IS",b)
    else:
        print(a,"squared IS NOT",b)
'''
Runs several tests into the divisible function.
'''
def test_divisible():
    divisible(-4,5)
    divisible(4,-5)
    divisible(5,5)
    divisible(5,3)
    divisible(3,10)
    divisible(0.5,60)
    divisible(0,0)
    divisible(10,2)
'''
Runs several tests into the squared function.
'''
def test_squared():
    squared(2,4)
    squared(4,9)
    squared(2.5,6.25)
    squared(4,2)
    squared(9,81)
    squared(25,5)
    squared(8,64)
    squared(3,9)
    squared(6,9)
    squared(9,2)
'''
Puts the whole code into a single function.
'''
def main():
    test_divisible()
    test_squared()

main()