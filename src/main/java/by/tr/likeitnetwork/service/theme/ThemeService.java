package by.tr.likeitnetwork.service.theme;


import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.service.exception.ThemeServiceException;

import java.util.List;
import java.util.Locale;

public interface ThemeService {
    List<Theme> getAllThemes(String localeLanguage) throws ThemeServiceException;
    Theme getThemeById(String localeLanguage, int id) throws ThemeServiceException;
}
