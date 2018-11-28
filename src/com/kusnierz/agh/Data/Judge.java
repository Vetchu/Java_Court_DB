package com.kusnierz.agh.Data;

public class Judge {
    String name;
    String specialRole;

    @Override
    public String toString() {
        if(specialRole!=null)
        return  "Name: " + name + '\n' +
                "  SpecialRole: " + specialRole+'\n';
        else return  "Name: " + name + '\n';
    }
}
