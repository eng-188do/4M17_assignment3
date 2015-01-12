close all; clear all;
M = csvread('outputES_SurvivingPopulationLogger.csv');

for i=0:M(end,4)

    
    sub=M(M(:,4)==i,:);
    minimums(i+1)=min(sub(:,3));
    means(i+1)=mean(sub(:,3));
    
end
indices=0:M(end,4);
[ax,p1,p2]=plotyy(indices,means,indices,minimums);

ylabel(ax(1),'Average Objective') % label left y-axis
ylabel(ax(2),'Minimum Objective') % label right y-axis
xlabel(ax(1),'Generation') % label x-axis
legend('Average objective in population','Minimum objective in population')