%%Plot minimum and all bcurrent base points.
close all; clear all;
M = csvread('outputTS_TabuSearchLogger.csv');

min=M(1,3);
storage=[];
indices=1:size(M,1);

for i=indices
    if min>M(i,3)
        min=M(i,3);
    end
    input=[M(i,3);min];
    storage=[storage, input];
end
storage=log10(storage-min+10);
%[ax,p1,p2]=
plot(indices,storage(1,:),indices,storage(2,:));
%semilogy(indices,storage(1,:)); hold on;
%semilogy(indices,storage(2,:));

%ylabel(ax(1),'Current Value') % label left y-axis
%ylabel(ax(2),'Best Minimum') % label right y-axis
ylabel('log_{10}(Value-min+10)')
%ylabel('Value')
%xlabel(ax(1),'Base Point Number') % label x-axis
xlabel('Base Point Number') % label x-axis
legend('Current Base Point''s Value','Smallest Base Point So Far''s Value')