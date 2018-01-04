package by.tr.likeitnetwork.dao.theme.impl;

import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.constant.DBFieldName;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.dao.theme.ThemeDAO;
import by.tr.likeitnetwork.entity.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemeDAOImpl implements ThemeDAO {
    @Override
    public List<Theme> getAllThemes(String localeLanguage) throws ThemeDAOException {
        Integer languageId = getIdLanguageIfExists(localeLanguage);
        if (languageId == null) {
            return getThemesInDefaultLanguage();
        }
        return getThemesInLocaleLanguage(languageId);
    }

    @Override
    public Theme getThemeById(String localeLanguage, int id) throws ThemeDAOException {
        Integer languageId = getIdLanguageIfExists(localeLanguage);
        if (languageId == null) {
            return getThemeInDefaultLanguage(id);
        }
        return getThemeInLocaleLanguage(languageId, id);
    }

    private Integer getIdLanguageIfExists(String language) throws ThemeDAOException {
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
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    private List<Theme> getThemesInDefaultLanguage() throws ThemeDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            PreparedStatement getThemes = connection.prepareStatement(DAOQuery.SQL_SELECT_ALL_THEMES_IN_DEFAULT_LANGUAGE);
            ResultSet resultSet = getThemes.executeQuery();
            List<Theme> themeList = new ArrayList<>();
            while (resultSet.next()) {
                Theme theme = new Theme();
                theme.setId(resultSet.getInt(DBFieldName.THEME_ID));
                theme.setName(resultSet.getString(DBFieldName.THEME_DEFAULT_NAME));
                themeList.add(theme);
            }
            return themeList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    private List<Theme> getThemesInLocaleLanguage(Integer languageId) throws ThemeDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            PreparedStatement getThemes = connection.prepareStatement(DAOQuery.SQL_SELECT_ALL_THEMES_IN_LOCALE_LANGUAGE);
            getThemes.setInt(1, languageId);
            ResultSet resultSet = getThemes.executeQuery();
            List<Theme> themeList = new ArrayList<>();
            while (resultSet.next()) {
                Theme theme = new Theme();
                theme.setId(resultSet.getInt(DBFieldName.THEME_ID));
                theme.setName(resultSet.getString(DBFieldName.THEME_LOCALE_NAME));
                themeList.add(theme);
            }

            return themeList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    private Theme getThemeInDefaultLanguage(int id) throws ThemeDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            PreparedStatement getThemes = connection.prepareStatement(DAOQuery.SQL_SELECT_THEME_IN_DEFAULT_LANGUAGE);
            getThemes.setInt(1, id);
            ResultSet resultSet = getThemes.executeQuery();
            Theme theme = null;
            if (resultSet.next()) {
                theme = new Theme();
                theme.setId(resultSet.getInt(DBFieldName.THEME_ID));
                theme.setName(resultSet.getString(DBFieldName.THEME_DEFAULT_NAME));
            }

            return theme;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    private Theme getThemeInLocaleLanguage(Integer languageId, int id) throws ThemeDAOException {
        Connection connection = null;
        try  {
            connection = DataSource.getConnection();
            PreparedStatement getThemes = connection.prepareStatement(DAOQuery.SQL_SELECT_THEME_IN_LOCALE_LANGUAGE);
            getThemes.setInt(1, languageId);
            getThemes.setInt(2, id);
            ResultSet resultSet = getThemes.executeQuery();
            Theme theme = null;
            if (resultSet.next()) {
                theme = new Theme();
                theme.setId(resultSet.getInt(DBFieldName.THEME_ID));
                theme.setName(resultSet.getString(DBFieldName.THEME_LOCALE_NAME));
            }

            return theme;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new ThemeDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
