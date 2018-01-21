package by.tr.likeitnetwork.service.locale;

import by.tr.likeitnetwork.service.exception.LocaleServiceException;

public interface LocaleService {
    boolean checkLanguageExists(String language) throws LocaleServiceException;
}
