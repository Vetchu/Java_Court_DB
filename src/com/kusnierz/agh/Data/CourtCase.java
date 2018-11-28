package com.kusnierz.agh.Data;

import java.util.LinkedList;

public class CourtCase {
    Integer Id;
    String Signature;
    String Date;
    String CourtType;
    LinkedList<Judge> Jugdes;


    @Override
    public String toString() {
        return "CourtCase{" +
                "Signature='" + Signature + '\'' +
                ", Date='" + Date + '\'' +
                ", CourtType='" + CourtType + '\'' +
                ", Jugdes=" + Jugdes +
                '}';
    }
}
