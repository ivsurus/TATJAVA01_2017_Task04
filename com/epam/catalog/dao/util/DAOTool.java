package com.epam.catalog.dao.util;


import com.epam.catalog.dao.pool.exeption.ConnectionPoolException;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.pool.ConnectionPool;

public class DAOTool {

    private DAOTool(){}

    public static void destroy() throws DAOException {
        ConnectionPool pool;
        try {
            pool = ConnectionPool.getInstance();
            pool.cleanUp();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    public static void init() throws DAOException {
        ConnectionPool pool;
        try {
            pool = ConnectionPool.getInstance();
            pool.init();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }
}
