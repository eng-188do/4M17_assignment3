x=[-6:0.1:6];
[X,Y] = meshgrid(x,x);
Z=sin(X).*exp((1-cos(Y)).^2)+cos(Y).*exp((1-sin(X)).^2)+(X-Y).^2;

figure
 M = csvread('outputES_SurvivingPopulationLogger.csv');
hold on
for i=0:M(end,4)
    hold off

    contour(X,Y,Z,'LineColor','k');
    hold on
    xlabel('x_1')
    ylabel('x_2')
    title(['Generation ' num2str(i)])
    sub=M(:,4)==i;
    plot(M(sub,1),M(sub,2),'o','MarkerFaceColor',     [0    0.4470    0.7410])
    pause
end