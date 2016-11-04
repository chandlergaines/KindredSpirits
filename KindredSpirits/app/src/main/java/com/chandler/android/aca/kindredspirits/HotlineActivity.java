package com.chandler.android.aca.kindredspirits;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HotlineActivity extends AppCompatActivity {

    private HotlineAdapter mHotlineAdapter;

    List mHotlineList;

    LinearLayoutManager mLayoutManager;

    DatabaseReference mDataRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mDataRootRef.child("hotlines");

    RecyclerView mRecyclerView;
    Hotline mHotline;

    //todo build the thing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_hotline);

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
                // final List<Hotline> hotline = new ArrayList<>();

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

                Intent intent = new Intent(getApplicationContext(), HotlineDetail.class);
                intent.putExtra("hotlines", mHotlineAdapter.getHotlineList().get(position));
                startActivity(intent);

                // Get a fragment manager
                FragmentManager fManager = getFragmentManager();

                // Create a new fragment using the manager
                // Passing in the id of the layout to hold it
                Fragment frag = fManager.findFragmentById(R.id.fragmentHolder);

                // Check the fragment has not already been initialized
                if (frag == null) {

                    // Initialize the fragment based on our SimpleFragment
                    frag = new HotlineDetail();
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

               /* for(int i = 1; i<= 12; i++) {
                    for (DataSnapshot hotlineSnapshot : dataSnapshot.getChildren()) {
                        String title = (String) hotlineSnapshot.child(""+i).child("title").getValue();
                        String number = (String) hotlineSnapshot.child(""+i).child("number").getValue();
                        String description = (String) hotlineSnapshot.child(""+i).child("description").getValue();
                    }
                }*/
                Log.v("E_VALUE", "Value is: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Failed to read value", "Life sux");
            }
        });

    }



  /*  public void myRetrofit() {
        Retrofit restAdapter = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://kindred-spirits.firebaseio.com/")
                .build();

        HotlineApiService apiService = restAdapter.create(HotlineApiService.class);

        Call<Hotline.HotlineResult> call = apiService.getHotlines();
        call.enqueue(new Callback<Hotline.HotlineResult>() {
            @Override
            public void onResponse(Call<Hotline.HotlineResult> call, Response<Hotline.HotlineResult> response) {
                mHotlineAdapter.setHotlineList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Hotline.HotlineResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }*/
}



   /* public void getHotlines(final String title, final String desc, final String num) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference("mHotlineList");
        mHotline = new Hotline();

        //todo without the child it will return a list or a hashmap

        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List mHotlineList = new ArrayList<>();
                for (DataSnapshot hotlineSnapshot : dataSnapshot.getChildren()) {
                    Hotline hotline = hotlineSnapshot.getValue(Hotline.class);
                    mHotlineList.add(hotline);
                }

                mHotline.setHotlineItem(hotlinePosition);
                List<String> details = new ArrayList<String>();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/


 /*listHotline.setAdapter(mHotlineAdapter);
        listHotline.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int whichITem, long id) {

                Hotline item = mHotlineAdapter.getItem(whichITem);

                // Create a new dialog window
                HotlineViewHolder dialog = new HotlineViewHolder();

                // Send in a reference to the hotline to be shown
                dialog.sendHotlineSelected(item);

                // Show the dialog window with the hotline in it
                dialog.show(getFragmentManager(), "");
            }
        });*/

        /*mDataRootRef.child("hotline").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // hotlineList.add();
                // todo set this up so it updates data
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
