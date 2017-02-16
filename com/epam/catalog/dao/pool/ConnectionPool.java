package com.epam.catalog.dao.pool;



import com.epam.catalog.dao.pool.exeption.ConnectionPoolException;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.constant.DAOConstant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {


    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean giveConnection = new AtomicBoolean(true);
    private static AtomicBoolean isNull = new AtomicBoolean(true);
    private static ConnectionPool instance;
    private ArrayBlockingQueue<Connection> pool;


    private ConnectionPool() {
    }


    public void init() throws ConnectionPoolException {
        pool = new ArrayBlockingQueue<>(DAOConstant.DEFAULT_POOL_SIZE);
        try {
            Class.forName(DAOConstant.LOCATION_OF_JDBC_DRIVER);
            for (int i = 0; i <= DAOConstant.DEFAULT_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(DAOConstant.JDBC_URL,
                        DAOConstant.DB_USERNAME, DAOConstant.DB_PASSWORD);
                pool.offer(connection);
            }
        } catch (SQLException e){
            throw new ConnectionPoolException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e){
            throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }


    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (isNull.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isNull.set(false);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }


    public Connection getConnection() throws DAOException {
        Connection connection = null;
        if (giveConnection.get()) {
            try {
                connection = pool.poll(DAOConstant.WAITING_TIMEOUT_SEC, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new DAOException("Exception occurred during getting connection");
            }
        }
        return connection;
    }


    public void returnConnection(Connection connection) {
        if (connection != null) {
            pool.offer(connection);
        }
    }


    public void cleanUp() throws ConnectionPoolException {
        giveConnection = new AtomicBoolean(false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException
                    ("InterruptedException occurred during connection pool cleaning",e);
        }
        Iterator<Connection> iterator = pool.iterator();
        while (iterator.hasNext()) {
            Connection connection = iterator.next();
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException
                ("SQLException occurred during connection pool cleaning",e);
            }
            iterator.remove();
        }
    }
}