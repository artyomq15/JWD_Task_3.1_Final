package by.tr.likeitnetwork.service.validation.rule;

import by.tr.likeitnetwork.entity.input.Input;
import by.tr.likeitnetwork.entity.input.ThemeInput;
import by.tr.likeitnetwork.entity.input.TopicInput;
import by.tr.likeitnetwork.entity.input.UserInput;

public enum Rule {
    LOGIN{
        @Override
        public <T extends Input> boolean isValid(T input){
            String login = ((UserInput) input).getLogin();
            return login != null
                    && RuleRegexp.LOGIN.matcher(login).matches();
        }
    },

    PASSWORD{
        @Override
        public <T extends Input> boolean isValid(T input){
            String password = ((UserInput) input).getPassword();
            return password != null
                    && RuleRegexp.PASSWORD.matcher(password).matches();
        }
    },

    OLD_PASSWORD{
        @Override
        public <T extends Input> boolean isValid(T input){
            String oldPassword = ((UserInput) input).getOldPassword();
            return oldPassword != null
                    && RuleRegexp.PASSWORD.matcher(oldPassword).matches();
        }
    },

    EMAIL{
        @Override
        public <T extends Input> boolean isValid(T input){
            String email = ((UserInput) input).getEmail();
            return email !=null
                    && RuleRegexp.EMAIL.matcher(email).matches();
        }
    },

    NAME{
        @Override
        public <T extends Input> boolean isValid(T input){
            String name = ((UserInput) input).getName();
            return name != null
                    && RuleRegexp.NAME.matcher(name).matches();
        }
    },

    CONFIRMATION{
        @Override
        public <T extends Input> boolean isValid(T input){
            UserInput userInput = (UserInput)input;
            return userInput.getPassword() != null
                    && userInput.getPassword().equals(userInput.getConfirmation());
        }
    },

    ABOUT_USER{
        @Override
        public <T extends Input> boolean isValid(T input){
            return true;
        }
    },

    THEME {
        @Override
        public <T extends Input> boolean isValid(T input){
            ThemeInput themeInput = (ThemeInput) input;
            return themeInput.getLocalizedNameMap() != null
                    && themeInput.getLocalizedNameMap()
                        .entrySet()
                        .stream()
                        .allMatch(entry -> RuleRegexp.NAME.matcher(entry.getValue()).matches());
        }
    },

    TOPIC_HEADER {
        @Override
        public <T extends Input> boolean isValid(T input){
            String header = ((TopicInput)input).getHeader();
            return header != null
                    && RuleRegexp.TOPIC_HEADER.matcher(header).matches();
        }
    },

    TOPIC_CONTEXT {
        @Override
        public <T extends Input> boolean isValid(T input){
            return true;
        }
    },

    TOPIC_THEME_ID {
        @Override
        public <T extends Input> boolean isValid(T input){
            return ((TopicInput)input).getThemeId() > 0;
        }
    };



    public <T extends Input> boolean isValid(T input){
        return true;
    }
}
