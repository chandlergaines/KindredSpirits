
package com.chandler.android.aca.kindredspirits;

import android.content.Intent;
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

    String mContactId;
    String mContactName;
    String mContactNumber;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_circle);

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

        /*Button button = (Button)findViewById(R.id.pickcontact);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });*/
/*



        @Override public void onActivityResult(int reqCode, int resultCode, Intent data){ super.onActivityResult(reqCode, resultCode, data);

            switch(reqCode)
            {
                case (PICK_CONTACT):
                    if (resultCode == Activity.RESULT_OK)
                    {
                        Uri contactData = data.getData();
                        Cursor c = managedQuery(contactData, null, null, null, null);
                        if (c.moveToFirst())
                        {
                            String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                            String hasPhone =
                                    c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                            if (hasPhone.equalsIgnoreCase("1"))
                            {
                                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                                phones.moveToFirst();
                                String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                // Toast.makeText(getApplicationContext(), cNumber, Toast.LENGTH_SHORT).show();
                                setCn(cNumber);
                            }
                        }
                    }
            }
        }

        mContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        mContactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
        mContactNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
*/

    }
}

