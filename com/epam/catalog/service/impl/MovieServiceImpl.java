package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.Movie;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;

import java.util.Set;


public class MovieServiceImpl implements EntityService<Movie> {
    @Override
    public void addEntity(Movie movie) throws ServiceException {
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        EntityDAO<Movie> movieDAO = daoObjectFactory.getMovieDAO();
        try{
            movieDAO.addEntity(movie);
        } catch (DAOException e){
            throw new ServiceException(e);
        }

    }

    @Override
    public Set<Book> findEntity(String searchCriterion) throws ServiceException {
        return null;
    }


}
