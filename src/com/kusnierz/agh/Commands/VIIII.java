package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Storage;

import java.util.Map;

public class VIIII implements ICommand {
    @Override
    public String Command(String args, Storage storage) {
        Map<String,Integer> sortedRefLeaderboard=storage.referencedRegulationsHash.getLeaderboard();

        Integer refIter=0;
        StringBuilder base= new StringBuilder();
        for(String sort:sortedRefLeaderboard.keySet()){
            if(refIter<11)
                base.append(refIter++).append(" ").append(sort).append(" ").append(sortedRefLeaderboard.get(sort)).append("\n");
        }
        return base.toString();
    }
}
