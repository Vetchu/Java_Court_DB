package com.kusnierz.agh.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class CourtCase {
    Integer Id;
    String Signature;
    LocalDate Date;
    String CourtType;
    LinkedList<String> Refs;
    LinkedList<Judge> Jugdes;


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
