package com.chandler.android.aca.kindredspirits;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonNoteActivity) Button mNoteActivity;
    @BindView(R.id.buttonKindnessActivity) Button mKindnessActivity;
    @BindView(R.id.buttonHotlineActivity) Button mHotlineActivity;
    @BindView(R.id.buttonLoginActivity) Button mLoginActivity;
    @BindView(R.id.buttonRegistrationActivity) Button mRegistrationActivity;
    @BindView(R.id.buttonBreathingActivity) Button mBreathingActivity;
    @BindView(R.id.buttonSafe) Button mSafeActivity;
    @BindView(R.id.button3) Button mFirebase;
    @BindView(R.id.button5) Button mLogout;
    @BindView(R.id.userTextView) TextView mUser;
    @BindView(R.id.imageBackground)
    ImageView mBackground;

    FirebaseAuth mFirebaseAuth;
    String firebaseUser;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.v("FirebaseAuth", "onAuthStateChanged:signed_in:" + user.getUid());
                    firebaseUser = user.getUid();
                } else {
                    // User is signed out
                    Log.v("FirebaseAuth", "onAuthStateChanged:signed_out");
                    firebaseUser = "No one is logged in";
                }

            }
        };

        mUser.setText(firebaseUser);

        mRegistrationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        mNoteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoteActivity.class));
            }
        });

        mKindnessActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KindWordsActivity.class));
            }
        });

        mHotlineActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HotlineActivity.class));
            }
        });

        mLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        mBreathingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BreathingActivity.class));
            }
        });

        mSafeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SafeCircle.class));
            }
        });

        mFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirebaseTest.class));
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(firebaseUser == null) {

                    Toast.makeText(getApplicationContext(), "No users logged in", Toast.LENGTH_SHORT).show();
                }
                else {

                    firebaseUser = mFirebaseAuth.getCurrentUser().toString();
                    Log.e("User: ", "" + firebaseUser + " signing out...");
                    mFirebaseAuth.signOut();
                    Toast.makeText(getApplicationContext(), "Logging out...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
