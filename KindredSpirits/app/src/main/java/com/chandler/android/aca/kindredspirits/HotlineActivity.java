package com.chandler.android.aca.kindredspirits;

import android.app.Fragment;
import android.app.FragmentManager;
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

public class HotlineActivity extends AppCompatActivity {

    private HotlineAdapter mHotlineAdapter;
    List mHotlineList;
    Hotline mHotline;

    LinearLayoutManager mLayoutManager;
    RecyclerView mRecyclerView;

    DatabaseReference mDataRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mDataRootRef.child("hotlines");

    @BindView(R.id.imageBackground)
    ImageView mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = (RecyclerView) findViewById(R.id.listViewHotline);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mConditionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mHotlineList = new ArrayList<>();
                for (int i = 0; i < 11; i++) {

                    mHotline = new Hotline();
                    mHotline = dataSnapshot.child("" + (i + 1)).getValue(Hotline.class);
                    mHotlineList.add(mHotline);

                }

                mHotlineAdapter = new HotlineAdapter(HotlineActivity.this);
                mRecyclerView.setAdapter(mHotlineAdapter);
                mHotlineAdapter.setHotlineList(mHotlineList);

                   /* mHotline.setHotlineItem(hotlinePosition);
                    List<String> details = new ArrayList<String>();*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mRecyclerView.addOnItemTouchListener(new RecyclerOnClick(getApplicationContext(),
                mRecyclerView, new RecyclerOnClick.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                mHotlineAdapter.getHotlineList();
                Hotline selected = (Hotline) mHotlineList.get(itemPosition);

                final String mTitle = selected.getTitle();
                final String mNumber = selected.getNumber();
                final String mDescription = selected.getDescription();

                Bundle hotlineBundle = new Bundle();
                hotlineBundle.putString("title", mTitle);
                hotlineBundle.putString("number", mNumber);
                hotlineBundle.putString("description", mDescription);

                // Get a fragment manager
                FragmentManager fManager = getFragmentManager();

                // Create a new fragment using the manager
                // Passing in the id of the layout to hold it
                Fragment frag = new Fragment();
                frag = fManager.findFragmentById(R.id.fragmentHolder);

                // Check the fragment has not already been initialized
                if (frag == null) {

                    // Initialize the fragment based on our SimpleFragment
                    frag = new HotlineDetailFragment();
                    frag.setArguments(hotlineBundle);
                    fManager.beginTransaction()
                            .add(R.id.fragmentHolder, frag)
                            .commit();

                }
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