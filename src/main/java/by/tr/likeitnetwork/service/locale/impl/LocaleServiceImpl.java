package by.tr.likeitnetwork.service.locale.impl;


import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.LocaleDAOException;
import by.tr.likeitnetwork.service.exception.LocaleServiceException;
import by.tr.likeitnetwork.service.locale.LocaleService;

public class LocaleServiceImpl implements LocaleService {
    @Override
    public boolean checkLanguageExists(String language) throws LocaleServiceException {
        try{
            return !(DAOFactory.getInstance().getLocaleDAO().getLanguageIdIfExists(language) == null);
        } catch (LocaleDAOException ex) {
            throw new LocaleServiceException(ex);
        }
    }
}
