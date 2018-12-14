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
        CommandIndex.put("rubrum",new rubrum());
        CommandIndex.put("content",new content());
        CommandIndex.put("judge",new judge());
        CommandIndex.put("judges",new judges());
        CommandIndex.put("months",new months());
        CommandIndex.put("courts",new courts());
        CommandIndex.put("regulations",new regulations());
        CommandIndex.put("jury",new jury());
        CommandIndex.put("help",new Help());
        CommandIndex.put("exit",new Exit());
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
            ICommand dummy=CommandIndex.get(command[0].toLowerCase());
            if(dummy!=null) {
                if(command.length==1)
                System.out.println(dummy.Execute("",storage));
                else
                    System.out.println(dummy.Execute(command[1].toLowerCase(),storage));
            }
            else terminal.writer().println("Incorrect command. If you wish to know the commands, type 'HELP'");
        }
    }

    public static void main(String[] args) {
        String filepath="./Json";
        if(args.length>0)
            try {
                new ConsoleEmulator(filepath,"prompt> ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            else
        try {
            new ConsoleEmulator(filepath,"prompt> ");
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
