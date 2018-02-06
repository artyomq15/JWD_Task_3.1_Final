package by.tr.likeitnetwork.service.validation;

import by.tr.likeitnetwork.entity.input.ThemeInput;
import by.tr.likeitnetwork.entity.input.UserInput;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ValidatorTest extends TestCase {

    public void testIsValidSignUp() {
        Validator<UserInput> validator = ValidatorFactory.getInstance().getSignUpValidator();
        UserInput input = new UserInput();
        input.setLogin("Qwerty");
        input.setPassword("Rasgraq16");
        input.setConfirmation("Rasgraq16");
        input.setEmail("artyom.stan0905@gmail.com");
        input.setName("Artem");
        Assert.assertTrue(validator.isValid(input));
    }

    public void testIsNotValidSignUp() {
        Validator<UserInput> validator = ValidatorFactory.getInstance().getSignUpValidator();
        UserInput input = new UserInput();
        input.setLogin("Qwe233y");
        input.setPassword("rasgraq16");
        input.setConfirmation("rasgraq16");
        input.setEmail("artyom.stan0905@gmail.com");
        input.setName("Artem");
        Assert.assertFalse(validator.isValid(input));
    }

    public void testIsValidSignIn(){
        Validator<UserInput> validator = ValidatorFactory.getInstance().getSignInValidator();
        UserInput input= new UserInput();
        input.setLogin("teasdwq");
        input.setPassword("Rasgraq16");
        Assert.assertTrue(validator.isValid(input));
    }

    public void testIsNotValidSignIn(){
        Validator<UserInput> validator = ValidatorFactory.getInstance().getSignInValidator();
        UserInput input= new UserInput();
        input.setLogin("1teasdwq");
        input.setPassword("Rasgraq16");
        Assert.assertFalse(validator.isValid(input));
    }

    public void testIsValidTheme(){
        Validator<ThemeInput> validator = ValidatorFactory.getInstance().getThemeValidator();
        ThemeInput input = new ThemeInput();
        input.putLocalizedName("Russian", "Resree");
        input.putLocalizedName("Belarussian", "Resree");
        input.putLocalizedName("English", "Resree");
        Assert.assertTrue(validator.isValid(input));
    }
    public void testIsNotValidTheme(){
        Validator<ThemeInput> validator = ValidatorFactory.getInstance().getThemeValidator();
        ThemeInput input = new ThemeInput();
        input.putLocalizedName("Russian", "Resree");
        input.putLocalizedName("Belarussian", "123123");
        input.putLocalizedName("English", "Resree");
        Assert.assertFalse(validator.isValid(input));
    }
}