package com.chandler.android.aca.kindredspirits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonNoteActivity) Button mNoteActivity;
    @BindView(R.id.buttonKindnessActivity) Button mKindnessActivity;
    @BindView(R.id.buttonHotlineActivity) Button mHotlineActivity;
    @BindView(R.id.buttonLoginActivity) Button mLoginActivity;
    @BindView(R.id.buttonRegistrationActivity) Button mRegistrationActivity;
    @BindView(R.id.buttonBreathingActivity) Button mBreathingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ButterKnife.bind(this);

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
    }
}
