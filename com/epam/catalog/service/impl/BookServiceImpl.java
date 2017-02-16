package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.constant.ServiceConstant;
import com.epam.catalog.service.util.ParameterValidator;

import java.util.*;

public class BookServiceImpl implements EntityService<Book> {


    @Override
    public void addEntity(Book book) throws ServiceException {
        boolean parametersAreValid = ParameterValidator.validateEntityParameters(book);
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
        }
    }

    @Override
    public Set<Book> findEntity(SearchRequest searchRequestObject) throws ServiceException {
        boolean parametersAreValid = ParameterValidator.validateSearchRequestParameters(searchRequestObject);
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
        }
        return bookSet;
   }

}