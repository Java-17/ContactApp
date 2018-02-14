package com.sheygam.contactapp.data.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by gregorysheygam on 11/02/2018.
 */

public interface ILoginRepository {

    /**
     *
     * @param email
     * @param password
     * @param callback
     */
    void onLogin(@NonNull String email,@NonNull String password, @Nullable ILoginRepositoryCallback callback);

    /**
     *
     * @param email
     * @param password
     * @param callback
     */
    void onRegistration(@NonNull String email, @NonNull String password, @Nullable ILoginRepositoryCallback callback);
}
