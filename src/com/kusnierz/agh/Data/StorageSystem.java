package com.kusnierz.agh.Data;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class StorageSystem {
    public HashMap<String, CourtCase> SignatureHash=new HashMap<>();
    public HashMap<Month, LinkedList<CourtCase>> MonthHash=new HashMap<>();
    public HashMap<String, LinkedList<CourtCase>> YearHash=new HashMap<>();
    public HashMap<String, LinkedList<CourtCase>> JudgeHash=new HashMap<>();
    public HashMap<String, LinkedList<CourtCase>> RefHash=new HashMap<>();
    public HashMap<String, LinkedList<CourtCase>> CourtTypeHash=new HashMap<>();

    public void putBySignature(CourtCase courtCase){
        SignatureHash.put(courtCase.Signature,courtCase);
    }
    public CourtCase getBySignature(String sig){
        return  SignatureHash.get(sig);
    }
    public void putByMonth(CourtCase courtCase){
        MonthHash.computeIfAbsent(courtCase.Date.getMonth(), k -> new LinkedList<CourtCase>());
        MonthHash.get(courtCase.Date.getMonth()).add(courtCase);
    }
    public LinkedList<CourtCase>  getByMonth(Month month){
        return  MonthHash.get(month);
    }

    public void putByYear(CourtCase courtCase){
        YearHash.computeIfAbsent(String.valueOf(courtCase.Date.getYear()), k -> new LinkedList<CourtCase>());
        YearHash.get(String.valueOf(courtCase.Date.getYear())).add(courtCase);
    }
    public LinkedList<CourtCase> getByYear(int year){
        return  YearHash.get(String.valueOf(year));
    }

    public void putByJudge(CourtCase courtCase,Judge judge){
        JudgeHash.computeIfAbsent(judge.name, k -> new LinkedList<CourtCase>());
        JudgeHash.get(judge.name).add(courtCase);
    }
    public LinkedList<CourtCase> getByJudge(String judgeName){
        return  JudgeHash.get(judgeName);
    }

}