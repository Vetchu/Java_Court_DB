package com.kusnierz.agh.Presentation;

import com.kusnierz.agh.Commands.*;
import com.kusnierz.agh.Data.DataLoader;
import com.kusnierz.agh.Data.Storage;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    private ConsoleEmulator(List<String> filePaths, String prompt, String outFilePath) throws IOException {
        this.prompt=prompt;

        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        System.out.println("Terminal initialized, loading sentences...");
        String line;
        DataLoader loader=new DataLoader();
        Storage storage=loader.loadData(filePaths);
        terminal.writer().println("Sentences loaded from specified directories. System is ready for action. \n" +
                "To see the list of available commands, type 'help'");
        LoadCommands();
        FileWriter fr=null;
        BufferedWriter br = null;


        while(true) {
            line = lineReader.readLine(this.prompt, (char) 0);
            if (line == "\n") continue;
            else if (line=="exit") break;
            String output = "";
            String[] command = line.split("\\s+", 2);
            ICommand dummy=CommandIndex.get(command[0].toLowerCase());
            if(dummy!=null) {
                if(command.length==1){
                    switch (command[0]){
                        case "rubrum":
                        case "content":
                        case "judge":
                        case "jury":
                            output="This command requires at least one argument";
                            break;
                        default:
                            output=dummy.Execute("",storage);
                            break;
                    }
                }
                else {
                    output=dummy.Execute(command[1].toLowerCase(), storage);

                }
                System.out.println(output);
            }
            else{
                output="Incorrect command. If you wish to know the commands, type 'HELP'";
                terminal.writer().println(output);
            }
            if(!outFilePath.equals("")) {
                File file = new File(outFilePath);
                fr= new FileWriter(file, true);
                br = new BufferedWriter(fr);
                br.write(line+"\n");
                br.write(output+"\n");
                br.close();
                fr.close();
            }
        }

    }

    public static void main(String[] args) {
        List<String> filepaths= Arrays.asList("HTML","Json");

        try {
            new ConsoleEmulator(filepaths,"prompt> ",args[0]);
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
