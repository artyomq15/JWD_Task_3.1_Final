package by.tr.likeitnetwork.dao.locale.impl;


import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.constant.DBFieldName;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.LocaleDAOException;
import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.dao.locale.LocaleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocaleDAOImpl implements LocaleDAO{
    @Override
    public Integer getLanguageIdIfExists(String language) throws LocaleDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            PreparedStatement getIdByName = connection.prepareStatement(DAOQuery.SQL_SELECT_LANGUAGE_ID_BY_NAME);
            getIdByName.setString(1, language);
            ResultSet resultSet = getIdByName.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(DBFieldName.LANGUAGE_ID);
            }
            return null;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new LocaleDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
