package com.epam.catalog.view;


import com.epam.catalog.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleMenu {



    private final String MESSAGE_1 = "Enter a command: (add find)";
    private final String MESSAGE_2 = "Enter a category: (book disk movie)";
    private final String MESSAGE_3 = "Enter a title:";
    private final String MESSAGE_4 = "Enter an author :";
    private final String MESSAGE_5 = "Enter a year:";
    private final String MESSAGE_6 = "Enter a criterion for a search:";
    private final String DELIMITER = "_";
    private final String PARAM_DELIMITER = "$%$";
    private final String TITLE = "TITLE";
    private final String AUTHOR = "AUTHOR";
    private final String YEAR = "YEAR";
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringBuilder builder;
    private Controller controller = new Controller();




    public void start(){

      //  System.out.println(controller.executeTask(readUserEntityToAdd()));
        System.out.println(controller.executeTask(readUserEntityToFind()));
    }

    private String readUserEntityToAdd(){
        builder = new StringBuilder();
        System.out.println(MESSAGE_1);
        builder.append(readUserInput());
        builder.append(DELIMITER);
        System.out.println(MESSAGE_2);
        builder.append(readUserInput());
        builder.append(PARAM_DELIMITER);
        System.out.println(MESSAGE_3);
        builder.append(readUserInput());
        builder.append(PARAM_DELIMITER);
        System.out.println(MESSAGE_4);
        builder.append(readUserInput());
        builder.append(PARAM_DELIMITER);
        System.out.println(MESSAGE_5);
        builder.append(readUserInput());
        return builder.toString();
    }
    private String readUserEntityToFind(){
        builder = new StringBuilder();
        System.out.println(MESSAGE_1);
        builder.append(readUserInput());
        builder.append(DELIMITER);
        System.out.println(MESSAGE_2);
        builder.append(readUserInput());
        builder.append(PARAM_DELIMITER);
        System.out.println(MESSAGE_6);
        builder.append(readUserInput());
        builder.append(PARAM_DELIMITER);
        builder.append(YEAR);
        return builder.toString();
    }



    private String readUserInput(){
       String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        return input;
    }
}
