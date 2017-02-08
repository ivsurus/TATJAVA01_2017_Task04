package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.Set;

public class FindDisk implements Command{

    private Set disks;
    private StringBuilder builder = new StringBuilder();
    private ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
    private EntityService<Disk> diskService = serviceObjectFactory.getDiskService();
    private final String TITLE = "Title: ";
    private final String AUTHOR = "Author: ";
    private final String YEAR = "Year: ";
    private final String FAILURE = "Service is currently not available";

    @Override
    public String execute(String request) {
        try{
            disks =  diskService.findEntity(request);
        } catch (ServiceException e){
            return FAILURE;
        }
        return createResponseForUser(disks);
    }

    private String createResponseForUser(Set<Disk> disks){
        for (Disk disk: disks){
            builder.append(TITLE + disk.getTitle()+"\n");
            builder.append(AUTHOR + disk.getAuthor()+"\n");
            builder.append(YEAR + disk.getYear()+"\n\n");
        }
        return builder.toString();
    }
}
