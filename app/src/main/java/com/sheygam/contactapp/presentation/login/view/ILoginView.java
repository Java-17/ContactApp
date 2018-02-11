package com.sheygam.contactapp.presentation.login.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by gregorysheygam on 11/02/2018.
 */
@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface ILoginView extends MvpView {
    void showProgress();
    void hideProgress();
    void onLoginSuccess();
    void onRegistrationSuccess();
    void showError(String error);
    void showInvalidateEmail();
    void showInvalidatePassword();
}
