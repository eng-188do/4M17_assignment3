close all; clear all;
M = csvread('outputS_python_best100.csv')
edges={[-6:0.5:6],[-6:0.5:6]};
hist3([M(:,1),M(:,2)],'Edges',edges);
h = get(gca,'child');
heights       = get(h,'Zdata');
% Index outer heights leaving a contour of zeros
mask          = ~logical(imdilate(heights,ones(3)));
% Set the zero heights to NaN
heights(mask) = NaN;
% Final result
set(h,'ZData',heights)
hold on
x=[-6:0.1:6];
[X,Y] = meshgrid(x,x);
Z=sin(X).*exp((1-cos(Y)).^2)+cos(Y).*exp((1-sin(X)).^2)+(X-Y).^2;
contour(X,Y,Z);
xlabel('x_1')
ylabel('x_2')
zlabel('Frequency')

he2=hist3([M(:,1),M(:,2)],'Edges',edges);