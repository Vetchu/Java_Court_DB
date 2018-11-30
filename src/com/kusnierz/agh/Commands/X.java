package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedList;
import java.util.Locale;

public class X implements ICommand {
    @Override
    public String Command(String args, Storage storage) {
        Integer i=0;
        Integer size=0;
        for (LinkedList<Judgment> ac:storage.signatureHash.getValues()) {
            for(Judgment judgment:ac) {
                i++;
                size += judgment.getJugdes().size();
            }
        }
        double before=(double)size/i;
        double avg=Double.valueOf(new DecimalFormat("#.##",new DecimalFormatSymbols(Locale.US)).format(before));
        return  "There are "+i+" unique cases with average "+avg+" judges per case.";
    }
}
