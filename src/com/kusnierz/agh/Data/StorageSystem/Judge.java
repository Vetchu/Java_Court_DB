package com.kusnierz.agh.Data.StorageSystem;

public class Judge {
    public String name;
    String specialRole;

    @Override
    public String toString() {
        if(specialRole!=null)
        return  "Name: " + name + '\n' +
                "  SpecialRole: " + specialRole+'\n';
        else return  "Name: " + name + '\n';
    }
}
