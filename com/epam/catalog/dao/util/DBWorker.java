package com.epam.catalog.dao.util;

import com.epam.catalog.dao.exeption.DAOException;

import java.sql.*;


public class DBWorker {

    // Количество рядов таблицы, затронутых последним запросом.
    private Integer affected_rows = 0;

    // Значение автоинкрементируемого первичного ключа, полученное после
    // добавления новой записи.
    private Integer last_insert_id = 0;

    private static DBWorker instance = new DBWorker();

    private DBWorker() {}

    public static DBWorker getInstance() {return instance;}



    // Выполнение запросов на выборку данных.
    public ResultSet getDBData(String query) throws ClassNotFoundException, IllegalAccessException,
                                                    InstantiationException, SQLException {
        Statement statement;
        Connection connect;
        Class.forName(DAOConstant.LOCATION_OF_JDBC_DRIVER).newInstance(); //???
        connect = DriverManager.getConnection(DAOConstant.JDBC_URL,
                    DAOConstant.DB_USERNAME, DAOConstant.DB_PASSWORD);
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    // Выполнение запросов на модификацию данных.
    public Integer changeDBData(String query) throws ClassNotFoundException, IllegalAccessException,
                                                     InstantiationException, SQLException {
        Statement statement;
        Connection connect;
        Class.forName(DAOConstant.LOCATION_OF_JDBC_DRIVER).newInstance();
        connect = DriverManager.getConnection(DAOConstant.JDBC_URL,
                DAOConstant.DB_USERNAME, DAOConstant.DB_PASSWORD);
        statement = connect.createStatement();
        this.affected_rows = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        // Получаем last_insert_id() для операции вставки.
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            this.last_insert_id = rs.getInt(1);
        }
        return this.affected_rows;
    }


        public Integer getAffectedRowsCount() {return this.affected_rows;}

        public Integer getLastInsertId() {return this.last_insert_id;}

}
