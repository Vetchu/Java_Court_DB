package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.StorageSystem.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.time.Month;
import java.util.LinkedList;

public class months implements ICommand{

    @Override
    public String Execute(String args, Storage storage) {
        Month z=null;
        StringBuilder base= new StringBuilder();
        do{
            z = z==null?Month.JANUARY:Month.values()[z.ordinal() + 1];
            LinkedList<Judgment> b=storage.monthIndex.getByString(z.toString());
            base.append(z.toString()).append(" ").append((b != null) ? b.size() : 0).append("\n");
        }while(z!=Month.DECEMBER);
        return base.toString();
    }
}
