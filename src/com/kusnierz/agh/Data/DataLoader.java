package com.kusnierz.agh.Data;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataLoader {
    public List<Jugdment> LoadData(String path) throws IOException, ParseException {
        JSONParser parser= new JSONParser();
        JSONObject json = (JSONObject) parser.parse(new FileReader(path));
        return putIntoStorage(json);
    }

    public List<Jugdment> putIntoStorage(JSONObject JSON){
        JSONArray items= (JSONArray) JSON.get("items");
        List<Jugdment> jugdArray = new ArrayList<>();
        for (Object rawobject: items) {
            JSONObject object=(JSONObject) rawobject;

            Jugdment jugdment=new Jugdment();
            jugdment.Id =  ((Long)  object.get("id")).intValue();
            jugdment.Date = (String) object.get("judgmentDate");
            jugdment.CourtType = (String) object.get("courtType");
            /*jugdment.Jugdes = new List<String>;
            JSONArray judgeArray= (JSONArray) object.get("judges");
            for(Object judge:judgeArray){
                ((List) jugdment.Jugdes).add(judge.get("name"));
            }*/

            jugdArray.add(jugdment);
            System.out.println(jugdment.Id+jugdment.Date+jugdment.CourtType);
        }
        return jugdArray;
    }


    public static void main(String[] main){
        DataLoader dataLoader=new DataLoader();
        try {
            dataLoader.LoadData("./src/com/kusnierz/agh/Json/judgments-348.json");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
