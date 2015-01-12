__author__ = 'eng-188do'

# Script to extract the best point found in the whole ES over multiple runs - ie what would the best value in the
# archive be at the end of the run.


import random
import math
import subprocess
import csv
from os.path import expanduser
import os
import numpy as np






def produceMeanObjectiveVal(populationLogEntries):
    numGen=populationLogEntries[-1,3]
    mean=[]
    for i in range(0,int(numGen)+1):
        genNums=populationLogEntries[:,3]
        gensAfter=float(i)* np.ones((populationLogEntries.shape[0]))
        indices= np.equal(gensAfter.T, genNums)
        mean.append(np.mean(populationLogEntries[indices,2]))
    return mean

def produceMinObjectiveVal(populationLogEntries):
    numGen=populationLogEntries[-1,3]
    mins=[]
    for i in range(0,int(numGen)+1):
        genNums=populationLogEntries[:,3]
        gensAfter=float(i)* np.ones((populationLogEntries.shape[0]))
        indices= np.equal(gensAfter.T, genNums)
        mins.append(np.amin(populationLogEntries[indices,2]))
    return mins



home = expanduser("~")

random.seed(14846126)  # set up random seed
meansRun = []  # set up list to store the best locations in each run.
minsRun=[] # set up list to store the best locations in each run.

iter=1000
while iter: # run the program 100 times recording the outputs.
    # Run the java program
    seed=str(random.randint(-math.pow(2, 63), math.pow(2, 63) - 1))
    commandString = "java -cp ~/Documents/uniwork/4M17/ex3/code/Exercise3/bin ex3.RunGeneral "
    commandString += seed
    commandString += " ~/Documents/uniwork/4M17/ex3/code/defaultESInputs.csv ~/Documents/uniwork/4M17/ex3/code/outputES_python ES"
    failure = subprocess.call(commandString, shell=True)

    if not failure:
        print("Program Executed Successfully")
        fileLocation = home + '/Documents/uniwork/4M17/ex3/code/outputES_python_SurvivingPopulationLogger.csv'
        populationLogger = np.genfromtxt(fileLocation, delimiter=',')
        meansRun.append(produceMeanObjectiveVal(populationLogger))
        minsRun.append(produceMinObjectiveVal(populationLogger))
        os.remove(fileLocation)
    else:
        print("Could not execute program, seed was " + seed)

    iter+=-1 #  reduce the increment


# Write out the best one
writerLocation=home+ '/Documents/uniwork/4M17/ex3/code/outputES_python_best100means.csv'
with open(writerLocation, 'w', newline='') as csvfile:
    writer = csv.writer(csvfile, delimiter=',')
    writer.writerows(meansRun)

writerLocation=home+ '/Documents/uniwork/4M17/ex3/code/outputES_python_best100mins.csv'
with open(writerLocation, 'w', newline='') as csvfile:
    writer = csv.writer(csvfile, delimiter=',')
    writer.writerows(minsRun)

