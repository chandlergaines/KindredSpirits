package com.chandler.android.aca.kindredspirits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SafeCircle extends AppCompatActivity {

    @BindView(R.id.contact1) Button mContact1;
    @BindView(R.id.contact2) Button mContact2;
    @BindView(R.id.contact3) Button mContact3;
    @BindView(R.id.contact4) Button mContact4;
    @BindView(R.id.contact5) Button mContact5;
    @BindView(R.id.callEmergency) Button mEmergency;
    @BindView(R.id.btnResources) Button mBtnResources;
    @BindView(R.id.btnSendAll) Button mSendAll;
    @BindView(R.id.spinner) Spinner mSpinner;

    @BindView(R.id.imageBackground)
    ImageView mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_circle);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

    }
}
