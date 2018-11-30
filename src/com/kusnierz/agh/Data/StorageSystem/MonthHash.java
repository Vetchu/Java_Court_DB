package com.kusnierz.agh.Data.StorageSystem;

import com.kusnierz.agh.Data.CourtCase;

import java.util.LinkedList;

public class Month extends Hash{
    public void put2(CourtCase courtCase){
        hashMap.computeIfAbsent(courtCase.Date.getMonth().toString(), k -> new LinkedList<CourtCase>());
        hashMap.get(courtCase.Date.getMonth().toString()).add(courtCase);
    }
}
