package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Storage;

public interface ICommand {
    String Command(String args, Storage storage);
}
