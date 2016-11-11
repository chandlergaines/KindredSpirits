
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SafeCircleActivity extends AppCompatActivity {

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

    DatabaseReference mDataRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mDataRootRef.child("contacts");

    Cursor cursor;
    List mContactList;
    SafeContact mContact;
    SafeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_circle_activity);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

        mConditionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mContactList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {

                    //todo figure out how to pull only as many as are needed

                    mContact = new SafeContact();
                    mContact = dataSnapshot.child("" + (i + 1)).getValue(SafeContact.class);
                    mContactList.add(mContact);

                }

                mAdapter = new SafeAdapter(SafeCircleActivity.this);
                mAdapter.setContactList(mContactList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

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
                startActivity(new Intent(SafeCircleActivity.this, HotlineActivity.class));
            }
        });

        mContact5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafeCircleFragment dialog = new SafeCircleFragment();
                dialog.show(getFragmentManager(), "");
            }
        });

        mContact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getContactList();
                SafeContact mContact = (SafeContact) mContactList.get(0);
                String name = mContact.getContactName();
                String num = mContact.getContactNumber();

                Intent action = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + num));
                startActivity(action);

                mContact1.setText(name);

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

      /*  sharingIntent.putExtra(android.content.Intent.EXTRA, mContact.getNumber());
        sharingIntent.putExtra(Intent.EXTRA_TEXT, default message);*/

        //Start the share
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

}



