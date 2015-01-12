close all; clear all;
x=[-6:0.1:6];
[X,Y] = meshgrid(x,x);
Z=sin(X).*exp((1-cos(Y)).^2)+cos(Y).*exp((1-sin(X)).^2)+(X-Y).^2;
colors=get(groot,'DefaultAxesColorOrder');
colors=[colors; colors; colors; colors; colors;];
figure
 M = csvread('outputS_BirdLogger.csv');
hold on
contour(X,Y,Z,'LineColor','k');
legendTitles={'Contour'};
for i=0:14
   birdData=M(M(:,1)==i,:);
   plot(birdData(:,2),birdData(:,3),'Color',colors(i+1,:))
   plot(birdData(1,2),birdData(1,3),'o','MarkerFaceColor',     colors(i+1,:),'Color',colors(i+1,:))
       xlabel('x_1')
    ylabel('x_2')
    legendTitles{end+1}=['Bird' num2str(i) ' Path'];

    legendTitles{end+1}= ['Bird' num2str(i) ' Start'];
end

title('Bird Locations')
legend(legendTitles);
