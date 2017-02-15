package com.epam.catalog.dao.util;


import com.epam.catalog.dao.exeption.ConnectionPoolException;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.pool.ConnectionPool;

public class DAOTool {

    public static void destroy() throws DAOException {
        ConnectionPool pool = null;
        try {
            pool = ConnectionPool.getInstance();
            pool.cleanUp();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }
}
