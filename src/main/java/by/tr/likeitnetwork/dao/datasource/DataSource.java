package by.tr.likeitnetwork.dao.datasource;

import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataSource {

    private static final String FILE_PATH = "db.properties";
    private static final InputStream UNIQUE_FILE_PATH = DataSource.class.getResourceAsStream(FILE_PATH);

    public static MysqlDataSource getMysqlDataSource(){
        Properties properties = new Properties();
        MysqlDataSource mysqlDataSource;
        try(InputStreamReader reader = new InputStreamReader(UNIQUE_FILE_PATH)) {
            properties.load(reader);
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
            mysqlDataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
            mysqlDataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException ex){
            mysqlDataSource = null;
        }
        return mysqlDataSource;
    }

    public static Connection getConnection() throws DataSourceDAOException{
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/likeitdb", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new DataSourceDAOException(e);
        }
    }


}
