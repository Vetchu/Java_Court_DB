package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class rubrum implements ICommand{

    @Override
    public String Execute(String command, Storage storage) {
        String[] commands=command.split("[\\s]*,[\\s]*");

        StringBuilder base= new StringBuilder();
        for (String sig:commands) {
            LinkedList<Judgment> a = storage.signatureIndex.getByString(sig);
            if(a!=null) {
                for (Judgment judgment : a)
                    base.append(judgment.toString());
            }
            else
                base.append("Could not find any case signed ").append(sig).append("\n");
        }
        return base.toString();
    }
}
