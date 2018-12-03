package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Storage;

public class Help implements ICommand{

    @Override
    public String Execute(String args, Storage storage) {
        return "sig - Zwróć metrykę po sygnaturze \n    sig I UK 388/10 I UK 388/10 \n\n"+
         "reason - Zwróć metrykę po uzasadnieniu (REASONS)\n   reason XVII AmC 1331/09 \n\n"+
         "judge -Zwróć metryki wszystkim spraw, w których uczestniczył sędzie \n  judge Wojciech Hermeliński \n\n"+
                "leaderboard - zwróć najlepszych sędziów \n"    +
                "month - zwróć rozkład na miesiące \n"+
                "courttype - zwróć rozkład na typy instancji sądów \n"+
                "refreg -zwróć najczęściej cytowane ustawy \n"+
                "average - zwróć średnią ilość sędziów na sprawę\n";
    }
}
