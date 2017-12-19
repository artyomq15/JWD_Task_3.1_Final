package by.tr.likeitnetwork.entity;

import java.io.Serializable;

public final class RegistrationInfo implements Serializable {
    private String login;
    private String password;
    private String confirmation;
    private String name;
    private String email;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        RegistrationInfo that = (RegistrationInfo) object;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (confirmation != null ? !confirmation.equals(that.confirmation) : that.confirmation != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (confirmation != null ? confirmation.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegistrationInfo{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confirmation='" + confirmation + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
