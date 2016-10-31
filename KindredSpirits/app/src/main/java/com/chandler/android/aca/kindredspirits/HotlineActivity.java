package com.chandler.android.aca.kindredspirits;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HotlineActivity extends AppCompatActivity {

    List<Hotline> hotlineList = new ArrayList<Hotline>();
    private HotlineAdapter mHotlineAdapter;
    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference;

    //todo build the thing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);

        mDatabaseReference = mFirebaseDatabase.getReference();

        mHotlineAdapter = new HotlineAdapter();
        ListView listHotline = (ListView) findViewById(R.id.listViewHotline);
        listHotline.setAdapter(mHotlineAdapter);

        listHotline.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int whichITem, long id){

                /*
                Create a temp note
                which is a reference to the note
                that has just been clicked
                 */
                Hotline item = mHotlineAdapter.getItem(whichITem);

                // Create a new dialog window
                HotlineDialogShow dialog = new HotlineDialogShow();
                // Send in a reference to the hotline to be shown
                dialog.sendHotlineSelected(item);

                // Show the dialog window with the hotline in it
                dialog.show(getFragmentManager(), "");
            }
        });
    }

    public class HotlineAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return hotlineList.size();
        }

        @Override
        public Hotline getItem(int whichItem) {
            return hotlineList.get(whichItem);
        }

        @Override
        public long getItemId(int whichItem) {
            return whichItem;
        }

        @Override
        public View getView(int whichItem, View view, ViewGroup viewGroup) {

            // Implement this method next
            // Has view been inflated already
            if (view == null) {
                // If not, do so here
                // First create a layout inflater
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // Now instantiate view using inflater.inflate
                // using the listitem layout
                view = inflater.inflate(R.layout.listitem_hotline, viewGroup, false);
                // The false parameter is necessary
                // because of the way we want to use listitem
            } // End if

            // Grab a reference to TextView and ImageView widgets
            TextView txtTitle = (TextView) view.findViewById(R.id.textView7);
            TextView txtDescription = (TextView) view.findViewById(R.id.textView8);
            TextView txtNumber = (TextView) view.findViewById(R.id.hotlineNumberTxt);

            // Hide any ImageView widgets that are not relevant
            Hotline item = hotlineList.get(whichItem);

            // Add the text to the heading and description
            txtTitle.setText(item.getHotlineTitle());
            txtDescription.setText(item.getHotlineDescription());
            txtNumber.setText(item.getHotlineNumber());

            return view;
        }
    }
}
