close all; clear all;
ES1 = csvread('ES1.csv');
ES2 = csvread('ES2.csv');
TS1 = csvread('TS1.csv');
TS2 = csvread('TS2.csv');
PS1 = csvread('PS1.csv');
PS2 = csvread('PS2.csv');
fprintf('mins: ')
min(ES1(:,3))
min(ES2(:,3))
min(TS1(:,3))
min(TS2(:,3))
min(PS1(:,3))
min(PS2(:,3))
fprintf('means: ')
mean(ES1(:,3))
mean(ES2(:,3))
mean(TS1(:,3))
mean(TS2(:,3))
mean(PS1(:,3))
mean(PS2(:,3))
minSize=size(TS2(:,3),1);
vector=[ES1(1:minSize,3), ES2(1:minSize,3),TS1(1:minSize,3),TS2(1:minSize,3),PS1(1:minSize,3),PS2(1:minSize,3)];
boxplot(vector,'labels',{'ES1','ES2','TS1','TS2','PSO1','PSO2'},'colors',    [0    0.4470    0.7410])
%'ES Default','ES POP. Size 30', 'TS Default', ...
   % 'TS New Div.  Scheme (mtm 1)','PSO Default','PSO Social 0.1, Avoid 0.01\n and Flock 15'}, ...
  %  'labelorientation','inline'
  
  ylabel('Function Value')
  pause
  ylim([-106.8 -105.4])