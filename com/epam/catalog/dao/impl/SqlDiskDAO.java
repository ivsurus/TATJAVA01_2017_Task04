package com.epam.catalog.dao.impl;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.bean.parameter.EntityParameterName;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.pool.ConnectionPool;
import com.epam.catalog.dao.pool.exeption.ConnectionPoolException;
import com.epam.catalog.dao.util.DAOConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class SqlDiskDAO implements EntityDAO<Disk>{

    private final static String INSERT_TO_DISK_TABLE = "INSERT INTO catalog.disk (price,title,genre,year) VALUES (?,?,?,?);";
    private final static String SELECT_FROM_DISK_TABLE_BY_PRICE = "SELECT * FROM catalog.disk WHERE price = ?;";
    private final static String SELECT_FROM_DISK_TABLE_BY_TITLE = "SELECT * FROM catalog.disk WHERE title = ?;";
    private final static String SELECT_FROM_DISK_TABLE_BY_GENRE = "SELECT * FROM catalog.disk WHERE genre = ?;";
    private final static String SELECT_FROM_DISK_TABLE_BY_YEAR = "SELECT * FROM catalog.disk WHERE year = ?;";

    private static Map<String,String> diskQueryRepository = new HashMap<>();

    static {
        diskQueryRepository.put(EntityParameterName.PRICE.toString(), SELECT_FROM_DISK_TABLE_BY_PRICE);
        diskQueryRepository.put(EntityParameterName.TITLE.toString(), SELECT_FROM_DISK_TABLE_BY_TITLE);
        diskQueryRepository.put(EntityParameterName.GENRE.toString(), SELECT_FROM_DISK_TABLE_BY_GENRE);
        diskQueryRepository.put(EntityParameterName.YEAR.toString(), SELECT_FROM_DISK_TABLE_BY_YEAR);
    }

    @Override
    public void addEntity(Disk disk) throws DAOException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            writeToDataBase(disk,connection);
            pool.returnConnection(connection);
        } catch  (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        }
    }

    @Override
    public Set<Disk> findEntity(SearchRequest searchRequestObject) throws DAOException {

        ResultSet resultSet;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            String[] searchParameter = getSearchParameter(searchRequestObject);
            resultSet = findInDataBase(searchParameter,connection);
            pool.returnConnection(connection);
            return createDiskSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    private void writeToDataBase(Disk disk, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_TO_DISK_TABLE);
        ps.setString(1, disk.getPrice());
        ps.setString(2, disk.getTitle());
        ps.setString(3, disk.getGenre());
        ps.setString(4, disk.getYear());
        ps.executeUpdate();
    }

    private ResultSet findInDataBase(String[] searchParameter,
                                     Connection connection) throws SQLException {
        String sqlQuery = diskQueryRepository.get(searchParameter[0]);
        PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ps.setString(1, searchParameter[1]);
        return ps.executeQuery();
    }


    private String[] getSearchParameter (SearchRequest searchRequestObject){
        String request = searchRequestObject.getRequestParameters();
         return request.split(DAOConstant.DELIMITER);
    }


    private Set<Disk> createDiskSet(ResultSet resultSet) throws SQLException {
        Set<Disk> diskSet = new HashSet<>();
        while (resultSet.next()) {
            diskSet.add(createDisk(resultSet));
        }
        return diskSet;
    }

    private Disk createDisk(ResultSet resultSet) throws SQLException {
        Disk disk = new Disk();
        disk.setPrice(resultSet.getString(1));
        disk.setTitle(resultSet.getString(2));
        disk.setGenre(resultSet.getString(3));
        disk.setYear(resultSet.getString(4));
        return disk;
    }
}
