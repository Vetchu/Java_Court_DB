package com.kusnierz.agh.Data.StorageSystem;

import com.kusnierz.agh.Data.CourtCase;

import java.util.LinkedList;

public class CourtType extends Hash {
    void put(CourtCase courtCase){
        hashMap.computeIfAbsent(courtCase.CourtType, k -> new LinkedList<CourtCase>());
        hashMap.get(courtCase.CourtType).add(courtCase);
    }
}
