package com.sheygam.contactapp.data.login;

/**
 * Created by gregorysheygam on 11/02/2018.
 */

public interface ILoginRepositoryCallback {
    void onLoginSuccess();
    void onRegistreationSuccess();
    void onLoginFailure(String error);
    void onRegisterationFailure(String error);
}
