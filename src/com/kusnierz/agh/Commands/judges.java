package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Storage;

import java.util.Map;

public class judges implements ICommand {
    @Override
    public String Execute(String args, Storage storage) {
        Integer g=1;
        Map<String,Integer> sortedLeaderboard=storage.judgesIndex.getLeaderboard();
        StringBuilder base= new StringBuilder();
        for(String sort:sortedLeaderboard.keySet()){
            if(g<11)
                base.append(g++).append(" ").append(sort).append(" ").append(sortedLeaderboard.get(sort)).append("\n");
        }
        return base.toString();
    }
}
