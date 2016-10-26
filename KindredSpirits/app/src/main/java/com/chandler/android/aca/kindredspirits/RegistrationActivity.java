package com.chandler.android.aca.kindredspirits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.editTextFirstName) EditText mFirstName;
    @BindView(R.id.editTextLastName) EditText mLastName;
    @BindView(R.id.editTextUsername) EditText mUsername;
    @BindView(R.id.editTextPassword) EditText mPassword;
    @BindView(R.id.editTextMobile) EditText mMoblieNumber;
    @BindView(R.id.editTextEmail) EditText mEmail;
    @BindView(R.id.buttonSignup) Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);

        mFirstName.setText("ButterKnife");
    }
}
