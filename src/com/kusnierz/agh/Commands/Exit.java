package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Storage;

public class Exit implements ICommand {
    @Override
    public String Execute(String args, Storage storage) {
        System.exit(0);
        return null;
    }
}
