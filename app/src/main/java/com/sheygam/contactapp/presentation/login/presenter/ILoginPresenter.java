package com.sheygam.contactapp.presentation.login.presenter;

/**
 * Created by gregorysheygam on 11/02/2018.
 */

public interface ILoginPresenter {
    void onLogin(String email, String password);
    void onRegistration(String email, String password);
}
