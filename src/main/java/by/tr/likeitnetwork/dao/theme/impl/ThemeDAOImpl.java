package by.tr.likeitnetwork.dao.theme.impl;

import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.dao.theme.ThemeDAO;
import by.tr.likeitnetwork.entity.Theme;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThemeDAOImpl implements ThemeDAO {
    @Override
    public List<Theme> getAllThemes(String localeLanguage) throws ThemeDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getThemes = connection.prepareCall(DAOQuery.SQL_CALL_GET_THEMES_BY_LANGUAGE);
            getThemes.setString(1, localeLanguage);
            getThemes.registerOutParameter(2, Types.INTEGER);
            getThemes.registerOutParameter(3, Types.VARCHAR);

            ResultSet resultSet = getThemes.executeQuery();
            List<Theme> themeList = new ArrayList<>();
            while (resultSet.next()) {
                Theme theme = new Theme();
                theme.setId(resultSet.getInt(1));
                theme.setName(resultSet.getString(2));
                themeList.add(theme);
            }


            return themeList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public Theme getThemeById(String localeLanguage, int id) throws ThemeDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getTheme = connection.prepareCall(DAOQuery.SQL_CALL_GET_THEME_BY_ID_AND_LANGUAGE);
            getTheme.setString(1, localeLanguage);
            getTheme.setInt(2, id);

            getTheme.registerOutParameter(3, Types.VARCHAR);
            ResultSet resultSet = getTheme.executeQuery();
            Theme theme = null;
            if (resultSet.next()) {
                theme = new Theme();
                theme.setId(id);
                theme.setName(resultSet.getString(1));
            }
            return theme;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}

