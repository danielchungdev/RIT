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

plot_waveforms(LA, RV, RA, TL, TR)

#Only 5 of them go over 100 in linear acceleration. 