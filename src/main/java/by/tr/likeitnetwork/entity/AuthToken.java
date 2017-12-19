package by.tr.likeitnetwork.entity;

import java.io.Serializable;

public final class AuthToken implements Serializable{
    private String accessToken;
    private String refreshToken;

    public AuthToken(){}

    public AuthToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        AuthToken authToken = (AuthToken) object;

        if (accessToken != null ? !accessToken.equals(authToken.accessToken) : authToken.accessToken != null)
            return false;
        return refreshToken != null ? refreshToken.equals(authToken.refreshToken) : authToken.refreshToken == null;

    }

    @Override
    public int hashCode() {
        int result = accessToken != null ? accessToken.hashCode() : 0;
        result = 31 * result + (refreshToken != null ? refreshToken.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
