package by.tr.likeitnetwork.service.validation;

import java.util.regex.Pattern;

public final class ThemeValidator {
    private static final Pattern NAME_VALIDATION = Pattern.compile("[A-Za-zА-Яа-яІіўЁё'-]+");

    public static boolean isValidName(String name) {
        return NAME_VALIDATION.matcher(name).matches();
    }

    private ThemeValidator(){}
}
