package com.sheygam.contactapp.di.login;

import android.os.Handler;

import com.google.gson.Gson;
import com.sheygam.contactapp.business.login.ILoginInteractor;
import com.sheygam.contactapp.business.login.LoginInteractor;
import com.sheygam.contactapp.data.login.ILoginRepository;
import com.sheygam.contactapp.data.login.LoginRepository;
import com.sheygam.contactapp.data.login.RetrofitLoginRepository;
import com.sheygam.contactapp.data.providers.Api;
import com.sheygam.contactapp.data.providers.IStoreProvider;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by gregorysheygam on 14/02/2018.
 */
@Module
public class LoginModule {
//    @Provides
//    @LoginScope
//    ILoginRepository provideLoginRepository(Handler handler,
//                                            OkHttpClient client,
//                                            Gson gson,
//                                            IStoreProvider storeProvider){
//        return new LoginRepository(handler,gson,client,storeProvider);
//
//
//    }
    @Provides
    @LoginScope
    ILoginRepository provideLoginRepository(Api api, IStoreProvider storeProvider){
        return new RetrofitLoginRepository(api,storeProvider);
    }

    @Provides
    @LoginScope
    ILoginInteractor provideLoginInteractor(ILoginRepository loginRepository){
        return new LoginInteractor(loginRepository);
    }
}
