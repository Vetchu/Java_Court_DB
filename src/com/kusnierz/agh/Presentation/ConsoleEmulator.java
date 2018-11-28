package com.kusnierz.agh.Presentation;

import com.kusnierz.agh.Data.CourtCase;
import com.kusnierz.agh.Data.DataLoader;
import com.kusnierz.agh.Data.Judge;
import com.kusnierz.agh.Data.StorageSystem;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class ConsoleEmulator {


    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        String line;
        StorageSystem storage=new StorageSystem();

        DataLoader loader=new DataLoader();
        try {
            storage=loader.loadData("./Json/judgments-348.json");
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }
        for (CourtCase a:storage.SignatureHash.values()){
           // System.out.println(a);
        }


/*
        while((line=reader.nextLine())!=null) {

            String[] command=line.split("\\s+",2);
            System.out.println(command[0]);

            if(command.length>1)
                switch (command[0]){
                case "getSig":
                    CourtCase a=storage.getBySignature(command[1]);
                    if(a!=null)
                    System.out.println(a.toString());
                    break;


                    default:
                        System.out.println("Incorrect command");
            }
            else System.out.println("No arguments given");
            }
            */
/*
        //getSig
        CourtCase a=storage.getBySignature("I UK 388/10");
        if(a!=null)
            System.out.println(a.toString());
                    //getYear
        LinkedList<CourtCase> c=storage.getByYear(2010);
        if(c!=null)
        for(CourtCase cc: c)
            System.out.println(cc);
*/
        //getMonth
        Month z= Month.of(Month.valueOf("JANUARY".toUpperCase()).getValue());
        LinkedList<CourtCase> b=storage.getByMonth(z);
        if(b!=null)
            for(CourtCase cc: b)
        System.out.println(cc);



        //getJudge
        LinkedList<CourtCase> d=storage.getByJudge("Wojciech Katner");
        if(d!=null)
            for(CourtCase cc: d)
                System.out.println(cc);

        //leaderBoard
        Map<String,Integer> e = new HashMap<>();
        for(String judge:storage.JudgeHash.keySet()){
            e.put(judge,storage.getByJudge(judge).size());
        }
        Map<String,Integer> sorted=e.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        Integer g=1;
        for(String sort:sorted.keySet()){
            if(g<11)
            System.out.println(g++ +" "+ sort+" "+sorted.get(sort));
        }

    }
}
