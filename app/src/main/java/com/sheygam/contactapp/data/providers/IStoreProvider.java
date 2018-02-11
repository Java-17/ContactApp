package com.sheygam.contactapp.data.providers;

/**
 * Created by gregorysheygam on 11/02/2018.
 */

public interface IStoreProvider {
    String SP_NAME = "auth";
    String TOKEN_KEY = "TOKEN";

    void saveToken(String token);
    String getToken();
}
