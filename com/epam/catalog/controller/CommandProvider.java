package com.epam.catalog.controller;

import com.epam.catalog.controller.command.Command;
import com.epam.catalog.controller.command.impl.*;
import java.util.HashMap;
import java.util.Map;

final public class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.FIND_BOOK, new FindBook());
        repository.put(CommandName.ADD_DISK, new AddDisk());
        repository.put(CommandName.FIND_DISK, new FindDisk());
        repository.put(CommandName.ADD_MOVIE, new AddMovie());
        repository.put(CommandName.FIND_MOVIE, new FindMovie());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }


    Command getCommand(String name){
        CommandName commandName;
        Command command;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
