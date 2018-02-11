package com.sheygam.contactapp.business.login;

/**
 * Created by gregorysheygam on 11/02/2018.
 */

public interface ILoginInteractorCallback {
    void onLoginSuccess();
    void onRegistrationSuccess();
    void onLoginFailure(String error);
    void onRegistrationFailure(String error);
}
