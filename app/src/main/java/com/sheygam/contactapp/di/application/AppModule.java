package com.sheygam.contactapp.di.application;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.sheygam.contactapp.data.providers.IStoreProvider;
import com.sheygam.contactapp.data.providers.StoreProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by gregorysheygam on 14/02/2018.
 */
@Module
public class AppModule {
    private final Context context;
    private final Handler handler;

    public AppModule(Context context) {
        this.context = context;
        handler = new Handler();
    }

    @Provides
    @Singleton
    Handler provideHandler(){
        return handler;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    IStoreProvider provideStoreProvider(Context context){
        return new StoreProvider(context);
    }

    @Provides
    @Singleton
    Gson provideGson(){
        return  new Gson();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }
}
