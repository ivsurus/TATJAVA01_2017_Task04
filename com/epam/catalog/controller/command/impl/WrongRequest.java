package com.epam.catalog.controller.command.impl;

import com.epam.catalog.controller.command.Command;


public class WrongRequest implements Command {

    private final String FAILURE = "Try again to enter the correct command";

    @Override
    public String execute(String request) {
        return FAILURE;
    }
}
