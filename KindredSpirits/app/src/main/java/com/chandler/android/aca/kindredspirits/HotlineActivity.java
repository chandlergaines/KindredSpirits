package com.chandler.android.aca.kindredspirits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotlineActivity extends AppCompatActivity {

    List<Hotline> hotlineList = new ArrayList<Hotline>();
    private HotlineAdapter mHotlineAdapter;

    DatabaseReference mDataRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mDataRootRef.child("hotlines");

    HotlineJSON mHotlineJSON;

    RecyclerView mRecyclerView;
    Hotline mHotline;

    int hotlinePosition = 0;

    //todo build the thing

    TextView mHotlineTitle;
    TextView mHotlineDesc;
    TextView mHotlineNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);

        mRecyclerView = (RecyclerView) findViewById(R.id.listViewHotline);

        mHotlineTitle = (TextView) findViewById(R.id.hotlineTextTitle);
        mHotlineDesc = (TextView) findViewById(R.id.hotlineTextDesc);
        mHotlineNumber = (TextView) findViewById(R.id.hotlineNumberTxt);


       // final ListView listHotline = (ListView) findViewById(R.id.listViewHotline);

        /*final ArrayAdapter <String> adapter = new ArrayAdapter<>
                (this, R.layout.listitem_hotline, );*/

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHotlineAdapter = new HotlineAdapter(this);
        mRecyclerView.setAdapter(mHotlineAdapter);
        final List<Hotline> hotline = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            hotline.add(new Hotline());
        }
        mHotlineAdapter.setHotlineList(hotline);



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

    }

    @Override
    protected void onStart() {
        super.onStart();
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mHotlineTitle.setText(text);
                Log.e("Database pull result", "Value is: " + text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Failed to read value", "Life sux");
            }
        });

    }

    public void getHotlines(final String title, final String desc, final String num){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference("hotlines");
        mHotlineJSON = new HotlineJSON();

        //todo without the child it will return a list or a hashmap

        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mHotlineJSON = null;
                mHotlineJSON = dataSnapshot.getValue(HotlineJSON.class);
                hotlinePosition = mHotlineJSON.getItem();
                hotlinePosition++;
                mHotlineJSON.setItem(hotlinePosition);


                mHotlineJSON.setItem(hotlinePosition);
                List<String> details = new ArrayList<String>();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void myRetrofit() {
        Retrofit restAdapter = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://kindred-spirits.firebaseio.com/")
                .build();
    }
}
