package com.sheygam.contactapp.business.login;

/**
 * Created by gregorysheygam on 11/02/2018.
 */

public interface ILoginInteractor {
    void onLogin(String email,String password,ILoginInteractorCallback callback)
            throws InteractorEmailValidException,InteractorPasswordValidException;
    void onRegistration(String email, String password, ILoginInteractorCallback callback)
        throws InteractorEmailValidException, InteractorPasswordValidException;
}
