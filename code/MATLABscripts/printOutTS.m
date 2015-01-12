x=[-6:0.1:6];
[X,Y] = meshgrid(x,x);
Z=sin(X).*exp((1-cos(Y)).^2)+cos(Y).*exp((1-sin(X)).^2)+(X-Y).^2;

figure
 M = csvread('outputTS_TabuSearchLogger.csv');
hold on
for i=0:M(end,4)
    hold off

    contour(X,Y,Z,'LineColor','k');
    hold on
    xlabel('x_1')
    ylabel('x_2')
    switch mod(i,3)
        case 0
            typeStr='Reduction';
        case 1
            typeStr='Intensification';
        case 2
            typeStr='Diversification';
    end
    title(typeStr);
    
            
    subOverall=M(M(:,4)==i,:);
    sub1=subOverall(subOverall(:,7)==0,:); %non pattern moves
    sub2=subOverall(subOverall(:,7)==1,:); % pattern moves
    
    plot(sub1(:,1),sub1(:,2),'o','MarkerFaceColor',     [0    0.4470    0.7410])
    plot(sub2(:,1),sub2(:,2),'x','MarkerEdgeColor',     [0    0.4470    0.7410])
    legend('Contour','Normal Moves', 'Pattern Moves')
    
    plot(subOverall(1,1),subOverall(1,2),'o','MarkerEdgeColor',     'r')
    plot(subOverall(end,1),subOverall(end,2),'o','MarkerEdgeColor',     'g')
    pause
end