package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.Set;


public class FindBook implements Command {


    private Set<Book> books;
    private Book book;
    private StringBuilder builder = new StringBuilder();
    private ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
    private EntityService<Book> bookService = serviceObjectFactory.getBookService();
    private final String TITLE = "Title: ";
    private final String AUTHOR = "Author: ";
    private final String YEAR = "Year: ";
    private final String FAILURE = "Service is currently not available";
    private final String DELIMITER = "$%$";
    private final String CASE_AUTHOR = "AUTHOR";
    private final String CASE_TITLE = "TITLE";
    private final String CASE_YEAR = "YEAR";
    private String searchParameter;
    private String searchParameterValue;





    @Override
    public String execute(String request) {
        try{
          books =  bookService.findEntity(createBook(request));
        } catch (ServiceException e){
            return FAILURE;
        }
        return createResponseForUser(books);
    }


    //создаем непроверенный bean
    private Book createBook(String request){
        book = new Book();
        initSearchParameter(request);
        setSearchParameter();
        return book;
    }

    //вид поисковой строки
    // find_book$%$author$%$pushkin


    private void initSearchParameter(String request){
        String[] parameters = request.split(DELIMITER);
        searchParameter = parameters[1];
        searchParameterValue = parameters[2];
    }

    private void setSearchParameter(){
       switch (searchParameter) {
           case CASE_AUTHOR:
               book.setAuthor(searchParameterValue);
               break;
           case CASE_TITLE:
               book.setTitle(searchParameterValue);
               break;
           case CASE_YEAR:
               book.setYear(searchParameterValue);
               break;
        }
    }


    //формируем вывод для пользователя
    private String createResponseForUser(Set<Book> books){
        for (Book book: books){
            builder.append(TITLE + book.getTitle()+"\n");
            builder.append(AUTHOR + book.getAuthor()+"\n");
            builder.append(YEAR + book.getYear()+"\n\n");
        }
        return builder.toString();
    }

}
