package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.tools.DataBaseTools;
import com.epam.catalog.dao.util.DBWorker;
import java.sql.SQLException;
import java.util.Set;


public class TxtBookDAO implements EntityDAO<Book> {

    private DataBaseTools dbTools = DataBaseTools.getInstance();


    @Override
    public void addEntity(Book book) throws DAOException {
        DBWorker dataBase = DBWorker.getInstance();
        String query = "Select * from table";
        try {
            dataBase.changeDBData(query);
        } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | SQLException e) {
            throw new DAOException();
        }
    }


    @Override
    public Set<Book> findEntity(SearchRequest searchRequestObject) throws DAOException {
        DBWorker dataBase = DBWorker.getInstance();
        String query = "Select * from table";
        try {
            dataBase.getDBData(query);
        } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | SQLException e) {
            throw new DAOException();
        }
        return null;
    }

    //сделаем перегрузочку
    private String getQuery(SearchRequest searchRequestObject){
        String query = "";
        return query;
    }

    private String getQuery(Book book){
        String query = "";
        return query;
    }

}

