package com.sheygam.contactapp.presentation.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.sheygam.contactapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by gregorysheygam on 11/02/2018.
 */

public class LoginFragment extends MvpAppCompatFragment implements ILoginView {

    @BindView(R.id.inputEmail)
    EditText inputEmail;
    @BindView(R.id.inputPassword)
    EditText inputPassword;
    @BindView(R.id.regBtn)
    Button regBtn;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.myProgress)
    ProgressBar myProgress;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onRegistrationSuccess() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showInvalidateEmail() {

    }

    @Override
    public void showInvalidatePassword() {

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
