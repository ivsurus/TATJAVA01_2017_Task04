package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.util.ServiceConstant;
import com.epam.catalog.service.util.ServiceTool;

import java.util.*;

public class BookServiceImpl implements EntityService<Book> {


    @Override
    public void addEntity(Book book) throws ServiceException {
        boolean parametersAreValid = ServiceTool.validateEntityParameters(book);
        if (parametersAreValid) {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            EntityDAO<Book> bookDAO = daoObjectFactory.getBookDAO();
            try {
                bookDAO.addEntity(book);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException(ServiceConstant.INVALID_PARAMETERS);
            // log
        }
    }

    @Override
    public Set<Book> findEntity(SearchRequest searchRequestObject) throws ServiceException {
        boolean parametersAreValid = ServiceTool.validateSearchRequestParameters(searchRequestObject);
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

}