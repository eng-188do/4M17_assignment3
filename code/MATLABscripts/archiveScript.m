%close all; clear all;
M = csvread('outputS_FunctionCallLogger.csv'); %read in csv
archiveResults  = archive( M, 5 ); %run archive program

%%Calculate the Bird Function on a mesh grid of the area.
x=[-6:0.1:6];
[X,Y] = meshgrid(x,x);
Z=sin(X).*exp((1-cos(Y)).^2)+cos(Y).*exp((1-sin(X)).^2)+(X-Y).^2;

%%Now plot - contours first
figure
contour(X,Y,Z,'LineColor','k');
 hold on
xlabel('x_1')
ylabel('x_2')
title('Archive Results')
sub=M(:,4)==i;
plot(archiveResults(1,:),archiveResults(2,:),'o','MarkerFaceColor',      [   0.4940    0.1840    0.5560],'MarkerEdgeColor',      [   0.4940    0.1840    0.5560])

