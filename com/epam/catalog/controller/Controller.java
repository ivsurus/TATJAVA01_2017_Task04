package com.epam.catalog.controller;

import com.epam.catalog.controller.command.Command;

public final class Controller {

    private final CommandProvider provider = new CommandProvider();
    private final String paramDelimiter = "$%$";

    public String executeTask(String request){
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(paramDelimiter));
        executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(request);
        return response;
    }
}
