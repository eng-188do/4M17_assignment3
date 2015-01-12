function [ fx ] = f( X )
%performs the function we are interested in
%   Detailed explanation goes here
x=X(1);
y=X(2);
fx=sin(x)*exp((1-cos(y))^2)+cos(y)*exp((1-sin(x))^2)+(x-y)^2;

end

