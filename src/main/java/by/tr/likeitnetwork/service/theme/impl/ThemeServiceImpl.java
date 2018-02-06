package by.tr.likeitnetwork.service.theme.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.entity.input.ThemeInput;
import by.tr.likeitnetwork.service.exception.ThemeServiceException;
import by.tr.likeitnetwork.service.theme.ThemeService;
import by.tr.likeitnetwork.service.validation.ValidatorFactory;

import java.util.List;

public class ThemeServiceImpl implements ThemeService {

    @Override
    public List<Theme> getAllThemes(String localeLanguage) throws ThemeServiceException {
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
    public void addTheme(ThemeInput input) throws ThemeServiceException {
        try {
            if (ValidatorFactory.getInstance().getThemeValidator().isValid(input)) {
                DAOFactory.getInstance().getThemeDAO().addTheme(input.getLocalizedNameMap());
            }
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }

    @Override
    public void updateTheme(int id, ThemeInput input) throws ThemeServiceException {
        try {
            if (ValidatorFactory.getInstance().getThemeValidator().isValid(input)) {
                DAOFactory.getInstance().getThemeDAO().updateTheme(id, input.getLocalizedNameMap());
            }
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }

    @Override
    public Theme getThemeById(String localeLanguage, int id) throws ThemeServiceException {
        try {
            return DAOFactory.getInstance().getThemeDAO().getThemeById(localeLanguage, id);
        } catch (ThemeDAOException ex) {
            throw new ThemeServiceException(ex);
        }
    }
}
