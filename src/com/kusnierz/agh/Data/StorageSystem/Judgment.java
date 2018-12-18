package com.kusnierz.agh.Data.StorageSystem;

import java.time.LocalDate;
import java.util.LinkedList;

public class Judgment {
    Integer Id;
    public String Signature;
    public LocalDate Date;
    public com.kusnierz.agh.Data.StorageSystem.CourtType CourtType;
    public String JudgmentType;
    public String content;


    public LinkedList<Regulation> Refs= new LinkedList<>();;
    public LinkedList<Judge> Jugdes=new LinkedList<Judge>();
    public LinkedList<Regulation> getRefs() {
        return Refs;
    }
    public LinkedList<Judge> getJugdes() {
        return Jugdes;
    }


    @Override
    public String toString() {
        String judgesString="";
        for (Judge judge:Jugdes)
            judgesString+=judge.toString();

        return  "Signature: " + Signature + '\n' +
                "Date: " + Date + '\n' +
                "CourtType: " + CourtType + '\n' +
                "Jugdes: " +'\n'+ judgesString+'\n';
    }
}
