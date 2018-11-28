package com.kusnierz.agh.Data;

import java.util.HashMap;

public class StorageSystem {
    public HashMap<String, CourtCase> SignatureHash=new HashMap<>();

    public void putBySignature(CourtCase courtCase){
        SignatureHash.put(courtCase.Signature,courtCase);
    }
    public CourtCase getBySignature(String sig){
        return  SignatureHash.get(sig);
    }
}