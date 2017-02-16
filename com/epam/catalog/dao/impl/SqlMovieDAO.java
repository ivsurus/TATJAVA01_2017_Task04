package com.epam.catalog.dao.impl;


import com.epam.catalog.bean.Movie;
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


public class SqlMovieDAO implements EntityDAO<Movie> {

    private final static String INSERT_TO_MOVIE_TABLE =
            "INSERT INTO catalog.movie (producer,title,genre,year) VALUES (?,?,?,?);";
    private final static String SELECT_FROM_MOVIE_TABLE_BY_PRODUCER =
            "SELECT * FROM catalog.movie WHERE producer = ?;";
    private final static String SELECT_FROM_MOVIE_TABLE_BY_TITLE =
            "SELECT * FROM catalog.movie WHERE title = ?;";
    private final static String SELECT_FROM_MOVIE_TABLE_BY_GENRE =
            "SELECT * FROM catalog.movie WHERE genre = ?;";
    private final static String SELECT_FROM_MOVIE_TABLE_BY_YEAR =
            "SELECT * FROM catalog.movie WHERE year = ?;";

    private static Map<String,String> movieQueryRepository = new HashMap<>();

    static {
        movieQueryRepository.put(EntityParameterName.PRODUCER.toString(),
                SELECT_FROM_MOVIE_TABLE_BY_PRODUCER);
        movieQueryRepository.put(EntityParameterName.TITLE.toString(),
                SELECT_FROM_MOVIE_TABLE_BY_TITLE);
        movieQueryRepository.put(EntityParameterName.GENRE.toString(),
                SELECT_FROM_MOVIE_TABLE_BY_GENRE);
        movieQueryRepository.put(EntityParameterName.YEAR.toString(),
                SELECT_FROM_MOVIE_TABLE_BY_YEAR);
    }

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
            String[] searchParameter = getSearchParameter(searchRequestObject);
            resultSet = findInDataBase(searchParameter,connection);
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

    private ResultSet findInDataBase(String[] searchParameter,
                                     Connection connection) throws SQLException {
        String sqlQuery = movieQueryRepository.get(searchParameter[0]);
        PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ps.setString(1, searchParameter[1]);
        return ps.executeQuery();
    }


    private String[] getSearchParameter (SearchRequest searchRequestObject){
        String request = searchRequestObject.getRequestParameters();
        return request.split(DAOConstant.DELIMITER);
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



