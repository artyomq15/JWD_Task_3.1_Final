package by.tr.likeitnetwork.service.validation;

import by.tr.likeitnetwork.entity.input.ThemeInput;
import by.tr.likeitnetwork.entity.input.TopicInput;
import by.tr.likeitnetwork.entity.input.UserInput;
import by.tr.likeitnetwork.service.validation.impl.ValidatorImpl;

import static by.tr.likeitnetwork.service.validation.rule.Rule.*;

public class ValidatorFactory {
    private static ValidatorFactory instance = new ValidatorFactory();

    private Validator<UserInput> signUpValidator = new ValidatorImpl();
    private Validator<UserInput> signInValidator = new ValidatorImpl();
    private Validator<UserInput> changePasswordValidator = new ValidatorImpl();
    private Validator<UserInput> changeProfileInfoValidator = new ValidatorImpl();
    private Validator<ThemeInput> themeValidator = new ValidatorImpl();
    private Validator<TopicInput> topicValidator = new ValidatorImpl();

    {
        signUpValidator.setRules(LOGIN, PASSWORD, CONFIRMATION, NAME, EMAIL);
        signInValidator.setRules(LOGIN, PASSWORD);
        changePasswordValidator.setRules(OLD_PASSWORD, PASSWORD, CONFIRMATION);
        changeProfileInfoValidator.setRules(NAME, EMAIL, ABOUT_USER);
        themeValidator.setRules(THEME);
        topicValidator.setRules(TOPIC_HEADER, TOPIC_CONTEXT, TOPIC_THEME_ID);
    }

    private ValidatorFactory(){}

    public static ValidatorFactory getInstance() {
        return instance;
    }

    public Validator<UserInput> getSignUpValidator(){
        return signUpValidator;
    }

    public Validator<UserInput> getSignInValidator(){
        return signInValidator;
    }

    public Validator<UserInput> getChangePasswordValidator(){
        return changePasswordValidator;
    }

    public Validator<UserInput> getChangeProfileInfoValidator(){
        return changeProfileInfoValidator;
    }

    public Validator<ThemeInput> getThemeValidator(){
        return themeValidator;
    }

    public Validator<TopicInput> getTopicValidator(){
        return topicValidator;
    }
}
