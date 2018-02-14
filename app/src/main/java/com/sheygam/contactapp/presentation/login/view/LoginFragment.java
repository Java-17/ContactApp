package com.sheygam.contactapp.presentation.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.sheygam.contactapp.R;
import com.sheygam.contactapp.presentation.login.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @InjectPresenter
    LoginPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void showProgress() {
        myProgress.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.INVISIBLE);
        regBtn.setVisibility(View.INVISIBLE);
        inputEmail.setVisibility(View.INVISIBLE);
        inputPassword.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        myProgress.setVisibility(View.INVISIBLE);
        loginBtn.setVisibility(View.VISIBLE);
        regBtn.setVisibility(View.VISIBLE);
        inputEmail.setVisibility(View.VISIBLE);
        inputPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(getActivity(), "Login ok", Toast.LENGTH_SHORT).show();
        nextView();
    }

    private void nextView() {
        //Todo show list fragment
    }

    @Override
    public void onRegistrationSuccess() {
        Toast.makeText(getActivity(), "Registration ok", Toast.LENGTH_SHORT).show();
        nextView();
    }

    @Override
    public void showError(String error) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Error!")
                .setMessage(error)
                .create()
                .show();
    }

    @Override
    public void showInvalidateEmail() {
        inputEmail.setError("Must contains @!");
    }

    @Override
    public void showInvalidatePassword() {
        inputPassword.setError("Password must be bigger then 4 symbols!");
    }

    @OnClick(R.id.loginBtn)
    void onLoginClick(){
        presenter.onLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
    }
    @OnClick(R.id.regBtn)
    void onRegistartionClick(){
        presenter.onRegistration(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
