package by.tr.likeitnetwork.dao.theme;

import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.entity.Theme;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface ThemeDAO {
    List<Theme> getAllThemes(String localeLanguage) throws ThemeDAOException;
    List<List<Object>> getThemesInfo () throws ThemeDAOException;
    void showTheme(int id) throws ThemeDAOException;
    void hideTheme (int id) throws ThemeDAOException;
    void addTheme (Map<String, String> localizedNames) throws ThemeDAOException;
    void updateTheme (int id, Map<String, String> localizedNames) throws ThemeDAOException;
    Theme getThemeById(String localeLanguage, int id) throws ThemeDAOException;
}
