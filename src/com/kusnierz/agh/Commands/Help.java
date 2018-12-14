package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Storage;

public class Help implements ICommand{

    @Override
    public String Execute(String args, Storage storage) {
        return "rubrum - wyświetlenie metryki jednego lub wielu orzeczeń, na podstawie sygnatury (pierwsza wartość w polu courtCases)\n" +
                "content - wyświetlenie uzasadnienia (czyli treści pola textContent)\n" +
                "judge - wyświetlał liczbę orzeczeń dla wybranego sędziego\n" +
                "judges - wyświetla 10 sędziów, którzy wydali najwięcej orzeczeń\n" +
                "months - wyświetlał liczbę orzeczeń w poszczególnych miesiącach (rozkład statystyczny)\n" +
                "courts - wyświetlał liczbę orzeczeń ze względu na typ sądu (rozkład statystyczny)\n" +
                "regulations - wyświetlał 10 najczęściej przywoływanych ustaw\n" +
                "jury - wyświetlał liczbę spraw przypadających na określony skład sędziowski (określoną liczbę sędziów)";
        /*
        return "sig - Zwróć metrykę po sygnaturze \n    sig I UK 388/10 I UK 388/10 \n\n"+
         "reason - Zwróć metrykę po uzasadnieniu (REASONS)\n   reason XVII AmC 1331/09 \n\n"+
         "judge -Zwróć metryki wszystkim spraw, w których uczestniczył sędzie \n  judge Wojciech Hermeliński \n\n"+
                "leaderboard - zwróć najlepszych sędziów \n"    +
                "month - zwróć rozkład na miesiące \n"+
                "courttype - zwróć rozkład na typy instancji sądów \n"+
                "refreg -zwróć najczęściej cytowane ustawy \n"+
                "average - zwróć średnią ilość sędziów na sprawę\n";
                */
    }
}
