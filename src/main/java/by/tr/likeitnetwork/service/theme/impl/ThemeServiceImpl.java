package by.tr.likeitnetwork.service.theme.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.service.exception.ThemeServiceException;
import by.tr.likeitnetwork.service.theme.ThemeService;
import by.tr.likeitnetwork.service.validation.ThemeValidator;

import java.util.List;
import java.util.Map;

public class ThemeServiceImpl implements ThemeService{

    @Override
    public List<Theme> getAllThemes(String localeLanguage) throws ThemeServiceException{
        try {
            return DAOFactory.getInstance().getThemeDAO().getAllThemes(localeLanguage);
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }

    @Override
    public List<List<Object>> getThemesInfo() throws ThemeServiceException {
        try {
            return DAOFactory.getInstance().getThemeDAO().getThemesInfo();
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }

    @Override
    public void showTheme(int id) throws ThemeServiceException {
        try {
            DAOFactory.getInstance().getThemeDAO().showTheme(id);
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }

    @Override
    public void hideTheme(int id) throws ThemeServiceException {
        try {
            DAOFactory.getInstance().getThemeDAO().hideTheme(id);
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }

    @Override
    public void addTheme(Map<String, String> localizedNames) throws ThemeServiceException {
        for (String langName : localizedNames.keySet()) {
            if (!ThemeValidator.isValidName(localizedNames.get(langName))){
                return;
            }
        }
        try {
            DAOFactory.getInstance().getThemeDAO().addTheme(localizedNames);
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }

    @Override
    public void updateTheme(int id, Map<String, String> localizedNames) throws ThemeServiceException {
        localizedNames.entrySet().removeIf(entry -> !ThemeValidator.isValidName(entry.getValue()));
        try {
            if (!localizedNames.isEmpty()){
                DAOFactory.getInstance().getThemeDAO().updateTheme(id, localizedNames);
            }
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }

    @Override
    public Theme getThemeById(String localeLanguage, int id) throws ThemeServiceException{
        try {
            return DAOFactory.getInstance().getThemeDAO().getThemeById(localeLanguage, id);
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }
}
