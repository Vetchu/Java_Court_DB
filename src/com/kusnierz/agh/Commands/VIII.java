package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class VIII implements ICommand {
    @Override
    public String Command(String args, Storage storage) {
        String[] courtTypes=storage.courtTypeHash.getHashMapKeys();
        StringBuilder base = new StringBuilder();
        for(String courtType:courtTypes){
            LinkedList<Judgment> b=storage.courtTypeHash.getByString(courtType);
            base.append(courtType).append(" ").append((b != null) ? b.size() : 0).append("\n");
        }
        return base.toString();
    }
}
