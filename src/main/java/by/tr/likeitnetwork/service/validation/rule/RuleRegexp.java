package by.tr.likeitnetwork.service.validation.rule;

import java.util.regex.Pattern;

public class RuleRegexp {
    public static final Pattern LOGIN = Pattern.compile("^[A-Za-z][A-Za-z0-9_]{4,49}$");
    public static final Pattern PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$");
    public static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)?@[A-Za-z0-9]+\\.[A-Za-z]{2,4}$");
    public static final Pattern NAME = Pattern.compile("^[A-Za-zА-Яа-яІіўЁё'-]{2,50}$");
    public static final Pattern TOPIC_HEADER = Pattern.compile("^.{1,250}$");
}
