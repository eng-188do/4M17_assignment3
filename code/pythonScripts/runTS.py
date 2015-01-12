__author__ = 'eng-188do'

import random
import math
import subprocess
import csv
from os.path import expanduser
import os

###### INFO
# Script to extract the best point found in the whole TS over multiple runs - ie what would the best value in the
# archive be at the end of the run.
# Haven't yet profiled and made efficient - the for loops may be slow.
#   eng-188do Dec 2014


###### FUNCTIONS

# Function to find the minimum in the log entries
def findMin(populationLogEntries):
    minVal = float('inf')

    for row in populationLogEntries: #go through rows and extract the value, if smaller than current min store.
        value=float(row[2])
        if value<minVal:
            minVal=value
            bestRow=row

    return bestRow

###### SCRIPT

home = expanduser("~") #get my home directory - so you cannot see my name!

random.seed(14846126)  # set up random seed
bestLocations = []  # set up list to store the best locations in each run.

iter=1000 #number of iterations to carry out
failCount=0 #variable to store how many times in the run the program failes
while iter: # run the program 100 times recording the outputs.
    # Run the java program
    seed=str(random.randint(-math.pow(2, 63), math.pow(2, 63) - 1))
    commandString = "java -cp ~/Documents/uniwork/4M17/ex3/code/Exercise3/bin ex3.RunGeneral "
    commandString += seed
    commandString += " ~/Documents/uniwork/4M17/ex3/code/defaultTSInputs.csv ~/Documents/uniwork/4M17/ex3/code/outputTS_python TS"
    failure = subprocess.call(commandString, shell=True) #record if it has failed or not

    if not failure: #if not failed read in the file and get the best result found.
        print("Program Executed Successfully")
        fileLocation = home + '/Documents/uniwork/4M17/ex3/code/outputTS_python_FunctionCallLogger.csv'
        with open(fileLocation, newline='') as csvfile:
            populationLogEntries = csv.reader(csvfile)
            bestLocations.append(findMin(populationLogEntries))
        os.remove(fileLocation)
    else: #if failed then say so and up the count.
        print("Could not execute program, seed was " + seed)
        failCount+=1

    iter+=-1 #we have done one more iteration so only iter more to go...


# Write out the best one
writerLocation=home+ '/Documents/uniwork/4M17/ex3/code/outputTS_python_best100.csv' #storage location
with open(writerLocation, 'w', newline='') as csvfile:
    writer = csv.writer(csvfile, delimiter=',')
    writer.writerows(bestLocations) #write out the best rows in each one in a new csv for reading by MATLAB for plotting.


print('failcount was ' +str(failCount)) #print out fail count


