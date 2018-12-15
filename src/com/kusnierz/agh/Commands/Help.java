package com.kusnierz.agh.Commands;

import com.kusnierz.agh.Data.Storage;

public class Help implements ICommand{

    @Override
    public String Execute(String args, Storage storage) {
        return "rubrum - wyświetla metryki jednego lub wielu orzeczeń , na podstawie sygnatur oddzielonych przecinkami (pierwsza wartość w polu courtCases)\n" +
                "content - wyświetla uzasadnienie (czyli treści pola textContent)\n" +
                "judge - wyświetla liczbę orzeczeń dla wybranego sędziego\n" +
                "judges - wyświetla 10 sędziów, którzy wydali najwięcej orzeczeń\n" +
                "months - wyświetla liczbę orzeczeń w poszczególnych miesiącach (rozkład statystyczny)\n" +
                "courts - wyświetla liczbę orzeczeń ze względu na typ sądu (rozkład statystyczny)\n" +
                "regulations - wyświetla 10 najczęściej przywoływanych ustaw\n" +
                "jury - wyświetla liczbę spraw przypadających na określony skład sędziowski (określoną liczbę sędziów)\n" +
                "help - wyświetla tą pomoc\n" +
                "exit - kończy działanie programu";
    }
}
