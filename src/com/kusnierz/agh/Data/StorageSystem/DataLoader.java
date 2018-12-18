package com.kusnierz.agh.Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class DataLoader {
    public Storage loadData(List<String> dirPaths) throws IOException {
        Storage storage = new Storage();
        String workingDir = System.getProperty("user.dir");

        for(String dir:dirPaths) {
            try (Stream<Path> paths = Files.walk(Paths.get(workingDir+"/"+dir))) {
                paths
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                for (Judgment judgment : parseData(String.valueOf(path))) {
                                    storage.insert(judgment);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
            }

        }
            return storage;
        }

    private List<Judgment> parseData(String path) throws Exception {
        String[] filename=path.split("/");
        String[] type=filename[filename.length-1].split("\\.");
        List<Judgment> judgments;
        switch (type[type.length-1]){
            case "json":
                JSONParser parser= new JSONParser();
                JSONObject json = (JSONObject) parser.parse(new FileReader(path));
                JSONArray items= (JSONArray) json.get("items");
                judgments= jsonCourtCaseFactory(items);
                break;
            case "html":
                File htmlfile=new File(path);
                Document doc= Jsoup.parse(htmlfile,"UTF-8","");
                judgments=htmlCourtCaseFactory(doc);
                break;
            default:
                throw new Exception("Unable to parse file "+path);
        }
        return judgments;
    }

    private List<Judgment> jsonCourtCaseFactory(JSONArray items) {
        List<Judgment> judgmentFactory = new ArrayList<>();
        for (Object rawobject: items) {
            JSONObject object=(JSONObject) rawobject;

            Judgment judgment =new Judgment();
            judgment.Id =  ((Long)  object.get("id")).intValue();
            judgment.Signature=(String) ((JSONObject)((JSONArray) object.get("courtCases")).get(0)).get("caseNumber");
            judgment.Date= LocalDate.parse((String) object.get("judgmentDate"));
            switch(((String) object.get("courtType")).toLowerCase()){
                case "common":
                    judgment.CourtType = CourtType.common;
                    break;
                case "national_appeal_chamber":
                    judgment.CourtType = CourtType.national_appeal_chamber;
                    break;
                case "constitutional_tribunal":
                    judgment.CourtType = CourtType.tribunal;
                    break;
                case "supreme":
                    judgment.CourtType = CourtType.supreme;
                    break;
            }
            judgment.JudgmentType = (String) object.get("judgmentType");
            judgment.content = (String) object.get("textContent");

            JSONArray judgeArray= (JSONArray) object.get("judges");
            for(Object rawjudge:judgeArray){
                 Judge judge=new Judge();
                 judge.name=(String) ((JSONObject)rawjudge).get("name");
                 judge.specialRole=  ((JSONArray)((JSONObject)rawjudge).get("specialRoles")).size()>0?
                         (String)((JSONArray) ((JSONObject)rawjudge).get("specialRoles")).get(0) : null;
                 judgment.Jugdes.add(judge);
            }
            JSONArray referencedRegulationsArray= (JSONArray) object.get("referencedRegulations");
            for(Object rawRegulation:referencedRegulationsArray){
                String regulation=(String) ((JSONObject)rawRegulation).get("journalTitle");
                Long JournalNumber=(Long) ((JSONObject)rawRegulation).get("journalNo");
                Long Year=(Long) ((JSONObject)rawRegulation).get("journalYear");
                Long Entry=(Long) ((JSONObject)rawRegulation).get("journalEntry");
                judgment.Refs.add(new Regulation(JournalNumber,Year,Entry,regulation));
            }
            judgmentFactory.add(judgment);
        }
        return judgmentFactory;
    }

    private List<Judgment> htmlCourtCaseFactory(Document doc){
        Element body = doc.body();
        List<Element> listavalues=body.getElementsByClass("niezaznaczona");

        Judgment judgment =new Judgment();
        //signature
        String signature=body.getElementsByClass("war_header").get(0).html().split(" -")[0];
        //judgmentDate
        String judgmentDate="";
        //courtType
        String courtType="";
        //judges
        LinkedList<Judge> finaljudges=new LinkedList<>();

        for(Element niezaznaczon:listavalues){
            switch (niezaznaczon.getElementsByClass("lista-label").html()){
                case "Data orzeczenia":
                    judgmentDate= niezaznaczon.getElementsByClass("info-list-value").get(0).getElementsByTag("td").get(1).html();
                    break;
                case "Sąd":
                    courtType=niezaznaczon.getElementsByClass("info-list-value").html().split(" ")[0];
                    break;
                case "Sędziowie":
                    String[] protojudges= niezaznaczon.getElementsByClass("info-list-value").html().split("<br>");
                    for(String judge:protojudges){
                        Judge newjudge = new Judge();
                        String[] a=judge.split("/");
                        if(a.length>1){
                            newjudge.specialRole=a[1];
                        }
                        newjudge.name=a[0];
                        finaljudges.add(newjudge);
                    }
                    break;
            }
        }

        //refs
        Elements protoRefs= body.getElementsByClass("nakt");
        LinkedList<Regulation> finalRefs=new LinkedList<>();
        for(Element ref:protoRefs){
            String sref=ref.html();
            sref=sref.split("\\s*-\\s*")[0];
            finalRefs.add(new Regulation(null,null,null,sref));
        }
        //content
        String content=body.getElementsByClass("info-list-value-uzasadnienie").get(0).child(0).html();

        judgment.Signature=signature;
        judgment.Date= LocalDate.parse(judgmentDate);
        judgment.CourtType = courtType.equals("Wojewódzki") ?CourtType.common_administrative : CourtType.national_administrative;
        judgment.content = content;
        judgment.Jugdes=finaljudges;
        judgment.Refs=finalRefs;

        List<Judgment> judgments=new ArrayList<>();
        judgments.add(judgment);


        return judgments;
    }
}
