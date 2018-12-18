package com.kusnierz.agh.Data;

import com.kusnierz.agh.Data.StorageSystem.*;

public class Storage {
    public Index signatureIndex =new Index();
    public Index judgesIndex =new Index();
    public Index monthIndex =new Index();
    public Index courtTypeIndex =new Index();
    public Index referencedRegulationsIndex =new Index();
    public Index juryIndex=new Index();

    public void insert(Judgment judgment){
        this.signatureIndex.put(judgment,judgment.Signature.toLowerCase());
        this.monthIndex.put(judgment, judgment.Date.getMonth().toString().toLowerCase());
        this.courtTypeIndex.put(judgment, judgment.CourtType.toString());
        for(Judge judge: judgment.getJugdes()) {
            this.judgesIndex.put(judgment, judge.name.toLowerCase());
        }
        for(String ref: judgment.getRefs()) {
            this.referencedRegulationsIndex.put(judgment,ref.toLowerCase());
        }
        this.juryIndex.put(judgment, String.valueOf(judgment.Jugdes.size()));
    }
}