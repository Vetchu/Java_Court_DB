package com.kusnierz.agh;

import com.kusnierz.agh.Presentation.ConsoleEmulator;


public class Main {
    public static void main(String[] args){
        if(args.length<3)
        ConsoleEmulator.main(new String[]{"output.txt","HTML","Json"});
        else
            ConsoleEmulator.main(args);

    }
}
