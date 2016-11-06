package com.chandler.android.aca.kindredspirits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

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

    FirebaseAuth mFirebaseAuth;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        user = FirebaseAuth.getInstance().getCurrentUser().toString();
        mUser.setText(user);

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

                if(user == null) {

                    Toast.makeText(getApplicationContext(), "No users logged in", Toast.LENGTH_SHORT).show();
                }
                else {

                    user = mFirebaseAuth.getCurrentUser().toString();
                    Log.e("User: ", "" + user + " signing out...");
                    mFirebaseAuth.signOut();
                    Toast.makeText(getApplicationContext(), "Logging out...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
