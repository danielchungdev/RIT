
def determine_delay(time_mins):
    time_mins = float(time_mins)
    if time_mins <= -10:
        return "very-early"
    if time_mins > -10 and time_mins <= -5:
        return "early"
    if time_mins > -5 and time_mins <= 5:
        return "on-time"
    if time_mins > 5 and time_mins <= 10:
        return "late"
    else:
        return "very-late"

def determine_day(day):
    day = int(day)
    if day >= 1 and day < 5:
        return "Weekday"
    else:
        return "Weekend" 

def determine_season(num):
    num = int(num)
    if num > 2 and num < 6:
        return "Spring"
    if num > 5 and num < 9: 
        return "Summer"
    if num > 8 and num < 12:
        return "Fall"
    else:
        return "Winter"

def array_contains_empty(data_arr):
    for i in data_arr:
        if i == "":
            return True
    return False

def read_file(in_file, out_file):

    headers = "airline,day_type,season,origin,destination,dep_delay,arr_delay"

    allowed_airlines = [
        'United Air Lines Inc.', 
        'Delta Air Lines Inc.', 
        'American Airlines Inc.', 
        'JetBlue Airways'
    ]

    out_file.write(headers + "\n")
    n = 0

    for i in in_file:
        data = i.split(',')
        if not array_contains_empty(data):
            airline = data[1]
            season = determine_season(data[18])
            day = determine_day(data[20])
            dep_delay = determine_delay(data[9])
            arr_delay = determine_delay(data[55])
            or_airport = data[2]
            dest_airport = data[3]
            if airline in allowed_airlines and n % 3 == 0:
                out_file.write(airline + "," + day + "," + season + "," + or_airport + "," + dest_airport + "," + dep_delay + "," + arr_delay +"\n")
    n += 1
                
def main():
    f2020 = open('./input/Combined_Flights_2021.csv')
    f2020_clean = open('./output/Clean_2021.csv' ,'w+')

    read_file(f2020, f2020_clean)
    
    f2020.close()
    f2020_clean.close()

main()