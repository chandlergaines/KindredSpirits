package com.chandler.android.aca.kindredspirits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   // @BindView(R.id.buttonNoteActivity) Button mNoteActivity;
   // @BindView(R.id.buttonKindnessActivity) Button mKindnessActivity;

    Button mNoteActivity;
    Button mKindnessActivity;
    Button mHotlineActivity;
    Button mLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteActivity = (Button) findViewById(R.id.buttonNoteActivity);
        mKindnessActivity = (Button) findViewById(R.id.buttonKindnessActivity);
        mHotlineActivity = (Button) findViewById(R.id.buttonHotlineActivity);
        mLoginActivity = (Button) findViewById(R.id.buttonLoginActivity);

       // ButterKnife.bind(this);

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
    }
}
