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
    public StorageSystem loadData(String path) throws IOException, ParseException {
        StorageSystem storage=new StorageSystem();
        for(CourtCase courtCase:parseData(path)){
            storage.putBySignature(courtCase);
        }
        return storage;
    }
    private List<CourtCase> parseData(String path) throws IOException, ParseException {
        JSONParser parser= new JSONParser();
        JSONObject json = (JSONObject) parser.parse(new FileReader(path));
        JSONArray items= (JSONArray) json.get("items");
        return convertToJudgmentList(items);
    }

    private List<CourtCase> convertToJudgmentList(JSONArray items){
        List<CourtCase> judgArray = new ArrayList<>();
        for (Object rawobject: items) {
            JSONObject object=(JSONObject) rawobject;

            CourtCase courtCase =new CourtCase();
            courtCase.Id =  ((Long)  object.get("id")).intValue();
            courtCase.Signature=(String) ((JSONObject)((JSONArray) object.get("courtCases")).get(0)).get("caseNumber");
            courtCase.Date = (String) object.get("judgmentDate");
            courtCase.CourtType = (String) object.get("courtType");
            courtCase.Jugdes = new LinkedList<Judge>();
            JSONArray judgeArray= (JSONArray) object.get("judges");
            for(Object rawjudge:judgeArray){
                 Judge judge=new Judge();
                 judge.name=(String) ((JSONObject)rawjudge).get("name");
                 judge.specialRole= ((JSONArray) ((JSONObject)rawjudge).get("specialRoles"))==null?(String)((JSONArray) ((JSONObject)rawjudge).get("specialRoles")).get(0) : null;
                 courtCase.Jugdes.add(judge);
            }
            judgArray.add(courtCase);

            System.out.println(courtCase.Id+courtCase.Signature+courtCase.Date+courtCase.CourtType+courtCase.Jugdes);
        }
        return judgArray;
    }


    public static void main(String[] main){
        DataLoader dataLoader=new DataLoader();
        try {

            /*
            try (Stream<Path> paths = Files.walk(Paths.get("./Json"))) {
                paths
                        .filter(Files::isRegularFile)
                        //.forEach(System.out::println);
                        .forEach(path -> {
                            try {
                                System.out.println(Files.readAllLines(path));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

            }
            */

            dataLoader.loadData("./Json/judgments-348.json");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
