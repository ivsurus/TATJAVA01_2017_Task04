package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.controller.util.ControllerConstant;
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
            return ControllerConstant.UNSUCCESSFUL_OPERATION;
        }
        return createResponseForUser(bookSet);
    }

    private String createResponseForUser(Set<Book> bookSet){
        StringBuilder builder = new StringBuilder();
        for (Book book: bookSet){
            builder.append(ControllerConstant.AUTHOR + book.getAuthor()+"\n");
            builder.append(ControllerConstant.TITLE + book.getTitle()+"\n");
            builder.append(ControllerConstant.GENRE + book.getGenre()+"\n");
            builder.append(ControllerConstant.YEAR + book.getYear()+"\n\n");
        }
        return builder.toString();
    }


    private SearchRequest initParameters (SearchRequest searchRequestObject, String request){
        searchRequestObject.setRequestParameters(request.split(ControllerConstant.DELIMITER,2)[1]);
        return searchRequestObject;
    }

}
