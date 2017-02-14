package com.epam.catalog.dao.impl;


import com.epam.catalog.bean.Movie;
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


public class SqlMovieDAO implements EntityDAO<Movie> {

    private final static String INSERT_TO_MOVIE_TABLE =
            "INSERT INTO catalog.movie (producer,title,genre,year) VALUES (?,?,?,?);";
    private final static String SELECT_FROM_MOVIE_TABLE =
            "SELECT * FROM catalog.movie WHERE  = ?;";
    private final static int PARAMETER_INSERT_INDEX_FOR_SELECT =34;

    @Override
    public void addEntity(Movie movie) throws DAOException {

        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            writeToDataBase(movie,connection);
            pool.returnConnection(connection);
        } catch  (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        }
    }

    @Override
    public Set<Movie> findEntity(SearchRequest searchRequestObject) throws DAOException {

        ResultSet resultSet;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            resultSet = findInDataBase(searchRequestObject,connection);
            pool.returnConnection(connection);
            return createMovieSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    private void writeToDataBase(Movie movie, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_TO_MOVIE_TABLE);
        ps.setString(1, movie.getProducer());
        ps.setString(2, movie.getTitle());
        ps.setString(3, movie.getGenre());
        ps.setString(4, movie.getYear());
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
        StringBuilder builder = new StringBuilder(SELECT_FROM_MOVIE_TABLE);
        builder.insert(PARAMETER_INSERT_INDEX_FOR_SELECT,
                getSearchParameterName(searchRequestObject));
        return builder.toString();
    }

    private Set<Movie> createMovieSet (ResultSet resultSet) throws SQLException {
        Set<Movie> movieSet = new HashSet<>();
        while (resultSet.next()) {
            movieSet.add(createMovie(resultSet));
        }
        return movieSet;
    }

    private Movie createMovie(ResultSet resultSet) throws SQLException {
        Movie book = new Movie();
        book.setProducer(resultSet.getString(1));
        book.setTitle(resultSet.getString(2));
        book.setGenre(resultSet.getString(3));
        book.setYear(resultSet.getString(4));
        return book;
    }
}



