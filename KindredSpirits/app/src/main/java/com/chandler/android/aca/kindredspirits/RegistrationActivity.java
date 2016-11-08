package com.chandler.android.aca.kindredspirits;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.editTextFirstName)
    EditText mFirstName;
    @BindView(R.id.editTextLastName)
    EditText mLastName;
    @BindView(R.id.editTextUsername)
    EditText mUsername;
    @BindView(R.id.editTextPassword)
    EditText mPassword;
    @BindView(R.id.editTextMobile)
    EditText mMoblieNumber;
    @BindView(R.id.editTextEmail)
    EditText mEmail;
    @BindView(R.id.buttonSignup)
    Button mRegister;

    @BindView(R.id.imageBackground)
    ImageView mBackground;

    private FirebaseAuth mFirebaseAuth;

    private ProgressDialog mProgressDialog;

    EventBus mBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);

        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mProgressDialog = new ProgressDialog(this);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {

        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String firstName = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String userName = mUsername.getText().toString().trim();
        String moblieNumber = mMoblieNumber.getText().toString().trim();


        if(TextUtils.isEmpty(firstName)){
            mFirstName.setError("Please enter your preferred name");
            return;
        }
        if(TextUtils.isEmpty(lastName)){
            mLastName.setError("Please enter your last name");
            return;
        }
        if(TextUtils.isEmpty(userName)){
            mUsername.setError("Please enter a username");
            return;
        }
        if(TextUtils.isEmpty(moblieNumber)){
            mMoblieNumber.setError("Please enter your moblie number");
            return;
        }
        if(TextUtils.isEmpty(email)){
            mEmail.setError("Please enter a valid email address");
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter a password",
                    Toast.LENGTH_LONG).show();
            return;
        }

        mProgressDialog.setMessage("Registering, please wait...");
        mProgressDialog.show();

        //create a new user
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking success
                        if(task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Successfully registered",
                                    Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Registration error",
                                    Toast.LENGTH_LONG).show();
                        }
                        mProgressDialog.dismiss();
                    }
                });
    }

/*    @Override
    protected void onStart() {
        super.onStart();
        mBus.register(this); //registering the event bus
    }

    @Override
    public void onStop() {
        mBus.unregister(this); //unregister the event bus
        super.onStop(); //nothing will be completed after this line is executed
    }

    @Override
    protected void onDestroy() {
        mBus.unregister(this);
        super.onDestroy();
    }*/

}

