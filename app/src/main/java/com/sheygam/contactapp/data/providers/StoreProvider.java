package com.sheygam.contactapp.data.providers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;

/**
 * Created by gregorysheygam on 14/02/2018.
 */

public class StoreProvider implements IStoreProvider{
    private Context context;

    public StoreProvider(Context context) {
        this.context = context;
    }


    @SuppressLint("ApplySharedPref")
    @Override
    public void saveToken(String token) {
        context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE)
                .edit()
                .putString(TOKEN_KEY,token)
                .commit();
    }

    /**
     *
     * @return null if token does not exist
     */
    @Override
    @Nullable
    public String getToken() {
        return context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE)
                .getString(TOKEN_KEY,null);
    }
}
