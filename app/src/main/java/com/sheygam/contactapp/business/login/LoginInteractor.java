package com.sheygam.contactapp.business.login;

import com.sheygam.contactapp.data.login.ILoginRepository;
import com.sheygam.contactapp.data.login.ILoginRepositoryCallback;

/**
 * Created by gregorysheygam on 14/02/2018.
 */

public class LoginInteractor implements ILoginInteractor, ILoginRepositoryCallback {
    private ILoginRepository repository;
    private ILoginInteractorCallback callback;

    public LoginInteractor(ILoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onLogin(String email, String password, ILoginInteractorCallback callback) throws InteractorEmailValidException, InteractorPasswordValidException {
        this.callback = callback;
        if(email == null || email.trim().isEmpty() || !isEmailValid(email.trim())){
            throw new InteractorEmailValidException();
        }

        if(password == null || password.trim().isEmpty() || !isPasswordValid(password.trim())){
            throw new InteractorPasswordValidException();
        }

        repository.onLogin(email.trim(),password.trim(),this);

    }

    @Override
    public void onRegistration(String email, String password, ILoginInteractorCallback callback) throws InteractorEmailValidException, InteractorPasswordValidException {
        this.callback = callback;
        if(email == null || email.trim().isEmpty() || !isEmailValid(email.trim())){
            throw new InteractorEmailValidException();
        }

        if(password == null || password.trim().isEmpty() || !isPasswordValid(password.trim())){
            throw new InteractorPasswordValidException();
        }

        repository.onRegistration(email.trim(),password.trim(),this);
    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length() >= 4;
    }

    @Override
    public void onLoginSuccess() {
        callback.onLoginSuccess();
    }

    @Override
    public void onRegistreationSuccess() {
        callback.onRegistrationSuccess();
    }

    @Override
    public void onLoginFailure(String error) {
        callback.onLoginFailure(error);
    }

    @Override
    public void onRegisterationFailure(String error) {
        callback.onRegistrationFailure(error);
    }
}
