package com.epam.catalog.view;


import com.epam.catalog.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleMenu {


    private final static String INSTRUCTIONS ="Input any of the following commands to" +
            " add the news to the catalog:\n" +
            "1.about book: add%_%book%_%author%_%title%_%genre%_%year\n"+
            "2.about disk: add%_%disk%_%price%_%title%_%genre%_%year\n"+
            "3.about movie: add%_%movie%_%producer%_%title%_%genre%_%year\n\n"+
            "Input any of the following commands to find the news in the catalog:\n" +
            "1.about book: find%_%book%_%parameterName(author/title/genre/year)%_%parameterValue\n"+
            "2.about disk: find%_%disk%_%parameterName(price/title/genre/year)%_%parameterValue\n"+
            "3.about movie: find%_%movie%_%parameterName(producer/title/genre/year)%_%parameterValue\n";

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Controller controller = new Controller();


    public void start(){
        System.out.println(INSTRUCTIONS);
        System.out.println(controller.executeTask(readUserInput()));
        controller.destroy();
    }

    private String readUserInput(){
       String input = "";
        try {
            input = reader.readLine().toUpperCase();
        } catch (IOException e) {
            System.out.println(e);
        }
        return input;
    }
}
