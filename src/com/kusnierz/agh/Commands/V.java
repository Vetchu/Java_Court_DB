package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class V implements ICommand {

    @Override
    public String Command(String arg, Storage storage) {
        LinkedList<Judgment> d=storage.judgesHash.getByString(arg);
        StringBuilder base= new StringBuilder();
        if(d!=null)
            for(Judgment judgment: d)
                base.append(judgment.toString());
        else base.append("Could not find judge with name "+arg);
        return base.toString();
    }

}
