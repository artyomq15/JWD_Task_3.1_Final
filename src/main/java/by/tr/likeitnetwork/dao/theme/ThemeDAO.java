package by.tr.likeitnetwork.dao.theme;

import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.entity.Theme;

import java.util.List;
import java.util.Locale;

public interface ThemeDAO {
    List<Theme> getAllThemes(String localeLanguage) throws ThemeDAOException;
    Theme getThemeById(String localeLanguage, int id) throws ThemeDAOException;
}
