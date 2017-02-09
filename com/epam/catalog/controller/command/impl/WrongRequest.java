package com.epam.catalog.controller.command.impl;

import com.epam.catalog.controller.util.ControllerConstant;
import com.epam.catalog.controller.command.Command;


public class WrongRequest implements Command {

    @Override
    public String execute(String request) {
        return ControllerConstant.WRONG_REQUEST;
    }
}
