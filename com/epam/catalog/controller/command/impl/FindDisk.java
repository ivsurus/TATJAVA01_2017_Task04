package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.controller.util.ControllerConstant;
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
            return ControllerConstant.UNSUCCESSFUL_OPERATION;
            //log
        }
        return createResponseForUser(diskSet);
    }

    private String createResponseForUser(Set<Disk> diskSet){
        StringBuilder builder = new StringBuilder();
        for (Disk disk: diskSet){
            builder.append(ControllerConstant.TITLE + disk.getTitle()+"\n");
            builder.append(ControllerConstant.AUTHOR + disk.getAuthor()+"\n");
            builder.append(ControllerConstant.YEAR + disk.getYear()+"\n\n");
        }
        return builder.toString();
    }

    //"find_book$%$author$%$pushkin"  - all request
    //author$%$pushkin                - request parameters
    //сделать методом бина или сервисным методом
    private SearchRequest initParameters (SearchRequest searchRequestObject, String request){
        searchRequestObject.setRequestParameters(request.split(ControllerConstant.DELIMITER,2)[1]);
        return searchRequestObject;
    }
}
