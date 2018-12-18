package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.StorageSystem.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class courts implements ICommand {
    @Override
    public String Execute(String args, Storage storage) {
        String[] courtTypes=storage.courtTypeIndex.getHashMapKeys();
        StringBuilder base = new StringBuilder();
        for(String courtType:courtTypes){
            LinkedList<Judgment> b=storage.courtTypeIndex.getByString(courtType);
            base.append(courtType).append(" ").append((b != null) ? b.size() : 0).append("\n");
        }
        return base.toString();
    }
}
