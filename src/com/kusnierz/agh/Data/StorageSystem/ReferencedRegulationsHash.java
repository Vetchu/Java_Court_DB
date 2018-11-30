package com.kusnierz.agh.Data.StorageSystem;

import com.kusnierz.agh.Data.CourtCase;

import java.util.LinkedList;

public class Refs extends Hash {
    void putByReferencedRegulations(CourtCase courtCase) {
        for(String ref:courtCase.Refs){
            hashMap.computeIfAbsent(ref, k -> new LinkedList<CourtCase>());
            hashMap.get(ref).add(courtCase);
        }
    }
}
