package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.tools.DataBaseTools;
import com.epam.catalog.dao.util.DAOConstant;
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
    //author$%$pushkin                - request parameters
    private String getQuery(SearchRequest searchRequestObject){
        String request = searchRequestObject.getRequestParameters();
        String parameterName = request.split(DAOConstant.DELIMITER)[0];
        String parameterValue = request.split(DAOConstant.DELIMITER)[1];
        String query = "SELECT * FROM book WHERE" + parameterName + "=" +parameterValue;
        return query;
    }

    private String getQuery(Book book){
        String query = "";
        return query;
    }

}

