package com.sheygam.contactapp.di.login;

import com.sheygam.contactapp.presentation.login.presenter.LoginPresenter;

import dagger.Subcomponent;

/**
 * Created by gregorysheygam on 14/02/2018.
 */

@Subcomponent(modules = {LoginModule.class})
@LoginScope
public interface LoginComponent {
    void inject(LoginPresenter presenter);
}
