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
import java.util.Map;

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
    public List<List<Object>> getThemesInfo() throws ThemeDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getThemes = connection.prepareCall(DAOQuery.SQL_CALL_GET_THEMES_INFO);
            getThemes.registerOutParameter(1, Types.INTEGER);
            getThemes.registerOutParameter(2, Types.INTEGER);
            getThemes.registerOutParameter(3, Types.VARCHAR);
            getThemes.registerOutParameter(4, Types.VARCHAR);
            getThemes.registerOutParameter(5, Types.BIT);


            ResultSet resultSet = getThemes.executeQuery();
            List<List<Object>> themeList = new ArrayList<>();
            while (resultSet.next()) {
                List<Object> list = new ArrayList<>();
                list.add(resultSet.getInt(1));
                list.add(resultSet.getInt(2));
                list.add(resultSet.getString(3));
                list.add(resultSet.getString(4));
                list.add(resultSet.getBoolean(5));

                themeList.add(list);
            }
            return themeList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void showTheme(int id) throws ThemeDAOException {
        showOrHideTheme(id, DAOQuery.SQL_CALL_SHOW_THEME);
    }

    @Override
    public void hideTheme(int id) throws ThemeDAOException {
        showOrHideTheme(id, DAOQuery.SQL_CALL_HIDE_THEME);
    }

    @Override
    public void addTheme(Map<String, String> localizedNames) throws ThemeDAOException {
        Connection connection = null;
        Integer themeId;
        try {
            connection = DataSource.getConnection();

            connection.setAutoCommit(false);
            CallableStatement createNewTheme = connection.prepareCall(DAOQuery.SQL_CALL_GET_ADDED_THEME_ID);
            createNewTheme.registerOutParameter(1, Types.INTEGER);

            ResultSet resultSet = createNewTheme.executeQuery();
            resultSet.next();
            themeId = resultSet.getInt(1);

            for (String langName : localizedNames.keySet()) {
                CallableStatement addLocalizedName = connection.prepareCall(DAOQuery.SQL_CALL_INSERT_LOCALIZED_THEME_NAME);
                addLocalizedName.setInt(1, themeId);
                addLocalizedName.setString(2, langName);
                addLocalizedName.setString(3, localizedNames.get(langName));

                addLocalizedName.executeUpdate();
            }
            connection.commit();

        } catch (SQLException | DataSourceDAOException ex) {
            try {
                if (connection != null){
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ThemeDAOException("Rollback Error", e);
            }
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void updateTheme(int id, Map<String, String> localizedNames) throws ThemeDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            for (String langName : localizedNames.keySet()) {
                CallableStatement addLocalizedName = connection.prepareCall(DAOQuery.SQL_CALL_UPDATE_LOCALIZED_THEME_NAME);
                addLocalizedName.setInt(1, id);
                addLocalizedName.setString(2, langName);
                addLocalizedName.setString(3, localizedNames.get(langName));

                addLocalizedName.executeUpdate();
            }

        } catch (SQLException | DataSourceDAOException ex) {
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    private void showOrHideTheme(int id, String query) throws ThemeDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement statement = connection.prepareCall(query);
            statement.setInt(1, id);

            statement.executeUpdate();
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

