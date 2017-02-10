package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.tools.DataBaseTools;
import com.epam.catalog.dao.util.DAOConstant;
import com.epam.catalog.dao.util.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class TxtBookDAO implements EntityDAO<Book> {

    private DataBaseTools dbTools = DataBaseTools.getInstance();


    @Override
    public void addEntity(Book book) throws DAOException {
       /* DBWorker dataBase = DBWorker.getInstance();
      //  String query = "Select * from table";
        try {
           // dataBase.changeDBData(query);
        } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | SQLException e) {
            throw new DAOException();
        }*/
    }

    @Override
    public Set<Book> findEntity(SearchRequest searchRequestObject) throws DAOException {
        DBWorker dataBase = DBWorker.getInstance();
        String query = getQuery(searchRequestObject);
        ResultSet resultSet;
        try {
            resultSet =  dataBase.getDBData(query);
            return createBookSet(resultSet);
        } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | SQLException e) {
            throw new DAOException(e);
        }
    }

    //сделаем перегрузочку
    //author$%$pushkin                - request parameters
    private String getQuery(SearchRequest searchRequestObject){
        String request = searchRequestObject.getRequestParameters();
        String parameterName = request.split(DAOConstant.DELIMITER)[0];
        String parameterValue = request.split(DAOConstant.DELIMITER)[1];
        String query = DAOConstant.SELECT_FROM_BOOK_TABLE +
                        parameterName + DAOConstant.EQUALS_SIGN +
                        DAOConstant.SINGLE_QUOTE + parameterValue +
                        DAOConstant.SINGLE_QUOTE + DAOConstant.SEMI_COLON;
       System.out.println(query);
        return query;
    }

    private String getQuery(Book book){
        //INSERT INTO `book` (`idbook`, `title`, `author`, `year`) VALUES ('3', 'slq', 'sur', '2017');
        String title = book.getTitle();
        String author = book.getAuthor();
        String year = book.getYear();
        int id = 5;
        String query = DAOConstant.INSERT_INTO_BOOK_TABLE + DAOConstant.SINGLE_QUOTE +
                id + DAOConstant.SINGLE_QUOTE + book.getTitle() + DA;
        return query;
    }

    private Set<Book> createBookSet (ResultSet resultSet) throws SQLException {
        Set<Book> bookSet = new HashSet<>();
        while (resultSet.next()) {
            bookSet.add(createBook(resultSet));
        }
       return bookSet;
    }

    private Book createBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setTitle(resultSet.getString(2));
        book.setAuthor(resultSet.getString(3));
        book.setYear(resultSet.getString(4));
        return book;
    }





}

