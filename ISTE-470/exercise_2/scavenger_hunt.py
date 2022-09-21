import matplotlib.pyplot as plt
import numpy as np

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
        data[i] = float(data[i])

    TR.append(data)
    infile.close()

def plot_waveforms(LA, RV, RA, TL, TR) :
    print('create your individual waveform plots here')
    
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

plt.plot(TL, LA[6, :])
plt.title('Linear Acceleration of Instance 7')
plt.xlabel('Time (ms)')
plt.ylabel('Lin Accel (g)')
plt.xticks(np.arange(0, 55, step=5))
plt.savefig('Instance 7.png')
plt.close()


for i in range(0, 1):
    plt.subplot( 3, 1, 1 )
    plt.plot(TL, LA[i, :])
    plt.title("Wave forms for Instance {}".format(i + 1))
    plt.subplot( 3, 1, 2 )
    plt.plot(TR, RV[i, :])
    plt.subplot( 3, 1, 3 )
    plt.plot(TL, RA[i, :])
    plt.savefig('Instance ' + str(i + 1) + '.png')
    plt.xlabel('Time (ms)')
    plt.close()
