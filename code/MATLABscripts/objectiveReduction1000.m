%%prints out the objective reduction plot.
close all; clear all;
M = csvread('outputES_python_best100means.csv')
Mins = csvread('outputES_python_best100mins.csv')
gen=0:size(Mins,2)-1;
mean(M)
indices=gen;
means=mean(M)
minimums=mean(Mins);
[ax,p1,p2]=plotyy(indices,means,indices,minimums);
ylabel(ax(1),'Average Objective') % label left y-axis
ylabel(ax(2),'Minimum Objective') % label right y-axis
xlabel(ax(1),'Generation') % label x-axis

legend('Average objective in population','Minimum objective in population')
legend('Average objective in population','Minimum objective in population')