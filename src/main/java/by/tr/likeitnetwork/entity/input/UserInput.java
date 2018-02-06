package by.tr.likeitnetwork.entity.input;

import java.io.Serializable;
import java.util.Objects;

public class UserInput extends Input implements Serializable {
    private String login;
    private String oldPassword;
    private String password;
    private String confirmation;
    private String name;
    private String email;
    private String aboutUser;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInput)) return false;
        UserInput input = (UserInput) o;
        return Objects.equals(login, input.login) &&
                Objects.equals(oldPassword, input.oldPassword) &&
                Objects.equals(password, input.password) &&
                Objects.equals(confirmation, input.confirmation) &&
                Objects.equals(name, input.name) &&
                Objects.equals(email, input.email) &&
                Objects.equals(aboutUser, input.aboutUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, oldPassword, password, confirmation, name, email, aboutUser);
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "login='" + login + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", password='" + password + '\'' +
                ", confirmation='" + confirmation + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", aboutUser='" + aboutUser + '\'' +
                '}';
    }
}

