package com.kusnierz.agh.Presentation;

import com.kusnierz.agh.Commands.*;
import com.kusnierz.agh.Data.DataLoader;
import com.kusnierz.agh.Data.Storage;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.HashMap;

public class ConsoleEmulator {
    private String prompt;
    private HashMap<String, ICommand> CommandIndex=new HashMap<>();

    private void LoadCommands(){
        CommandIndex.put("sig",new II());
        CommandIndex.put("reason",new III());
        CommandIndex.put("judge",new V());
        CommandIndex.put("leaderboard",new VI());
        CommandIndex.put("month",new VII());
        CommandIndex.put("courttype",new VIII());
        CommandIndex.put("refreg",new VIIII());
        CommandIndex.put("average",new X());
        CommandIndex.put("help",new Help());
    }

    private ConsoleEmulator(String filePath, String prompt) throws IOException {
        this.prompt=prompt;

        Terminal terminal = TerminalBuilder.builder()
                .build();


        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        String line;
        DataLoader loader=new DataLoader();
        Storage storage=loader.loadData(filePath);
        terminal.writer().println("System ready for action");
        LoadCommands();

        while(true) {
            line = lineReader.readLine(this.prompt);

            if (line == "\n") continue;
            String[] command = line.split("\\s+", 2);
            ICommand dummy;
            if((dummy=CommandIndex.get(command[0].toLowerCase()))!=null) {
                if(command.length==1)
                System.out.println(dummy.Execute("",storage));
                else
                    System.out.println(dummy.Execute(command[1],storage));
            }
            else terminal.writer().println("Incorrect command");
        }
    }

    public static void main(String[] args) {
        try {
            new ConsoleEmulator("./Json","prompt> ");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        new ConsoleEmulator("args[0]","prompt> ");

        /*
        //getYear
        LinkedList<Judgment> c=storage.getByYear(2010);
        if(c!=null)
            for(Judgment cc: c)
                System.out.println(cc);
        */
    }
}
