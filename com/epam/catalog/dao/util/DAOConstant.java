package com.epam.catalog.dao.util;


public class DAOConstant {
    public final static String DELIMITER = "%_%";
    public final static String LOCATION_OF_JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
    public final static String JDBC_URL = "jdbc:mysql://127.0.0.1/catalog";
    public final static String DB_PASSWORD = "root";
    public final static String DB_USERNAME = "root";
    public final static String SELECT_FROM_BOOK_TABLE = "SELECT DISTINCT * FROM book WHERE ";
    public final static String PREPARED_INSERT_INTO_BOOK_TABLE = "INSERT INTO 'book' VALUES (?,?,?,?)";
    public final static String INSERT_INTO_BOOK_TABLE = "INSERT INTO 'book' VALUES ";
    public final static String EQUALS_SIGN = " = ";
    public final static String SINGLE_QUOTE = "'";
    public final static String ACUTE = "`";
    public final static String SEMI_COLON = ";";
}

