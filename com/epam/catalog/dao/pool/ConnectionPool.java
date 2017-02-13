package com.epam.catalog.dao.pool;



import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.util.DAOConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

   // final static Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static final int WAITING_TIMEOUT_SEC = 10;
  //  private static ResourceBundle configBundle = ResourceBundle.getBundle("database");
    private final int DEFAULT_POOL_SIZE = 10;//Integer.parseInt(configBundle.getString("defaultPoolSize"));
    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean giveConnection = new AtomicBoolean(true);
    private static AtomicBoolean isNull = new AtomicBoolean(true);
    private static ConnectionPool instance;
    private ArrayBlockingQueue<Connection> pool;

    /**
     * Simple constructor that performs the only initializing method.
     */
    private ConnectionPool() {
        init();
    }

    /**
     * Initializing method that sets up all starting database conditions:
     * login, password, pool size, encoding, etc. Causes {@code RuntimeException}
     * in the case of {@code SQLException} that occurs if any SQL error is faced.
     */
    private void init() {
        pool = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);

        try {
            Class.forName(DAOConstant.LOCATION_OF_JDBC_DRIVER).newInstance();
            /*DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Properties properties = new Properties();
            properties.setProperty("user", configBundle.getString("login"));
            properties.setProperty("password", configBundle.getString("password"));
            properties.setProperty("useUnicode", configBundle.getString("unicode"));
            properties.setProperty("characterEncoding", configBundle.getString("encoding"));*/
            for (int i = 0; i <= DEFAULT_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(DAOConstant.JDBC_URL,
                        DAOConstant.DB_USERNAME, DAOConstant.DB_PASSWORD);
                pool.offer(connection);
            }
        } catch (SQLException e) {
     //       LOG.fatal("Exception occurred during connecting to database", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Represents double-checked singleton pattern that gets the
     * only instance of {@code ConnectionPool}.
     * @return  single instance of {@code ConnectionPool}.
     */
    public static ConnectionPool getInstance() {
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

    /**
     * Gets an instance of {@code Connection} from the pool using non-
     * blocking {@code poll()} method.
     * @return  an instance of {@code Connection}.
     * @throws DAOException (self-generated) if {@code InterruptedException}
     *          occurs due to {@code poll()} method used with time-waiting parameter.
     */
    public Connection getConnection() throws DAOException {
        Connection connection = null;
        if (giveConnection.get()) {
            try {
                connection = pool.poll(WAITING_TIMEOUT_SEC, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new DAOException("Exception occurred during getting connection");
            }
        }
        return connection;
    }

    /**
     * Gives back a non-null connection to the connection pool through the
     * use of {@code offer()} method.
     * @param   connection is an instance of {@code Connection}.
     */
    public void returnConnection(Connection connection) {
        if (connection != null) {
            pool.offer(connection);
        }
    }

    /**
     * Shuts down the connection pool by closing all the connections in it.
     * Uses {@code sleep} method for compulsory delay before cleaning the pool.
     */
    public void cleanUp() throws DAOException {
        giveConnection = new AtomicBoolean(false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new DAOException("InterruptedException occurred during connection pool cleaning");
        }
        Iterator<Connection> iterator = pool.iterator();
        while (iterator.hasNext()) {
            Connection connection = iterator.next();
            try {
                connection.close();
            } catch (SQLException e) {
                //LOG.error("Exception occurred during connection pool cleaning");
            }
            iterator.remove();
        }
    }
}