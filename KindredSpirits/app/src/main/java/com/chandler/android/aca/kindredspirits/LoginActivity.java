package com.chandler.android.aca.kindredspirits;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseUser mFirebaseUser;
    String mActualUser;

    private ProgressDialog progressDialog;

    // UI references.
    @BindView(R.id.email) AutoCompleteTextView mEmailView;
    @BindView(R.id.password) EditText mPasswordView;
    @BindView(R.id.login_progress) View mProgressView;
    @BindView(R.id.login_form) View mLoginFormView;
    @BindView(R.id.email_sign_in_button) Button mEmailSignInButton;
    @BindView(R.id.register) TextView mRegister;

    @BindView(R.id.imageBackground)
    ImageView mBackground;

    EventBus mBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseAuth.getCurrentUser() != null) {
            //Go to main menu
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Log.v("FirebaseAuth", " user: " + user);
                    mActualUser = user.getUid();
                } else { Log.v("FirebaseAuth", " not logged in");}
            }
        };

        //attaching click listener
        mEmailSignInButton.setOnClickListener(this);
        mRegister.setOnClickListener(this);

        mActualUser = mFirebaseUser.toString();
       // mBus.postSticky(new LoginEvent(mUserName.getText().toString()));
    }

    @Override
    public void onClick(View v) {

        if(v == mEmailSignInButton){
            userLogin();
        }

        if(v == mRegister){
            finish();
            startActivity(new Intent(this, RegistrationActivity.class));
        }

    }

    //method for user login
    private void userLogin(){
        String email = mEmailView.getText().toString().trim();
        String password  = mPasswordView.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Signing in. Please Wait...");
        progressDialog.show();

        //logging in the user
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            Log.v("Login Results", "Login Successful, " + mActualUser);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {Toast.makeText(getApplicationContext(), "Login failed. Please try again", Toast.LENGTH_LONG).show();}
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

/*mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Log.v("FirebaseAuth", " user: " + user);
                    mActualUser = user.getUid();
                } else { Log.v("FirebaseAuth", " not logged in");}
            }
        };*/


