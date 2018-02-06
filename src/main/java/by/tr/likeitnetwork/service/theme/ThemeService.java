package by.tr.likeitnetwork.service.theme;


import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.entity.input.ThemeInput;
import by.tr.likeitnetwork.service.exception.ThemeServiceException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface ThemeService {
    List<Theme> getAllThemes(String localeLanguage) throws ThemeServiceException;
    List<List<Object>> getThemesInfo () throws ThemeServiceException;
    void showTheme(int id) throws ThemeServiceException;
    void hideTheme(int id) throws ThemeServiceException;
    void addTheme (ThemeInput input) throws ThemeServiceException;
    void updateTheme (int id, ThemeInput input) throws ThemeServiceException;
    Theme getThemeById(String localeLanguage, int id) throws ThemeServiceException;
}
