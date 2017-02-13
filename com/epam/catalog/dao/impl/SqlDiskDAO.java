package com.epam.catalog.dao.impl;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.pool.ConnectionPool;
import com.epam.catalog.dao.util.DAOConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class SqlDiskDAO implements EntityDAO<Disk>{

    private final static String INSERT_TO_DISK_TABLE =
            "INSERT INTO catalog.disk (price,title,genre,year) VALUES (?,?,?,?);";
    private final static String SELECT_FROM_DISK_TABLE =
            "SELECT * FROM catalog.disk WHERE  = ?;";
    private final static int PARAMETER_INSERT_INDEX_FOR_SELECT =33;

    @Override
    public void addEntity(Disk disk) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            writeToDataBase(disk,connection);
            pool.returnConnection(connection);
        } catch  (SQLException e) {
            throw new DAOException();
        }
    }

    @Override
    public Set<Disk> findEntity(SearchRequest searchRequestObject) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet resultSet;
        try {
            resultSet = findInDataBase(searchRequestObject,connection);
            pool.returnConnection(connection);
            return createDiskSet(resultSet);
        } catch (SQLException e) {
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
        StringBuilder builder = new StringBuilder(SELECT_FROM_DISK_TABLE);
        builder.insert(PARAMETER_INSERT_INDEX_FOR_SELECT,
                getSearchParameterName(searchRequestObject));
        return builder.toString();
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
