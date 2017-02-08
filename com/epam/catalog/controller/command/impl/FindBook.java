package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.Set;


public class FindBook implements Command {


    private Set books;
    private StringBuilder builder = new StringBuilder();
    private ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
    private EntityService<Book> bookService = serviceObjectFactory.getBookService();
    private final String TITLE = "Title: ";
    private final String AUTHOR = "Author: ";
    private final String YEAR = "Year: ";
    private final String FAILURE = "Service is currently not available";

    @Override
    public String execute(String request) {
        try{
          books =  bookService.findEntity(request);
        } catch (ServiceException e){
            return FAILURE;
        }
        return createResponseForUser(books);
    }


    private String createResponseForUser(Set<Book> books){
        for (Book book: books){
            builder.append(TITLE + book.getTitle()+"\n");
            builder.append(AUTHOR + book.getAuthor()+"\n");
            builder.append(YEAR + book.getYear()+"\n\n");
        }
        return builder.toString();
    }

}
