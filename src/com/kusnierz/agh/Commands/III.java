package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class III implements ICommand {
    @Override
    public String Command(String arg, Storage storage) {
        LinkedList<Judgment> a = storage.signatureHash.getByString(arg);
        if (a != null)
            for (Judgment judgment : a)
                if (judgment.JudgmentType.equals("REASONS")) {
                    return judgment.toString();
                }
        return "Could not find any reason signed as " + arg;
    }
}

