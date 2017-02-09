package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.controller.ControllerConstants;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.Set;

public class FindDisk implements Command{

    @Override
    public String execute(String request) {

        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        EntityService<Disk> diskService = serviceObjectFactory.getDiskService();
        SearchRequest searchRequestObject = new SearchRequest();
        searchRequestObject = initParameters(searchRequestObject,request);
        Set diskSet;
        try{
           diskSet =  diskService.findEntity(searchRequestObject);
        } catch (ServiceException e){
            return ControllerConstants.UNSUCCESSFUL_OPERATION;
            //log
        }
        return createResponseForUser(diskSet);
    }

    private String createResponseForUser(Set<Disk> diskSet){
        StringBuilder builder = new StringBuilder();
        for (Disk disk: diskSet){
            builder.append(ControllerConstants.TITLE + disk.getTitle()+"\n");
            builder.append(ControllerConstants.AUTHOR + disk.getAuthor()+"\n");
            builder.append(ControllerConstants.YEAR + disk.getYear()+"\n\n");
        }
        return builder.toString();
    }

    //"find_book$%$author$%$pushkin"  - all request
    //author$%$pushkin                - request parameters
    //сделать методом бина или сервисным методом
    private SearchRequest initParameters (SearchRequest searchRequestObject, String request){
        searchRequestObject.setRequestParameters(request.split(ControllerConstants.DELIMITER,2)[1]);
        return searchRequestObject;
    }
}
