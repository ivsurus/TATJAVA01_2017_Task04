package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.controller.constant.ControllerConstant;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public final class AddDisk implements Command{




    @Override
    public String execute(String request) {
        Disk disk = new Disk();
        disk = initParameters(disk, request);
        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        EntityService<Disk> diskService = serviceObjectFactory.getDiskService();
        try{
            diskService.addEntity(disk);
        } catch (ServiceException e){
            return ControllerConstant.UNSUCCESSFUL_OPERATION;
        } catch (Exception e){
            return ControllerConstant.UNSUCCESSFUL_OPERATION;
        }
        return ControllerConstant.SUCCESSFUL_OPERATION;
    }

    private Disk initParameters (Disk disk, String request){
        String[] parameters = request.split(ControllerConstant.DELIMITER);
        disk.setPrice(parameters[1]);
        disk.setTitle(parameters[2]);
        disk.setGenre(parameters[3]);
        disk.setYear(parameters[4]);
        return disk;
    }
}
