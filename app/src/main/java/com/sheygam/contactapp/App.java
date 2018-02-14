package com.sheygam.contactapp;

import android.app.Application;

import com.sheygam.contactapp.di.application.AppComponent;
import com.sheygam.contactapp.di.application.AppModule;
import com.sheygam.contactapp.di.application.DaggerAppComponent;
import com.sheygam.contactapp.di.login.LoginComponent;
import com.sheygam.contactapp.di.login.LoginModule;

/**
 * Created by gregorysheygam on 14/02/2018.
 */

public class App extends Application {
    public static final String BASE_URL = "https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";
    private AppComponent component;
    private LoginComponent loginComponent;
    private static App app;

    public App() {
        app = this;
    }

    public static App get(){
        return app;
    }
    @Override
    public void onCreate() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        super.onCreate();
    }

    public LoginComponent plus(LoginModule module){
        if(loginComponent == null){
            loginComponent = component.plus(module);
        }
        return loginComponent;
    }

    public void clearLoginComponent(){
        loginComponent = null;
    }
}
