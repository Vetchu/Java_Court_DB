package com.kusnierz.agh.Data;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class StorageSystem {
    public HashMap<String, CourtCase> SignatureHash=new HashMap<>();
    private HashMap<Month, LinkedList<CourtCase>> MonthHash=new HashMap<>();
    private HashMap<String, LinkedList<CourtCase>> YearHash=new HashMap<>();
    private HashMap<String, LinkedList<CourtCase>> JudgeHash=new HashMap<>();
    private HashMap<String, LinkedList<CourtCase>> RefHash=new HashMap<>();
    private HashMap<String, LinkedList<CourtCase>> CourtTypeHash=new HashMap<>();

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

    public void putByJudges(CourtCase courtCase) {
        for (Judge judge : courtCase.Jugdes){

            JudgeHash.computeIfAbsent(judge.name, k -> new LinkedList<CourtCase>());
        JudgeHash.get(judge.name).add(courtCase);
        }
    }
    public LinkedList<CourtCase> getByJudge(String judgeName){
        return  JudgeHash.get(judgeName);
    }

    public Map<String,Integer> getLeaderboard(String type) {
             Map<String,Integer> e=new HashMap<String,Integer>();
            for (String judge : this.JudgeHash.keySet()) {
                e.put(judge, this.getByJudge(judge).size());
            }
            return e.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    void putByCourtType(CourtCase courtCase){
        CourtTypeHash.computeIfAbsent(courtCase.CourtType, k -> new LinkedList<CourtCase>());
        CourtTypeHash.get(courtCase.CourtType).add(courtCase);
    }
    public LinkedList<CourtCase> getByCourtType(String courtType){
        return CourtTypeHash.get(courtType);
    }
    public String[] getCourtTypes(){
        return  this.CourtTypeHash.keySet().toArray(new String[0]);
    }

    void putByReferencedRegulations(CourtCase courtCase) {
        for(String ref:courtCase.Refs){
            RefHash.computeIfAbsent(ref, k -> new LinkedList<CourtCase>());
            RefHash.get(ref).add(courtCase);
        }
    }
    public LinkedList<CourtCase> getByRegulation(String regulation){
        return RefHash.get(regulation);
    }
    public String[] getReferencedRegulations(){
        return  this.RefHash.keySet().toArray(new String[0]);
    }
}