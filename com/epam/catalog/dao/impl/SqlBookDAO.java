package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.pool.ConnectionPool;
import com.epam.catalog.dao.exeption.ConnectionPoolException;
import com.epam.catalog.dao.util.DAOConstant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class SqlBookDAO implements EntityDAO<Book> {

    private final static String INSERT_TO_BOOK_TABLE =
            "INSERT INTO catalog.book (author,title,genre,year) VALUES (?,?,?,?);";
    private final static String SELECT_FROM_BOOK_TABLE =
            "SELECT * FROM catalog.book WHERE  = ?;";
    private final static int PARAMETER_INSERT_INDEX_FOR_SELECT =33;

    @Override
    public void addEntity(Book book) throws DAOException {

        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            writeToDataBase(book,connection);
            pool.returnConnection(connection);
        } catch  (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        }
    }

    @Override
    public Set<Book> findEntity(SearchRequest searchRequestObject) throws DAOException {

        ResultSet resultSet;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            resultSet = findInDataBase(searchRequestObject,connection);
            pool.returnConnection(connection);
            return createBookSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    private void writeToDataBase(Book book, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_TO_BOOK_TABLE);
        ps.setString(1, book.getAuthor());
        ps.setString(2, book.getTitle());
        ps.setString(3, book.getGenre());
        ps.setString(4, book.getYear());
        ps.executeUpdate();
    }
    private ResultSet findInDataBase(SearchRequest searchRequestObject,
                                Connection connection) throws SQLException {
        String sql = getQuery(searchRequestObject);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, getSearchParameterValue(searchRequestObject));
        return ps.executeQuery();
    }

    private String getSearchParameterName(SearchRequest searchRequestObject){
        String request = searchRequestObject.getRequestParameters();
        return request.split(DAOConstant.DELIMITER)[0];
    }

    private String getSearchParameterValue(SearchRequest searchRequestObject){
        String request = searchRequestObject.getRequestParameters();
        return request.split(DAOConstant.DELIMITER)[1];
    }

    private String getQuery(SearchRequest searchRequestObject){
        StringBuilder builder = new StringBuilder(SELECT_FROM_BOOK_TABLE);
        builder.insert(PARAMETER_INSERT_INDEX_FOR_SELECT,
                getSearchParameterName(searchRequestObject));
        return builder.toString();
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
        book.setAuthor(resultSet.getString(1));
        book.setTitle(resultSet.getString(2));
        book.setGenre(resultSet.getString(3));
        book.setYear(resultSet.getString(4));
        return book;
    }
}

