package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;


public class AddBook implements Command {

    private final String DELIMITER = "\\$%\\$";
    private final String SUCCESS = "The book was successfully added to the catalog";
    private final String FAILURE = "Service is currently not available";

    @Override
    public String execute(String request) {
        Book book = new Book();
        String[] parameters = request.split(DELIMITER);
        book.setTitle(parameters[1]);
        book.setAuthor(parameters[2]);
        book.setYear(parameters[3]);
        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        EntityService<Book> bookService = serviceObjectFactory.getBookService();
        try{
            bookService.addEntity(book);
        } catch (ServiceException e){
            return FAILURE;
        }
        return SUCCESS;
    }
}
