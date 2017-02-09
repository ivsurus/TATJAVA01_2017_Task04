package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.util.PatternRepository;
import com.epam.catalog.service.util.ServiceConstant;

import java.util.*;

public class BookServiceImpl implements EntityService<Book> {


    @Override
    public void addEntity(Book book) throws ServiceException {
        boolean parametersAreValid = validateEntityParamenters(book);
        if (parametersAreValid) {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            EntityDAO<Book> bookDAO = daoObjectFactory.getBookDAO();
            try {
                bookDAO.addEntity(book);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException(ServiceConstant.INVALID_PARAMETERS); //тут хорошо подумать
            // log
        }
    }


    @Override
    public Set<Book> findEntity(SearchRequest searchRequestObject) throws ServiceException {
        boolean parametersAreValid = validateSearchRequestParamenters(searchRequestObject);
        Set<Book> bookSet;
        if (parametersAreValid) {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            EntityDAO<Book> bookDAO = daoObjectFactory.getBookDAO();
            try {
                bookSet = bookDAO.findEntity(searchRequestObject);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException(ServiceConstant.INVALID_PARAMETERS);
            //log
        }
        return bookSet;
   }

   //проверить на null
   // попробовать передалать в цикле
   private boolean validateEntityParamenters (Book book){
        return book.getAuthor().matches(ServiceConstant.AUTHOR_PATTERN) &&
                book.getTitle().matches(ServiceConstant.TITLE_PATTERN) &&
                book.getYear().matches(ServiceConstant.YEAR_PATTERN);
   }

        //author$%$pushkin                - request parameters
       private boolean validateSearchRequestParamenters (SearchRequest searchRequestObject){
       String parameterName = searchRequestObject.getRequestParameters()
                                .split(ServiceConstant.DELIMITER,2)[0];
       String parameterValue = searchRequestObject.getRequestParameters()
                                .split(ServiceConstant.DELIMITER,2)[1];
       return validate(parameterName,parameterValue);
       }


    private boolean validate (String parameterName, String parameterValue){
        PatternRepository PatternRepositoryObject = PatternRepository.getInstance();
        Map patternRepository = PatternRepositoryObject.getPatternRepository();
        return parameterValue.matches((String)patternRepository.get(parameterName));
    }

}