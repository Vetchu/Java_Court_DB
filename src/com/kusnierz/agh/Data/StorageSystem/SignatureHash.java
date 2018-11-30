package com.kusnierz.agh.Data.StorageSystem;

import com.kusnierz.agh.Data.CourtCase;

import java.util.HashMap;

public class Signature extends Hash{
    private HashMap<String, CourtCase> hashMap=new HashMap<>();

    public void put(CourtCase courtCase){
        this.hashMap.put(courtCase.Signature,courtCase);
    }
    public CourtCase get(String sig){
        return hashMap.get(sig);
    }
}
