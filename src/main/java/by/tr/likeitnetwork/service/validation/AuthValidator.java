package by.tr.likeitnetwork.service.validation;

import by.tr.likeitnetwork.entity.RegistrationInfo;

import java.util.regex.Pattern;

public final class AuthValidator {
    private static final Pattern LOGIN_VALIDATION = Pattern.compile("^[A-Za-z][A-Za-z0-9_]{4,49}$");
    private static final Pattern PASSWORD_VALIDATION = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$");
    private static final Pattern EMAIL_VALIDATION = Pattern.compile("^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)?@[A-Za-z0-9]+\\.[A-Za-z]{2,4}$");
    private static final Pattern NAME_VALIDATION = Pattern.compile("^[A-Za-zА-Яа-яІіўЁё'-]{2,50}$");

    public static boolean isValidRegistrationInfo(RegistrationInfo info) {
        return isRightConfirmation(info.getPassword(), info.getConfirmation())
                && isValidLogin(info.getLogin())
                && isValidPassword(info.getPassword())
                && isValidEmail(info.getEmail())
                && isValidName(info.getName());
    }

    public static boolean isValidSignInInfo(String login, String password){

        return isValidLogin(login) && isValidPassword(password);
    }

    public static boolean isValidPasswordsForChanging(String oldPassword, String newPassword, String newPasswordConfirmation){
        return isRightConfirmation(newPassword, newPasswordConfirmation)
                && isValidPassword(oldPassword)
                && isValidPassword(newPassword);
    }

    public static boolean isValidProfileInfo (String name, String email){
        return isValidName(name) && isValidEmail(email);
    }



    private static boolean isRightConfirmation(String password, String confirmation) {
        return password.equals(confirmation);
    }

    private static boolean isValidLogin(String login) {
        return LOGIN_VALIDATION.matcher(login).matches();
    }

    private static boolean isValidPassword(String password) {
        return PASSWORD_VALIDATION.matcher(password).matches();
    }

    private static boolean isValidEmail(String email) {
        return EMAIL_VALIDATION.matcher(email).matches();
    }

    private static boolean isValidName(String name) {
        return NAME_VALIDATION.matcher(name).matches();
    }

    private AuthValidator(){}

}
