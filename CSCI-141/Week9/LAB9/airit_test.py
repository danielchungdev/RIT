from airit_simulation import *

def test_read_passenger():
    """
    THis function should return a passengers tickets.
    :return:
    """
    print(read_passenger("passengers_very_small.txt"))
    print(read_passenger("passengers_small.txt"))

def test_passenger_gate():
    """
    This function should return the gate with the
    passengers in their respective lines.
    :return:
    """
    passengers_1 = read_passenger("passengers_very_small.txt")
    passengers_2 = read_passenger("passengers_small.txt")
    gate1 = Gate(mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), 5, 0)
    gate2 = Gate(mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), 10, 0)
    print(passenger_gate(gate1, passengers_1))
    print(passenger_gate(gate2, passengers_2))

def test_passenger_plane():
    """
    This test function should return a plane, with the 2 stacks.
    :return:
    """
    passengers_2 = read_passenger("passengers_small.txt")
    gate2 = Gate(mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), 10, 0)
    gate2 = passenger_gate(gate2, passengers_2)
    print(passenger_plane(gate2, passengers_2))

def test_airplane_empty():
    """
    This function makes sure to return
    an empty plane.

    :return:
    """
    passengers_1 = read_passenger("passengers_very_small.txt")
    passengers_2 = read_passenger("passengers_small.txt")
    passengers_3 = read_passenger("passengers_large.txt")
    gate = Gate(mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), 5, 0)
    gate1 = passenger_gate(gate, passengers_1)
    gate2 = passenger_gate(gate, passengers_2)
    plane10 = Airplane(mkEmptyStack(), mkEmptyStack(), 4, 0)
    plane20 = Airplane(mkEmptyStack(), mkEmptyStack(), 10, 0)
    plane1= passenger_plane(gate1, plane10)
    plane2= passenger_plane(gate2, plane20)
    plane3 = passenger_plane(gate3, plane30)
    print(airplane_empty(plane1))
    print(airplane_empty(plane2))

def test_main_loop():
    file1 = "passengers_very_small.txt"
    file2 = "passengers_small.txt"
    main_loop(file1, 10, 9)
    main_loop(file2, -3, 10)

def test_whole_function():
    passengers_1 = read_passenger("passengers_very_small.txt")
    passengers_2 = read_passenger("passengers_small.txt")
    gate = Gate(mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), 5, 0)
    airplane = Airplane(mkEmptyStack()), mkEmptyStack(), 10, 0
    whole_function(gate, passengers_1, airplane)
    whole_function(gate, passengers_2, airplane)

def main():
    print("Testing Read_passengers...")
    test_read_passenger()
    print("Test complete...")
    print("Testing passenger_gate...")
    test_passenger_gate()
    print("Test complete...")
    print("Testing passenger_plane...")
    test_passenger_plane()
    print("Test complete...")
    print("Test_airplane_empty...")
    test_airplane_empty()
    print("Test complete...")
    print("Testing Main loop...")
    test_main_loop()
    print("Test complete...")
    print("Testing whole function...")
    test_whole_function()
    print("Test complete...")

main()