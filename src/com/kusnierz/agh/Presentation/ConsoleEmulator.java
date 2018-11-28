package com.kusnierz.agh.Presentation;

import com.kusnierz.agh.Data.CourtCase;
import com.kusnierz.agh.Data.DataLoader;
import com.kusnierz.agh.Data.StorageSystem;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleEmulator {


    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        String line;
        StorageSystem storage=new StorageSystem();

        DataLoader loader=new DataLoader();
        try {
            storage=loader.loadData("./Json/judgments-348.json");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (CourtCase a:storage.SignatureHash.values()){
            System.out.println(a);
        }



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
    }
}
