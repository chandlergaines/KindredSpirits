package com.chandler.android.aca.kindredspirits;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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

public class UpdatesActivity extends AppCompatActivity {


    UpdatesAdapter mUpdatesAdapter;
    List mUpdatesList;
    Updates mUpdates;

    LinearLayoutManager mLayoutManager;
    RecyclerView mRecyclerView;

    DatabaseReference mDataRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mDataRootRef.child("updates");

    @BindView(R.id.imageBackground)
    ImageView mBackground;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updates_activity);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

        // FirebaseDatabase.getInstance().setPersistenceEnabled(true)


        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = (RecyclerView) findViewById(R.id.listViewUpdates);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        mConditionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUpdatesList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {

                    mUpdates = new Updates();
                    mUpdates = dataSnapshot.child("" + (i + 1)).getValue(Updates.class);
                    mUpdatesList.add(mUpdates);

                }

                mUpdatesAdapter = new UpdatesAdapter(UpdatesActivity.this);
                mRecyclerView.setAdapter(mUpdatesAdapter);
                mUpdatesAdapter.setUpdatesList(mUpdatesList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        mProgressDialog.dismiss();


        mRecyclerView.addOnItemTouchListener(new RecyclerOnClick(getApplicationContext(),
                mRecyclerView, new RecyclerOnClick.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                mUpdatesAdapter.getUpdatesList();
                Updates selected = (Updates) mUpdatesList.get(itemPosition);

                final String mSubject = selected.getSubject();
                final String mMessage = selected.getMessage();

                Bundle updatesBundle = new Bundle();
                updatesBundle.putString("subject", mSubject);
                updatesBundle.putString("message", mMessage);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


    }

    @Override
    protected void onStart() {
        super.onStart();

        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.v("DataSnapshot ", "Value is: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Failed to read value ", "Life sux");
            }
        });

    }
}

