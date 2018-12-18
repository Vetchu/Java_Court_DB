package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.StorageSystem.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class jury implements ICommand {
    @Override
    public String Execute(String args, Storage storage) {
        LinkedList<Judgment> a=storage.juryIndex.getByString(args);
        /*
        Integer i=0;
        Integer size=0;
        for (LinkedList<Judgment> ac:storage.signatureIndex.getValues()) {
            for(Judgment judgment:ac) {
                i++;
                size += judgment.getJugdes().size();
            }
        }
        double before=(double)size/i;
        double avg=Double.valueOf(new DecimalFormat("#.##",new DecimalFormatSymbols(Locale.US)).format(before));
        return  "There are "+i+" unique cases with average "+avg+" judges per case.";
        */
        return "There are " +(a==null?String.valueOf(0): String.valueOf(a.size()))+" judgments having number of judges equal to "+args ;
    }
}
