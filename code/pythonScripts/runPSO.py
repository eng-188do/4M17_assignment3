__author__ = 'eng-188do'

# Script to extract the best point found in the whole PSO over multiple runs - ie what would the best value in the
# archive be at the end of the run.


import random
import math
import subprocess
import csv
from os.path import expanduser
import os


# Function to find the minimum in the log entries
def findMin(populationLogEntries):
    minVal = float('inf')

    for row in populationLogEntries:
        value=float(row[2])
        if value<minVal:
            minVal=value
            bestRow=row

    return bestRow


home = expanduser("~")

random.seed(14846126)  # set up random seed
bestLocations = []  # set up list to store the best locations in each run.

iter=1000
failCount=0
while iter: # run the program 100 times recording the outputs.
    # Run the java program
    seed=str(random.randint(-math.pow(2, 63), math.pow(2, 63) - 1))
    commandString = "java -cp ~/Documents/uniwork/4M17/ex3/code/Exercise3/bin ex3.RunGeneral "
    commandString += seed
    commandString += " ~/Documents/uniwork/4M17/ex3/code/defaultSInputs.csv ~/Documents/uniwork/4M17/ex3/code/outputS_python PS"
    failure = subprocess.call(commandString, shell=True)

    if not failure:
        print("Program Executed Successfully")
        fileLocation = home + '/Documents/uniwork/4M17/ex3/code/outputS_python_FunctionCallLogger.csv'
        with open(fileLocation, newline='') as csvfile:
            populationLogEntries = csv.reader(csvfile)
            bestLocations.append(findMin(populationLogEntries))
        os.remove(fileLocation)
    else:
        print("Could not execute program, seed was " + seed)
        failCount+=1

    iter+=-1 #  reduce the increment


# Write out the best one
writerLocation=home+ '/Documents/uniwork/4M17/ex3/code/outputS_python_best100.csv'
with open(writerLocation, 'w', newline='') as csvfile:
    writer = csv.writer(csvfile, delimiter=',')
    writer.writerows(bestLocations)


print('failcount was ' +str(failCount))


