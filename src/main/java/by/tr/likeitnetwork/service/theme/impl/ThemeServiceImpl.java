package by.tr.likeitnetwork.service.theme.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.service.exception.ThemeServiceException;
import by.tr.likeitnetwork.service.theme.ThemeService;

import java.util.List;

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
    public Theme getThemeById(String localeLanguage, int id) {
        return DAOFactory.getInstance().getThemeDAO().getThemeById(localeLanguage, id);
    }
}
