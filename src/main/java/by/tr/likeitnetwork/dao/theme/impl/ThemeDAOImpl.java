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

public class ThemeDAOImpl implements ThemeDAO{
    @Override
    public List<Theme> getAllThemes(String localeLanguage) throws ThemeDAOException{
        Integer languageId = getIdLanguageIfExists(localeLanguage);
        if (languageId == null){
            return getThemesInDefaultLanguage();
        }
        return getThemesInLocaleLanguage(languageId);
    }

    @Override
    public Theme getThemeById(String localeLanguage, int id) {
        return null;
    }

    private Integer getIdLanguageIfExists(String language) throws ThemeDAOException{
        try(Connection connection = DataSource.getConnection()){
            PreparedStatement getIdByName = connection.prepareStatement(DAOQuery.SQL_SELECT_LANGUAGE_ID_BY_NAME);
            getIdByName.setString(1, language);
            ResultSet resultSet = getIdByName.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(DBFieldName.LANGUAGE_ID);
            }
            return null;
        }catch (SQLException | DataSourceDAOException ex){
            throw new ThemeDAOException(ex);
        }
    }

    private List<Theme> getThemesInDefaultLanguage()throws ThemeDAOException{
        try(Connection connection = DataSource.getConnection()){
            PreparedStatement getThemes = connection.prepareStatement(DAOQuery.SQL_SELECT_ALL_THEMES_IN_DEFAULT_LANGUAGE);
            ResultSet resultSet = getThemes.executeQuery();
            List<Theme> themeList = new ArrayList<>();
            while (resultSet.next()){
                Theme theme = new Theme();
                theme.setId(resultSet.getInt(DBFieldName.THEME_ID));
                theme.setName(resultSet.getString(DBFieldName.THEME_DEFAULT_NAME));
                themeList.add(theme);
            }
            return themeList;
        }catch (SQLException | DataSourceDAOException ex){
            throw new ThemeDAOException(ex);
        }
    }

    private List<Theme> getThemesInLocaleLanguage(Integer languageId)throws ThemeDAOException{
        try(Connection connection = DataSource.getConnection()){
            PreparedStatement getThemes = connection.prepareStatement(DAOQuery.SQL_SELECT_ALL_THEMES_IN_LOCALE_LANGUAGE);
            getThemes.setInt(1,languageId);
            ResultSet resultSet = getThemes.executeQuery();
            List<Theme> themeList = new ArrayList<>();
            while (resultSet.next()){
                Theme theme = new Theme();
                theme.setId(resultSet.getInt(DBFieldName.THEME_ID));
                theme.setName(resultSet.getString(DBFieldName.THEME_LOCALE_NAME));
                themeList.add(theme);
            }
            return themeList;
        }catch (SQLException | DataSourceDAOException ex){
            throw new ThemeDAOException(ex);
        }
    }
}
