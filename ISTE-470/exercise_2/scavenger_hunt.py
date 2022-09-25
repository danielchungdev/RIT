import matplotlib.pyplot as plt
import numpy as np
import math
import os

def read_waveforms(LA, RV, RA) :
    infile = open('waveforms.csv', 'r')

    line = infile.readline()
    wf = 0 # which waveform are we trying to read (0 = LA, 1 = RV, 2 = RA)

    while line :
        line = line.strip()
        data = line.split(',')

        for i in range(0, len(data)) : 
            data[i] = float(data[i])

        if(wf == 0) :
            LA.append(data)
        elif(wf == 1) :
            RV.append(data)
        elif(wf == 2) :
            RA.append(data)
        
        wf = (wf + 1) % 3
        line = infile.readline()

    infile.close()

def read_times(TL, TR) :
    infile = open('times.csv', 'r')
    line = infile.readline()
    data = line.strip().split(',')

    for i in range(0, len(data)) : 
        data[i] = float(data[i]) * 1000

    TL.append(data)

    line = infile.readline()
    data = line.strip().split(',')

    for i in range(0, len(data)) : 
        data[i] = float(data[i]) * 1000

    TR.append(data)
    infile.close()

def plot_waveforms(LA, RV, RA, TL, TR):
    os.makedirs('./waveforms-images', exist_ok=True)
    for i in range(0, len(LA)):
        plt.rcParams.update({'font.size': 8})

        plt.subplot( 311 )
        axes = plt.gca()
        plt.plot(TL, LA[i, :])
        axes.tick_params(direction="in", top=True, right=True)
        plt.yticks(np.arange(0, max(LA[i, :]) + 5, step=5))
        plt.xticks(np.arange(0, max(TL) + 1, step=5))
        plt.xlim([0, max(TL) + 1])
        plt.ylim([0, math.floor(max(LA[i, :]) + 5)])
        plt.title("Wave forms for Instance {}".format(i + 1))
        plt.ylabel('Lin Accel (g)')

        plt.subplot( 312 )
        axes = plt.gca()
        plt.plot(TR, RV[i, :])
        axes.tick_params(direction="in", top=True, right=True)
        plt.yticks(np.arange(0, max(RV[i, :]) + 5, step=5))
        plt.xticks(np.arange(0, max(TR) + 1, step=5))
        plt.xlim([0, max(TR) + 1])
        plt.ylim([0, math.floor(max(RV[i, :]) + 5)])
        plt.ylabel('Rot Vel (rad/sec)')

        plt.subplot( 313 )
        axes = plt.gca()
        plt.plot(TR, RA[i, :])
        axes.tick_params(direction="in", top=True, right=True)
        plt.yticks(np.arange(0, max(RA[i, :]) + 2, step=2))
        plt.xticks(np.arange(0, max(TR) + 1, step=5))
        plt.xlim([0, max(TR) + 1])
        plt.ylim([0, math.floor(max(RA[i, :]) + 2)])
        plt.ylabel('Rot Accel (rad/sec^2)')

        plt.xlabel('Time (ms)')

        plt.savefig('./waveforms-images/Instance ' + str(i + 1) + '.png')
        plt.close()
    
# make empty data and time Lists
LA_list = []
RV_list = []
RA_list = []
TL_list = []
TR_list = []

read_waveforms(LA_list, RV_list, RA_list)
read_times(TL_list, TR_list)

# convert all data and time lists to numpy arrays for plotting
LA = np.array(LA_list)
RV = np.array(RV_list)
RA = np.array(RA_list)
TL = np.array(TL_list[0])
TR = np.array(TR_list[0])

# plot_waveforms(LA, RV, RA, TL, TR)

def get_min_avg_max(data):
    minimum = []
    maximum = []
    average = []
    for waveform in data:
        wavearray = np.array(waveform)
        minimum.append(np.min(wavearray))
        maximum.append(np.max(wavearray))
        average.append(np.average(wavearray))
    return np.array(minimum), np.array(average), np.array(maximum) 

MLA, ALA, PLA = get_min_avg_max(LA)
MRV, ARV, PRV = get_min_avg_max(RV)
MRA, ARA, PRA = get_min_avg_max(RA)

print("MLA: min = {}, max = {}, avg = {}".format(np.min(MLA), np.max(MLA), np.average(MLA)))
print("ALA: min = {}, max = {}, avg = {}".format(np.min(ALA), np.max(ALA), np.average(ALA)))
print("PLA: min = {}, max = {}, avg = {}\n".format(np.min(PLA), np.max(PLA), np.average(PLA)))

print("MRV: min = {}, max = {}, avg = {}".format(np.min(MRV), np.max(MRV), np.average(MRV)))
print("ARV: min = {}, max = {}, avg = {}".format(np.min(ARV), np.max(ARV), np.average(ARV)))
print("PRV: min = {}, max = {}, avg = {}\n".format(np.min(PRV), np.max(PRV), np.average(PRV)))

print("MRA: min = {}, max = {}, avg = {}".format(np.min(MRA), np.max(MRA), np.average(MRA)))
print("ARA: min = {}, max = {}, avg = {}".format(np.min(ARA), np.max(ARA), np.average(ARA)))
print("PRA: min = {}, max = {}, avg = {}\n".format(np.min(PRA), np.max(PRA), np.average(PRA)))

os.makedirs('./step3', exist_ok=True)


plt.plot(MLA)
plt.title("Minimum Linear Acceleration")
plt.ylabel('Linear Acceleration (g)')
plt.xlabel('Instance')
plt.savefig('./step3/Min_linear_acceleration.png')
plt.close()

plt.plot(ALA)
plt.title("Average Linear Acceleration")
plt.ylabel('Linear Acceleration (g)')
plt.xlabel('Instance')
plt.savefig('./step3/Average_linear_acceleration.png')
plt.close()

plt.plot(PLA)
plt.title("Peak Linear Acceleration")
plt.ylabel('Linear Acceleration (g)')
plt.xlabel('Instance')
plt.savefig('./step3/Peak_linear_acceleration.png')
plt.close()

plt.plot(MRV)
plt.title("Minimum Rotational Velocity")
plt.ylabel('Rotational Velocity (rad/sec)')
plt.xlabel('Instance')
plt.savefig('./step3/Min_rotational_velocity.png')
plt.close()

plt.plot(ARV)
plt.title("Average Rotational Velocity")
plt.ylabel('Rotational Velocity (rad/sec)')
plt.xlabel('Instance')
plt.savefig('./step3/Average_rotational_velocity.png')
plt.close()

plt.plot(PRV)
plt.title("Peak Rotational Velocity")
plt.ylabel('Rotational Velocity (rad/sec)')
plt.xlabel('Instance')
plt.savefig('./step3/Peak_rotational_velocity.png')
plt.close()

plt.plot(MRA)
plt.title("Minimum Rotational Acceleration")
plt.ylabel('Rotational Acceleration (rad/sec^2)')
plt.xlabel('Instance')
plt.savefig('./step3/Min_rotational_acceleration.png')
plt.close()

plt.plot(ARA)
plt.title("Average Rotational Acceleration")
plt.ylabel('Rotational Acceleration (rad/sec^2)')
plt.xlabel('Instance')
plt.savefig('./step3/Average_rotational_acceleration.png')
plt.close()

plt.plot(PRA)
plt.title("Peak Rotational Acceleration")
plt.ylabel('Rotational Acceleration (rad/sec^2)')
plt.xlabel('Instance')
plt.savefig('./step3/Peak_rotational_acceleration.png')
plt.close()

#Only 5 of them go over 100 in linear acceleration. 