package by.tr.likeitnetwork.dao.datasource;

import by.tr.likeitnetwork.dao.exception.ConnectionPoolException;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DataSource {
    private static ConnectionPool connectionPool = new ConnectionPool();

    public static void init() throws DataSourceDAOException {
        try {
            connectionPool.initPool();
        } catch (ConnectionPoolException ex) {
            throw new DataSourceDAOException(ex);
        }
    }

    public static Connection getConnection() throws DataSourceDAOException {
        try {
            return connectionPool.getConnectionFromPool();
        } catch (ConnectionPoolException ex) {
            throw new DataSourceDAOException(ex);
        }
    }

    public static void closeConnection(Connection connection) {
        connectionPool.returnConnectionToPool(connection);
    }

    public static void destroy() throws DataSourceDAOException{
        try {
            connectionPool.closePool();
        } catch (ConnectionPoolException ex) {
            throw new DataSourceDAOException(ex);
        }
    }

    private DataSource() {
    }
}
