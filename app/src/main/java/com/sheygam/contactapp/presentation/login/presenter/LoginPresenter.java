package com.sheygam.contactapp.presentation.login.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.sheygam.contactapp.App;
import com.sheygam.contactapp.business.login.ILoginInteractor;
import com.sheygam.contactapp.business.login.ILoginInteractorCallback;
import com.sheygam.contactapp.business.login.InteractorEmailValidException;
import com.sheygam.contactapp.business.login.InteractorPasswordValidException;
import com.sheygam.contactapp.di.login.LoginModule;
import com.sheygam.contactapp.presentation.login.view.ILoginView;

import javax.inject.Inject;

/**
 * Created by gregorysheygam on 14/02/2018.
 */
@InjectViewState
public class LoginPresenter extends MvpPresenter<ILoginView> implements ILoginPresenter, ILoginInteractorCallback {

    @Inject
    ILoginInteractor interactor;

    public LoginPresenter() {
        super();
        App.get().plus(new LoginModule()).inject(this);
    }

    @Override
    public void onLogin(String email, String password) {
        getViewState().showProgress();
        try {
            interactor.onLogin(email,password,this);
        } catch (InteractorEmailValidException e) {
            e.printStackTrace();
            getViewState().hideProgress();
            getViewState().showInvalidateEmail();
        } catch (InteractorPasswordValidException e) {
            e.printStackTrace();
            getViewState().hideProgress();
            getViewState().showInvalidatePassword();
        }
    }

    @Override
    public void onRegistration(String email, String password) {
        getViewState().showProgress();
        try {
            interactor.onRegistration(email,password,this);
        } catch (InteractorEmailValidException e) {
            e.printStackTrace();
            getViewState().hideProgress();
            getViewState().showInvalidateEmail();
        } catch (InteractorPasswordValidException e) {
            e.printStackTrace();
            getViewState().hideProgress();
            getViewState().showInvalidatePassword();
        }
    }

    @Override
    public void onLoginSuccess() {
        getViewState().hideProgress();
        getViewState().onLoginSuccess();
    }

    @Override
    public void onRegistrationSuccess() {
        getViewState().hideProgress();
        getViewState().onRegistrationSuccess();
    }

    @Override
    public void onLoginFailure(String error) {
        getViewState().hideProgress();
        getViewState().showError(error);
    }

    @Override
    public void onRegistrationFailure(String error) {
        getViewState().hideProgress();
        getViewState().showError(error);
    }

    @Override
    public void onDestroy() {
        App.get().clearLoginComponent();
        super.onDestroy();
    }
}
