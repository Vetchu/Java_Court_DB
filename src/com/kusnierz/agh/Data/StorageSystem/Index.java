package com.kusnierz.agh.Data.StorageSystem;

import com.kusnierz.agh.Data.Judgment;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public abstract class Index {
    protected HashMap<String, LinkedList<Judgment>> hashMap=new HashMap<>();

    public void put(Judgment judgment, String name){
        hashMap.computeIfAbsent(name, k -> new LinkedList<Judgment>());
        hashMap.get(name).add(judgment);
    }

    public LinkedList<Judgment> getByString(String string){
        return hashMap.get(string);
    }

    public String[] getHashMapKeys(){
        return  this.hashMap.keySet().toArray(new String[0]);
    }
    public ArrayList<LinkedList<Judgment>> getValues(){
        return new ArrayList<>(this.hashMap.values());
    }

    public Map<String,Integer> getLeaderboard() {
        Map<String,Integer> e=new HashMap<String,Integer>();
        for (String ref : this.hashMap.keySet()) {
            e.put(ref, this.getByString(ref).size());
        }
        return e.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }
}
