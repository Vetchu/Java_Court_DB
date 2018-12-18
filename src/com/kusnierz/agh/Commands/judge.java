package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.StorageSystem.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class judge implements ICommand {

    @Override
    public String Execute(String arg, Storage storage) {
        LinkedList<Judgment> d=storage.judgesIndex.getByString(arg);
        return (d==null)?String.valueOf(0):String.valueOf(d.size());
    }

}
