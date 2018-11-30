package com.kusnierz.agh.Data;

import com.kusnierz.agh.Data.StorageSystem.*;


public class Storage {
    public SignatureHash signatureHash =new SignatureHash();
    public JudgesHash judgesHash =new JudgesHash();
    public MonthHash monthHash =new MonthHash();
    public CourtTypeHash courtTypeHash =new CourtTypeHash();
    public ReferencedRegulationsHash referencedRegulationsHash =new ReferencedRegulationsHash();

    public void insert(Judgment judgment){
        this.signatureHash.put(judgment,judgment.Signature);
        this.monthHash.put(judgment, judgment.Date.getMonth().toString());
        this.courtTypeHash.put(judgment, judgment.CourtType);
        for(Judge judge: judgment.getJugdes()) {
            this.judgesHash.put(judgment, judge.name);
        }
        for(String ref: judgment.getRefs()) {
            this.referencedRegulationsHash.put(judgment,ref);
        }
    }
}