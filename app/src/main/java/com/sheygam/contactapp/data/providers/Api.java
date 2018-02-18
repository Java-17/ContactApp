package com.sheygam.contactapp.data.providers;

import com.sheygam.contactapp.data.dto.Auth;
import com.sheygam.contactapp.data.dto.AuthToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by gregorysheygam on 18/02/2018.
 */

public interface Api {

    @POST("_ah/api/contactsApi/v1/login")
    Call<AuthToken> login(@Body Auth auth);
    @POST("_ah/api/contactsApi/v1/registration")
    Call<AuthToken> registration(@Body Auth auth);
    @GET("_ah/api/contactsApi/v1/contactsarray")
    Call<List<Object>> getList(@Header("Authorization") String token);
}
