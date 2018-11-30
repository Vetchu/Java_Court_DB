package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Storage;

import java.util.Map;

public class VI implements ICommand {
    @Override
    public String Command(String args, Storage storage) {
        Integer g=1;
        Map<String,Integer> sortedLeaderboard=storage.judgesHash.getLeaderboard();
        StringBuilder base= new StringBuilder();
        for(String sort:sortedLeaderboard.keySet()){
            if(g<11)
                base.append(g++).append(" ").append(sort).append(" ").append(sortedLeaderboard.get(sort)).append("\n");
        }
        return base.toString();
    }
}
