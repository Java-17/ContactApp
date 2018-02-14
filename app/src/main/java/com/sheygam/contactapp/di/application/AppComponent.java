package com.sheygam.contactapp.di.application;

import com.sheygam.contactapp.di.login.LoginComponent;
import com.sheygam.contactapp.di.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by gregorysheygam on 14/02/2018.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    LoginComponent plus(LoginModule loginModule);
}
