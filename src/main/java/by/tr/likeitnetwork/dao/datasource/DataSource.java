package by.tr.likeitnetwork.dao.datasource;

import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DataSource {

    public static void init() throws DataSourceDAOException{
        try {
            Class.forName(SourceMetaData.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new DataSourceDAOException(e);
        }
    }
    public static Connection getConnection() throws DataSourceDAOException{
        try{
            return DriverManager.getConnection(SourceMetaData.URL,SourceMetaData.USERNAME, SourceMetaData.PASSWORD);
        } catch (SQLException e) {
            throw new DataSourceDAOException(e);
        }
    }

    private DataSource(){}


}
