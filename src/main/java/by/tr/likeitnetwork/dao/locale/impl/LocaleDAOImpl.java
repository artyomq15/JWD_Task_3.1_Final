package by.tr.likeitnetwork.dao.locale.impl;


import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.LocaleDAOException;
import by.tr.likeitnetwork.dao.locale.LocaleDAO;

import java.sql.*;

public class LocaleDAOImpl implements LocaleDAO{
    @Override
    public Integer getLanguageIdIfExists(String language) throws LocaleDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getIdByName = connection.prepareCall(DAOQuery.SQL_CALL_GET_LANGUAGE_ID_BY_NAME);
            getIdByName.setString(1, language);
            getIdByName.registerOutParameter(2, Types.INTEGER);

            ResultSet resultSet = getIdByName.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return null;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new LocaleDAOException("Get language error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
