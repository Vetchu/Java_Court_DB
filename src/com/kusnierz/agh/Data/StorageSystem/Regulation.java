package com.kusnierz.agh.Data.StorageSystem;

import java.util.Objects;

public class Regulation {
    Long journalnumber;
    Long year;
    Long Entry;
    String text;

    Regulation(Long journalnumber, Long year, Long Entry, String text){
        this.journalnumber=journalnumber;
        this.year=year;
        this.Entry=Entry;
        this.text=text;
    }


    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regulation that = (Regulation) o;
        return Objects.equals(journalnumber, that.journalnumber) &&
                Objects.equals(year, that.year) &&
                Objects.equals(Entry, that.Entry);
    }


    public int hashCode() {
        if(journalnumber!=null && year!=null && Entry!=null)
        return Objects.hash(journalnumber, year, Entry);
        else return text.length();
    }
}
