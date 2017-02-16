package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.bean.parameter.EntityParameterName;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.pool.ConnectionPool;
import com.epam.catalog.dao.pool.exeption.ConnectionPoolException;
import com.epam.catalog.dao.constant.DAOConstant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class SqlBookDAO implements EntityDAO<Book> {


    private final static String INSERT_TO_BOOK_TABLE =
            "INSERT INTO catalog.book (author,title,genre,year) VALUES (?,?,?,?);";
    private final static String SELECT_FROM_BOOK_TABLE_BY_AUTHOR =
            "SELECT * FROM catalog.book WHERE author = ?;";
    private final static String SELECT_FROM_BOOK_TABLE_BY_TITLE =
            "SELECT * FROM catalog.book WHERE title = ?;";
    private final static String SELECT_FROM_BOOK_TABLE_BY_GENRE =
            "SELECT * FROM catalog.book WHERE genre = ?;";
    private final static String SELECT_FROM_BOOK_TABLE_BY_YEAR =
            "SELECT * FROM catalog.book WHERE year = ?;";

    private static Map <String,String> bookQueryRepository = new HashMap<>();

    static {
        bookQueryRepository.put(EntityParameterName.AUTHOR.toString(),
                SELECT_FROM_BOOK_TABLE_BY_AUTHOR);
        bookQueryRepository.put(EntityParameterName.TITLE.toString(),
                SELECT_FROM_BOOK_TABLE_BY_TITLE);
        bookQueryRepository.put(EntityParameterName.GENRE.toString(),
                SELECT_FROM_BOOK_TABLE_BY_GENRE);
        bookQueryRepository.put(EntityParameterName.YEAR.toString(),
                SELECT_FROM_BOOK_TABLE_BY_YEAR);
    }


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
            String[] searchParameter = getSearchParameter(searchRequestObject);
            resultSet = findInDataBase(searchParameter,connection);
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

    private ResultSet findInDataBase(String[] searchParameter,
                                     Connection connection) throws SQLException {
        String sqlQuery = bookQueryRepository.get(searchParameter[0]);
        PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ps.setString(1, searchParameter[1]);
        return ps.executeQuery();
    }


    private String[] getSearchParameter (SearchRequest searchRequestObject){
        String request = searchRequestObject.getRequestParameters();
        return request.split(DAOConstant.DELIMITER);
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

