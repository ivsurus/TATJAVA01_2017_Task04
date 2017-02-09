package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.controller.ControllerConstants;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.Set;


public class FindBook implements Command {


    @Override
    public String execute(String request) {

        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        EntityService<Book> bookService = serviceObjectFactory.getBookService();
        SearchRequest searchRequestObject = new SearchRequest();
        searchRequestObject = initParameters(searchRequestObject,request);
        Set bookSet;
        try{
            bookSet =  bookService.findEntity(searchRequestObject);
        } catch (ServiceException e){
            return ControllerConstants.UNSUCCESSFUL_OPERATION;
            //log
        }
        return createResponseForUser(bookSet);
    }

    private String createResponseForUser(Set<Book> bookSet){
        StringBuilder builder = new StringBuilder();
        for (Book book: bookSet){
            builder.append(ControllerConstants.TITLE + book.getTitle()+"\n");
            builder.append(ControllerConstants.AUTHOR + book.getAuthor()+"\n");
            builder.append(ControllerConstants.YEAR + book.getYear()+"\n\n");
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
