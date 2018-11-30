package com.kusnierz.agh.Presentation;

import com.kusnierz.agh.Commands.*;
import com.kusnierz.agh.Data.DataLoader;
import com.kusnierz.agh.Data.Storage;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleEmulator {


    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        String line;

        DataLoader loader=new DataLoader();
        Storage storage=loader.loadData("./Json");


        while((line=reader.nextLine())!=null) {

            String[] command=line.split("\\s+",2);
            System.out.println(command[0]);

            if(command.length>1)
                switch (command[0].toLowerCase()){
                case "sig":
                    System.out.println(new II().Command(command[1],storage));
                    break;
                case "reason":
                    System.out.println(new III().Command(command[1],storage));
                    break;
                    case "judge":
                        System.out.println(new V().Command(command[1],storage));
                        break;
                    case "leaderboard":
                        System.out.println(new VI().Command(command[1],storage));
                        break;
                    case "month":
                        System.out.println(new VII().Command(command[1],storage));
                        break;
                    case "courttype":
                        System.out.println(new VIII().Command(command[1],storage));
                        break;
                    case "refreg":
                        System.out.println(new VIIII().Command(command[1],storage));
                        break;
                    case "average":
                        System.out.println(new X().Command(command[1],storage));
                        break;

                    default:
                        System.out.println("Incorrect command");
            }
            else System.out.println("No arguments given");
            }

        //2.4. sig
        //command: getSig I UK 388/10 I UK 388/10
        //3. reason
        // reason XVII AmC 1331/09
        //4. getSig Above ^
        //5.judge
        // judge Wojciech Hermeliński
        //6.leaderboard
        // leaderboard
        //7. month
        // month
        //8. courttype
        // courttype
        //9. refreg
        // refreg
        //10.average
        // average

        /*
        //getYear
        LinkedList<Judgment> c=storage.getByYear(2010);
        if(c!=null)
            for(Judgment cc: c)
                System.out.println(cc);
        */
    }
}
