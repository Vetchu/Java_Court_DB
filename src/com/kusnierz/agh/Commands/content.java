package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class content implements ICommand {
    @Override
    public String Execute(String arg, Storage storage) {
        LinkedList<Judgment> a = storage.signatureIndex.getByString(arg);


        //return a.get(0).
        if (a != null)
            for (Judgment judgment : a)
                if (judgment.JudgmentType.equals("REASONS")) {
                    return judgment.toString();
                }

        return "Could not find any case signed as " + arg;
    }
}

