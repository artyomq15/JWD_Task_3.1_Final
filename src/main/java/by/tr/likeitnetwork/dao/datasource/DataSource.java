package by.tr.likeitnetwork.dao.datasource;

import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public final class DataSource {

    private static final String FILE_PATH = "db";

    private static final String URL_ATTR_NAME = "MYSQL_DB_URL";
    private static final String DRIVER_ATTR_NAME = "MYSQL_DB_DRIVER_CLASS";
    private static final String USERNAME_ATTR_NAME = "MYSQL_DB_USERNAME";
    private static final String PASSWORD_ATTR_NAME = "MYSQL_DB_PASSWORD";


    public static Connection getConnection() throws DataSourceDAOException{
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_PATH);
            Class.forName(resourceBundle.getString(DRIVER_ATTR_NAME));
            return DriverManager.getConnection(resourceBundle.getString(URL_ATTR_NAME), resourceBundle.getString(USERNAME_ATTR_NAME), resourceBundle.getString(PASSWORD_ATTR_NAME));
        } catch (ClassNotFoundException | SQLException e) {
            throw new DataSourceDAOException(e);
        }
    }

    private DataSource(){}


}
