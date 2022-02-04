from rit_lib import *
from myStack import *
from myQueue import *

Student = struct_type("Student", (str, "name"), (str, "ticket"), (bool, "carry_on"))
Gate = struct_type("Gate", (Queue, "line1"), (Queue, "line2"), (Queue, "line3"), (Queue, "line4"), (int, "max"), (int, "current"))
Airplane = struct_type("Airplane", (Stack, "carry_on"), (Stack, "no_carry_on"), (int, "max"), (int, "current"))

def read_passenger(a_file):
    """
    This function creates a queue with
    all the students in a structure.
    :param a_file:
    :return a passengers queue:
    """
    opened = open(a_file)
    passengers = mkEmptyQueue()
    for line in opened:
        line = line.strip()
        line = line.split(",")
        student = Student(line[0], line[1], line[2] == "True")
        enqueue(passengers, student)
    return passengers

def passenger_gate(gate, passenger):
    """
    This function makes sure that every
    passenger gets to the correct gate
    line.
    :param gate:
    :param passenger:
    :return The gate with passengers in the correct line.:
    """
    current = front(passenger)
    if current.ticket[0] == "1":
        print("  ", current)
        enqueue(gate.line1, current)
        dequeue(passenger)
        gate.current += 1
    elif current.ticket[0] == "2":
        print("  ", current)
        enqueue(gate.line2, current)
        dequeue(passenger)
        gate.current += 1
    elif current.ticket[0] == "3":
        print("  ", current)
        enqueue(gate.line3, current)
        dequeue(passenger)
        gate.current += 1
    elif current.ticket[0] == "4":
        print("  ", current)
        enqueue(gate.line4, current)
        dequeue(passenger)
        gate.current += 1

def passenger_plane(gate, airplane):
    """
    This function puts passengers into the plane,
    it makes sure to start from the 4th group onwards.
    :param gate:
    :param airplane:
    :return:
    """
    if gate.line4.size > 0:
        current = front(gate.line4)
        if current.carry_on == True:
            print("  ", current)
            push(airplane.carry_on, current)
            dequeue(gate.line4)
            airplane.current += 1
            gate.current -= 1
        else:
            print("  ", current)
            push(airplane.no_carry_on, current)
            dequeue(gate.line4)
            airplane.current += 1
            gate.current -= 1
    elif gate.line3.size > 0:
        current = front(gate.line3)
        if current.carry_on == True:
            print("  ", current)
            push(airplane.carry_on, current)
            dequeue(gate.line3)
            airplane.current += 1
            gate.current -= 1
        else:
            print("  ", current)
            push(airplane.no_carry_on, current)
            dequeue(gate.line3)
            airplane.current += 1
            gate.current -= 1
    elif gate.line2.size > 0:
        current = front(gate.line2)
        if current.carry_on == True:
            print("  ", current)
            push(airplane.carry_on, current)
            dequeue(gate.line2)
            airplane.current += 1
            gate.current -= 1
        else:
            print("  ", current)
            push(airplane.no_carry_on, current)
            dequeue(gate.line2)
            airplane.current += 1
            gate.current -= 1
    elif gate.line1.size > 0:
        current = front(gate.line1)
        if current.carry_on == True:
            print("  ", current)
            push(airplane.carry_on, current)
            dequeue(gate.line1)
            airplane.current += 1
            gate.current -= 1
        else:
            print("  ", current)
            push(airplane.no_carry_on, current)
            dequeue(gate.line1)
            airplane.current += 1
            gate.current -= 1
    return airplane

def airplane_empty(airplane):
    """
    This function disembarks the plane,
    taking into consideration priority is
    on those with carry ons.
    :param airplane:
    :return:
    """
    if airplane.carry_on.size > 0:
        current = top(airplane.carry_on)
        print("  ",current)
        pop(airplane.carry_on)
        airplane.current -= 1
    elif airplane.no_carry_on.size > 0:
        current = top(airplane.no_carry_on)
        print("  ",current)
        pop(airplane.no_carry_on)
        airplane.current -= 1
    return airplane

def whole_function(gate, passenger, airplane):
    """
    This function is the function that structures the whole code.
    :param gate:
    :param passenger:
    :param airplane:
    :return:
    """
    print("Passengers are lining up at the gate...")
    while gate.current < gate.max:
        if passenger.size == 0:
            print("Last passenger is in line!")
            break
        else:
            passenger_gate(gate, passenger)
    if gate.current == gate.max:
        print("Gate is full; remaining passengers must wait")
    print("Passengers are boarding the aircraft...")
    while airplane.current < airplane.max:

        passenger_plane(gate, airplane)
    print("Passengers are disembarking...")
    while airplane.current != 0:
        airplane_empty(airplane)

def main_loop(a_file, gate_size, plane_size):
    """
    This function is what keeps the loop going until there is none left
    :param a_file:
    :param gate_size:
    :param plane_size:
    :return:
    """
    if int(gate_size) <= 0 or int(plane_size) <= 0:
        return print("Numbers can't be 0 or negatives")
    passengers = read_passenger(a_file)
    gate = Gate(mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), mkEmptyQueue(), int(gate_size), 0)
    airplane = Airplane(mkEmptyStack(), mkEmptyStack(), int(plane_size), 0)
    while 1 > 0:
        if gate.current == 0 and passengers.size == 0:
            airplane_empty(airplane)
            break
        else:
            whole_function(gate, passengers, airplane)
    print("Simulation complete; all passengers are at their destination!")

def main():
    user_input_file = input("Passenger data file:")
    user_input_gate_size = input("Gate Capacity:")
    user_input_plane_size = input("Aircraft capacity:")
    main_loop(user_input_file, user_input_gate_size, user_input_plane_size)

if __name__ == '__main__':
    main()