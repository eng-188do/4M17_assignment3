__author__ = 'eng-188do'

import random
import math
import subprocess
import csv
from os.path import expanduser
import os
import numpy as np
import scipy.io as sio

#####
# This script extracts the best min found so far for each fucntion evaluation. It averages these figures over multiple runs.
# Does it for TS.

#####FUNCTIONS
# Function to find the minimum in the log entries
def findMins(populationLogEntries):
    minVal = float('inf') # infinite to ensure something gets added.
    minsOut=np.zeros(1000) #this stores the minimum function value so far.
    pos=0 #position to insert
    for row in populationLogEntries:
        value=float(row[2])
        if value<minVal:
            minVal=value
        np.put(minsOut,pos,minVal)
        pos+=1

    return minsOut

####SCRIPT
home = expanduser("~") # home dir.
random.seed(14846126)  # set up random seed

iter=500
cumulativeMins=np.zeros(1000) #list to store the cumulative mins found at each iteration.
count=0 #how many itereations were successful.
while iter: # run the program iter times recording the outputs.
    # Run the java program
    seed=str(random.randint(-math.pow(2, 63), math.pow(2, 63) - 1))
    commandString = "java -cp ~/Documents/uniwork/4M17/ex3/code/Exercise3/bin ex3.RunGeneral "
    commandString += seed
    commandString += " ~/Documents/uniwork/4M17/ex3/code/defaultTSInputs.csv ~/Documents/uniwork/4M17/ex3/code/outputTS_python TS"
    failure = subprocess.call(commandString, shell=True)

    if not failure:
        print("Program Executed Successfully")
        fileLocation = home + '/Documents/uniwork/4M17/ex3/code/outputTS_python_FunctionCallLogger.csv'
        with open(fileLocation, newline='') as csvfile:
            populationLogEntries = csv.reader(csvfile)
            cumulativeMins=np.add(cumulativeMins, findMins(populationLogEntries) )
        os.remove(fileLocation)
        count+=1
    else:
        print("Could not execute program, seed was " + seed)

    iter+=-1 #  reduce the increment


cumulative=np.divide(cumulativeMins,count) # get average.


# Write out the averages in MATLAB format.
sio.savemat('np_vector_minsAvgTS.mat', {'avgBestMinsTS':cumulative})


