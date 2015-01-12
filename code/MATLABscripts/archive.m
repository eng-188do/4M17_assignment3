function [ archiveResults ] = archive( functionCalls, L )
%archive best L dissimilar Solutions archive
%   functionCalls: record of all the function calls
%   L= number of calls to store
%
%archive is a matrix with the points as columns and the rows as x coord,
%y coord and value.
% ie archive=[ xcoord1, xcoord2,....;
%              ycoord1, ycoord2,....;
%              value1 , value2 , ...]


archiveResults=functionCalls(1,1:3)'; %put first item in archive as always goes in.
dMin=0.4;
dSim=0.05;

numInArchive=size(archiveResults,2);
assert(numInArchive==1); %should be 1 in at this point.

% go through all function calls (starting at second one as we have already added first.)
for i=2:size(functionCalls,1) 
    numInArchive=size(archiveResults,2); %update number in archive.
    point=functionCalls(i,1:3)'; %get in the same format as archive - ie column vector
    
    [index, distanceToClosest] = findClosestSolution(archiveResults,point); % find closest point and distance to it.
    
    if (numInArchive<L) %archive not full
        if(distanceToClosest>dMin) %if far emough away from others then archive it
            archiveResults(:,end+1)=point;
        end
    else %archive is full
        if (distanceToClosest>dMin) %sufficiently dissimilar to other points in archive
            [indexWorst, valueWorst]=findWorstInArchive(archiveResults);
            if (point(3)<valueWorst) %archive if it is better than the worse point
                archiveResults(:,indexWorst)=point; % replaces the worst point in the archive
            end
        else %not dissimilar to other points in archive
            if point(3)<min(archiveResults(3,:)) %archive it if it is the best point so far                
                archiveResults(:,index)=point; %then it replaces the closest point
            elseif (point(3)<archiveResults(3,index) && distanceToClosest<dSim) %or if it close enough to its closest neighbour and is better than it then it is archived.
                archiveResults(:,index)=point;
            end
        end
    end
end
end

function [index, distance] = findClosestSolution(archive,point)
%finds the index of the closest point. if several returns the one with the
%maximum value. If there are several of these then it will return the first
%one.

 %set the minimum to the first entry at first.
minDistance=norm(archive(1:2,1)-point(1:2));
index=1;
value=archive(3,1);

    for i=2:size(archive,2) %we could consider vectorising this loop to speed it up. but archive is only small so probably insignificant.
        if (norm(archive(1:2,i)-point(1:2))< minDistance)
            minDistance=norm(archive(1:2,i)-point(1:2)); %replace it with new index.
            index=i;
            value=archive(3,i);
        elseif (norm(archive(1:2,i)-point(1:2))== minDistance && archive(3,i) > value) %%if same distance as other one replace if the value is better.
            minDistance=norm(archive(1:2,i)-point(1:2)); %replace it with new index.
            index=i;
            value=archive(3,i);
        end
    end
    
distance=minDistance;
end

function [index, value]=findWorstInArchive(archive)
%finds the index and the value of the point with the highest value in the
%archive.
[value index]=max(archive(3,:));
end
        
        