'''
@Author: Daniel Chung
@Class: ISTE-470
@Date: Oct. 16, 2022
'''

header = """@relation pima_diabetes
@attribute 'preg' numeric
@attribute 'plas' numeric
@attribute 'pres' { low, ideal, prehigh, high }
@attribute 'skin' numeric
@attribute 'insu' numeric
@attribute 'mass' { underweight, normal, overweight }
@attribute 'pedi' numeric
@attribute 'age' { young, middle, elderly }
@attribute 'class' { tested_negative, tested_positive}
@data
"""

print("starting")
f = open('diabetes.arff')
flag = False

w = open('diabetes_disc.arff', 'w')
w.write(header)
for line in f: 
    if flag:
        line = line.strip()
        data_points = line.split(",")

        if int(data_points[2]) <= 90:
            data_points[2] = "low"
        elif int(data_points[2]) > 90 and int(data_points[2]) < 120:
            data_points[2] = "ideal"
        elif int(data_points[2]) >= 120 and int(data_points[2]) < 140:
            data_points[2] = "prehigh"
        else:
            data_points[2] = "high"

        if float(data_points[5]) <= 18.5:
            data_points[5] = "underweight"
        elif float(data_points[5]) > 18.5 and float(data_points[5]) < 25:
            data_points[5] = "normal"
        else:
            data_points[5] = "overweight"

        if int(data_points[7]) <= 40:
            data_points[7] = "young"
        elif int(data_points[7]) > 40 and int(data_points[7]) < 60:
            data_points[7] = "middle"
        else:
            data_points[7] = "elderly"
        
        w.write(','.join(data_points) + '\n')
    if line.strip() == "@data":
        flag = True
w.close()