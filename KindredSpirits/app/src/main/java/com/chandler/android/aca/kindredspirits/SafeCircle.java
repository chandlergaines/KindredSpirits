
package com.chandler.android.aca.kindredspirits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SafeCircle extends AppCompatActivity {

    @BindView(R.id.contact1)
    Button mContact1;
    @BindView(R.id.contact2)
    Button mContact2;
    @BindView(R.id.contact3)
    Button mContact3;
    @BindView(R.id.contact4)
    Button mContact4;
    @BindView(R.id.contact5)
    Button mContact5;
    @BindView(R.id.callEmergency)
    Button mEmergency;
    @BindView(R.id.btnResources)
    Button mBtnResources;
    @BindView(R.id.btnSendAll)
    Button mSendAll;
    @BindView(R.id.spinner)
    Spinner mSpinner;

    @BindView(R.id.imageBackground)
    ImageView mBackground;

    String mContactId;
    String mContactName;
    String mContactNumber;

    SharedPreferences mPrefs;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_circle_activity);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

        mEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent action = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:911"));
                startActivity(action);
                Log.v("Safe Circle", "Called 911");
            }
        });

        mBtnResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SafeCircle.this, HotlineActivity.class));
            }
        });

        mContact5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SafeCircleFragment contactFragment = new SafeCircleFragment();

                setContentView(R.layout.safe_circle_fragment);
                getSupportFragmentManager().beginTransaction().add(R.id.safeFragLayout, contactFragment).commit();
            }
        });

        mContact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mContact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mContact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mContact4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void messageContact() {
    Intent action = new Intent(Intent.ACTION_SEND,
            Uri.parse("tel:" + mContactNumber));

    startActivity(action);

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        //What gets shared added in here

      /*  sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mNote.getTitle());
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Note: " + mNote.getTitle() + "\n \n" + "Description: "+ mNote.getDescription());
        sharingIntent.putExtra(Intent.EXTRA_STREAM, mNote.getImage());*/

        //Start the share
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

}

/*mPrefs = getSharedPreferences("Note to self", MODE_PRIVATE);
        mSound = mPrefs.getBoolean("sound", true);
        mAnimOption = mPrefs.getInt("anim option", NoteSettingsActivity.FAST);
// mBackground = mPrefs.getInt("bkg option", SettingsActivity.COLORS);*/



