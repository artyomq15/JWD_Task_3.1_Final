package by.tr.likeitnetwork.entity;

import java.io.Serializable;

public final class AuthToken implements Serializable{// если тебе не нужно переопределять equals и прочее, то ты должен явно изменить контракт этих методов
    // помнишь как это делается?
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
}
