package com.kusnierz.agh.Data.StorageSystem;

import com.kusnierz.agh.Data.CourtCase;
import com.kusnierz.agh.Data.Judge;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Judges extends Hash{

    public void putByJudges(CourtCase courtCase) {
        for (Judge judge : courtCase.Jugdes){
            hashMap.computeIfAbsent(judge.name, k -> new LinkedList<CourtCase>());
            hashMap.get(judge.name).add(courtCase);
        }
    }
    public LinkedList<CourtCase> get(String judgeName){
        return  hashMap.get(judgeName);
    }

    public Map<String,Integer> getLeaderboard() {
        Map<String,Integer> e=new HashMap<String,Integer>();
        for (String judge : this.hashMap.keySet()) {
            e.put(judge, this.get(judge).size());
        }
        return e.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }
}
