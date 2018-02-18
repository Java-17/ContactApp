package com.sheygam.contactapp.data.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sheygam.contactapp.data.dto.Auth;
import com.sheygam.contactapp.data.dto.AuthToken;
import com.sheygam.contactapp.data.providers.Api;
import com.sheygam.contactapp.data.providers.IStoreProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gregorysheygam on 18/02/2018.
 */

public class RetrofitLoginRepository implements ILoginRepository{
    private Api api;
    private IStoreProvider storeProvider;

    public RetrofitLoginRepository(Api api, IStoreProvider storeProvider) {
        this.api = api;
        this.storeProvider = storeProvider;
    }

    @Override
    public void onLogin(@NonNull String email, @NonNull String password, @Nullable ILoginRepositoryCallback callback) {
        Auth auth = new Auth(email,password);
        api.login(auth).enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if (response.isSuccessful()){
                    storeProvider.saveToken(response.body().getToken());
                    if (callback != null) {
                        callback.onLoginSuccess();
                    }
                }else if(response.code() == 401){
                    if (callback != null) {
                        callback.onLoginFailure("Wrong email or password!");
                    }
                }else{
                    if (callback != null) {
                        callback.onLoginFailure("Server error!");
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                if (callback != null) {
                    callback.onLoginFailure("Connection error!");
                }
            }
        });
    }

    @Override
    public void onRegistration(@NonNull String email, @NonNull String password, @Nullable ILoginRepositoryCallback callback) {

        Auth auth = new Auth(email,password);
        api.registration(auth).enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if (response.isSuccessful()){
                    storeProvider.saveToken(response.body().getToken());
                    if (callback != null) {
                        callback.onRegistreationSuccess();
                    }
                }else if(response.code() == 409){
                    if (callback != null) {
                        callback.onRegisterationFailure("User already exist!");
                    }
                }else{
                    if (callback != null) {
                        callback.onRegisterationFailure("Server error!");
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                if (callback != null) {
                    callback.onRegisterationFailure("Connection error!");
                }
            }
        });
    }
}
