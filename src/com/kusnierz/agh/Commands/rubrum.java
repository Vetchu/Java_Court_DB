package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Judgment;
import com.kusnierz.agh.Data.Storage;

import java.util.LinkedList;

public class rubrum implements ICommand{

    @Override
    public String Execute(String command, Storage storage) {
        //String liner="I UK 388/10 I UK 388/10";
        String[] commands=command.split("\\s+");
        if(commands.length<2) return "badargument";
        String[] sigs=new String[commands.length/3];

        for(int i=0;i<commands.length;i++){
            if(i%3==0)sigs[(i)/3]="";
            sigs[i/3]+=i%3==2 ? commands[i] : commands[i]+" ";
        }

        StringBuilder base= new StringBuilder();
        for (String sig:sigs) {
            LinkedList<Judgment> a = storage.signatureIndex.getByString(sig);
            if(a!=null) {
                for (Judgment judgment : a)
                    base.append(judgment.toString());
            }
            else
                base.append("Could not find any case signed ").append(sig).append("\n");
        }
        return base.toString();
    }
}
