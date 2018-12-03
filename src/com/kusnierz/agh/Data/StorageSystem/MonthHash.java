package com.kusnierz.agh.Data.StorageSystem;

import com.kusnierz.agh.Data.Judgment;

import java.util.LinkedList;

public class MonthHash extends Index {
    public void put2(Judgment judgment){
        hashMap.computeIfAbsent(judgment.Date.getMonth().toString(), k -> new LinkedList<Judgment>());
        hashMap.get(judgment.Date.getMonth().toString()).add(judgment);
    }
}
