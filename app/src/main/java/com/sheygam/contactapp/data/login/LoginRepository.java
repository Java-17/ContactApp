package com.sheygam.contactapp.data.login;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.sheygam.contactapp.App;
import com.sheygam.contactapp.data.dto.Auth;
import com.sheygam.contactapp.data.dto.AuthToken;
import com.sheygam.contactapp.data.providers.IStoreProvider;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gregorysheygam on 14/02/2018.
 */

public class LoginRepository implements ILoginRepository{
    private Handler handler;
    private Gson gson;
    private OkHttpClient client;
    private IStoreProvider storeProvider;

    public LoginRepository(Handler handler, Gson gson, OkHttpClient client, IStoreProvider storeProvider) {
        this.handler = handler;
        this.gson = gson;
        this.client = client;
        this.storeProvider = storeProvider;
    }


    @Override
    public void onLogin(@NonNull String email, @NonNull String password, @Nullable final ILoginRepositoryCallback callback) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Auth auth = new Auth(email,password);
        String requestJson = gson.toJson(auth);
        RequestBody requestBody = RequestBody.create(JSON,requestJson);
        final Request request = new Request.Builder()
                .url(App.BASE_URL + "/login")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(() -> {
                    if (callback != null) {
                        callback.onLoginFailure("Connection error!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    AuthToken authToken = gson.fromJson(response.body().string(),AuthToken.class);
                    storeProvider.saveToken(authToken.getToken());
                    handler.post(() -> {
                        if (callback != null) {
                            callback.onLoginSuccess();
                        }
                    });
                }else if(response.code() == 401){
                    handler.post(() -> {
                        if (callback != null) {
                            callback.onLoginFailure("Wrong email or password!");
                        }
                    });
                }else{
                    Log.d("MY_TAG", "LoginRepository onLogin() onResponse: " + response.body().string());
                    handler.post(() -> {
                        if (callback != null) {
                            callback.onLoginFailure("Server error! Call to Support!");
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRegistration(@NonNull String email, @NonNull String password, @Nullable final ILoginRepositoryCallback callback) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Auth auth = new Auth(email,password);
        String requestJson = gson.toJson(auth);
        RequestBody requestBody = RequestBody.create(JSON,requestJson);
        final Request request = new Request.Builder()
                .url(App.BASE_URL + "/registration")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(() -> {
                    if (callback != null) {
                        callback.onRegisterationFailure("Connection error!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    AuthToken authToken = gson.fromJson(response.body().string(),AuthToken.class);
                    storeProvider.saveToken(authToken.getToken());
                    handler.post(() -> {
                        if (callback != null) {
                            callback.onRegistreationSuccess();
                        }
                    });
                }else if(response.code() == 409){
                    handler.post(() -> {
                        if (callback != null) {
                            callback.onRegisterationFailure("User already exist!");
                        }
                    });
                }else{
                    Log.d("MY_TAG", "LoginRepository onRegistration() onResponse: " + response.body().string());
                    handler.post(() -> {
                        if (callback != null) {
                            callback.onRegisterationFailure("Server error! Call to Support!");
                        }
                    });
                }
            }
        });
    }
}
