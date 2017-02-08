package com.epam.catalog.dao.impl;


import com.epam.catalog.bean.Movie;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.tools.DataBaseTools;

import java.io.IOException;
import java.util.Set;


public class TxtMovieDAO implements EntityDAO<Movie> {

    private DataBaseTools dbTools = DataBaseTools.getInstance();
    private final String IDENTIFIER = "m";
    private final String DELIMITER = "$%$";

    @Override
    public void addEntity(Movie movie) throws DAOException {
        try {
            dbTools.writeToDB(IDENTIFIER+DELIMITER+movie.getTitle()+DELIMITER+movie.getAuthor()
                    +DELIMITER+movie.getYear()+"\n");
        } catch (IOException e){
            throw new DAOException(e);
        }
    }

    @Override
    public Set<String> findEntity() throws DAOException{
        try {
            return dbTools.delUnnecessaryData(dbTools.readFromDB(), IDENTIFIER);
        } catch (IOException e){
            throw new DAOException(e);
        }
    }
}



