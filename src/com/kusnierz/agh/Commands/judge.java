package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class judge implements ICommand {

    @Override
    public String Execute(String arg, Storage storage) {
        LinkedList<Judgment> d=storage.judgesIndex.getByString(arg);
        /*
        StringBuilder base= new StringBuilder();
        if(d!=null)
            for(Judgment judgment: d)
                base.append(judgment.toString());
        else base.append("Could not find judge with name "+arg);
        */

        return (d==null)?String.valueOf(0):String.valueOf(d.size());
    }

}
