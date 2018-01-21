package by.tr.likeitnetwork.dao.locale;


import by.tr.likeitnetwork.dao.exception.LocaleDAOException;

public interface LocaleDAO {
    Integer getLanguageIdIfExists(String language) throws LocaleDAOException;
}
