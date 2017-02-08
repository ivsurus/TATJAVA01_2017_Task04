package com.epam.catalog.dao.factory;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.Movie;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.impl.TxtBookDAO;
import com.epam.catalog.dao.impl.TxtDiskDAO;
import com.epam.catalog.dao.impl.TxtMovieDAO;

public final class DAOFactory {


    private static final DAOFactory instance = new DAOFactory();

    private final EntityDAO<Book> txtBookDAOImpl = new TxtBookDAO();
    private final EntityDAO<Movie> txtMovieDAOImpl = new TxtMovieDAO();
    private final EntityDAO<Disk> txtDiskDAOImpl = new TxtDiskDAO();

    //singleton
    private DAOFactory(){}


    public static DAOFactory getInstance(){
        return instance;
    }

    public EntityDAO<Book> getBookDAO(){
        return txtBookDAOImpl;
    }
    public EntityDAO<Movie> getMovieDAO(){
        return txtMovieDAOImpl;
    }
    public EntityDAO<Disk> getDiskDAO(){
        return txtDiskDAOImpl;
    }

}
