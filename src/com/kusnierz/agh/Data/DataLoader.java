package com.kusnierz.agh.Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataLoader {
    public Storage loadData(String dirPath) throws IOException {
        Storage storage = new Storage();
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            for (Judgment judgment : parseData(String.valueOf(path))) {
                                    storage.insert(judgment);
                                }
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    });
        }

            return storage;
        }

    private List<Judgment> parseData(String path) throws IOException, ParseException {
        JSONParser parser= new JSONParser();
        JSONObject json = (JSONObject) parser.parse(new FileReader(path));
        JSONArray items= (JSONArray) json.get("items");
        return courtCaseFactory(items);
    }

    private List<Judgment> courtCaseFactory(JSONArray items) {
        List<Judgment> judgmentFactory = new ArrayList<>();
        for (Object rawobject: items) {
            JSONObject object=(JSONObject) rawobject;

            Judgment judgment =new Judgment();
            judgment.Id =  ((Long)  object.get("id")).intValue();
            judgment.Signature=(String) ((JSONObject)((JSONArray) object.get("courtCases")).get(0)).get("caseNumber");
            judgment.Date= LocalDate.parse((String) object.get("judgmentDate"));
            judgment.CourtType = (String) object.get("courtType");
            judgment.JudgmentType = (String) object.get("judgmentType");

            JSONArray judgeArray= (JSONArray) object.get("judges");
            for(Object rawjudge:judgeArray){
                 Judge judge=new Judge();
                 judge.name=(String) ((JSONObject)rawjudge).get("name");
                 judge.specialRole=  ((JSONArray)((JSONObject)rawjudge).get("specialRoles")).size()>0?(String)((JSONArray) ((JSONObject)rawjudge).get("specialRoles")).get(0) : null;
                 judgment.Jugdes.add(judge);
            }
            JSONArray referencedRegulationsArray= (JSONArray) object.get("referencedRegulations");
            for(Object rawRegulation:referencedRegulationsArray){
                String regulation=(String) ((JSONObject)rawRegulation).get("journalTitle");
                judgment.Refs.add(regulation);
            }
            judgmentFactory.add(judgment);
        }
        return judgmentFactory;
    }


    public static void main(String[] main){
        DataLoader dataLoader=new DataLoader();
        try {
            dataLoader.loadData("./Json/judgments-348.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
