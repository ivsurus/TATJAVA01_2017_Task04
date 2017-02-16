package com.epam.catalog.controller;

import com.epam.catalog.controller.command.Command;
import com.epam.catalog.controller.constant.ControllerConstant;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.util.ParameterValidator;
import com.epam.catalog.service.util.ServiceTool;

public final class Controller implements LifeCircle{

    private final CommandProvider provider = new CommandProvider();

    public String executeTask(String request){
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(ControllerConstant.DELIMITER));
        executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(request);
        return response;
    }

    @Override
    public void init() {
        try {
            ServiceTool.init();
        } catch (ServiceException e) {
            //////log
        }
    }

    @Override
    public String destroy () {
        try {
            ServiceTool.destroy();

        } catch (ServiceException e) {
            return ControllerConstant.UNSUCCESSFUL_DESTROY;
        }
        return ControllerConstant.SUCCESSFUL_DESTROY;
    }
}
