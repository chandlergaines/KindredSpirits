package com.chandler.android.aca.kindredspirits;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;

public class SafeCircleFragment extends Fragment {

    @BindView(R.id.editTextContactName)
    EditText mContactName;
    @BindView(R.id.editTextContactNumber)
    EditText mContactNumber;
    @BindView(R.id.contactButton)
    Button mContactButton;

    String mNameString;
    String mNumberString;

    Context mContext;

    private View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.safe_circle_list, container, false);

        mContext.getApplicationContext();


        PreferenceManager.getDefaultSharedPreferences(mContext).edit().putString("MYCONTACT", "myStringToSave").commit();


        return mView;
    }


}

/*
    ContactsContract mContactsContract;

    //todo get the search string from the user

    //TODO Define global variables
    */
/*
     * Defines an array that contains column names to move from
     * the Cursor to the ListView.
     *//*

    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                    ContactsContract.Contacts.DISPLAY_NAME
    };


    */
/*
     * Defines an array that contains resource ids for the layout views
     * that get the Cursor column contents. The id is pre-defined in
     * the Android framework, so it is prefaced with "android.R.id"
     *//*

    private final static int[] TO_IDS = {
            android.R.id.text1
    };

    // Define global mutable variables
    // Define a ListView object
    ListView mContactsList;
    // Define variables for the contact the user selects
    // The contact's _ID value
    long mContactId;
    // The contact's LOOKUP_KEY
    String mContactKey;
    // A content URI for the selected contact
    Uri mContactUri;
    // An adapter that binds the result Cursor to the ListView
    private SimpleCursorAdapter mCursorAdapter;
    private View mView;
    //TODO End global variables

    //Initialize the fragment
    //Empty public constructor, required by the system
    public SafeCircleFragment () {}

    //A UI Fragment must inflate its view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.safe_circle_list, container, false);

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);

        // Gets the ListView from the View list of the parent activity
        mContactsList = (ListView) mView.findViewById(R.id.lists);

        // Gets a CursorAdapter
        mCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.safe_circle_item,
                null,
                FROM_COLUMNS, TO_IDS,
                0);
        // Sets the adapter for the ListView
        mContactsList.setAdapter(mCursorAdapter);

        // Set the click listener to the current fragment
        mContactsList.setOnItemClickListener(this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        */
/*
         * Makes search string into pattern and
         * stores it in the selection array
         *//*

        mSelectionArgs[0] = "%" + mSearchString + "%";
        // Starts the query
        return new CursorLoader(
                getActivity(),
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                mSelectionArgs,
                null
        );
    }

    @SuppressLint("InlinedApi")
    private static final String[] PROJECTION =
            {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    Build.VERSION.SDK_INT
                            >= Build.VERSION_CODES.HONEYCOMB ?
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                            ContactsContract.Contacts.DISPLAY_NAME

            };


    // The column index for the _ID column
    private static final int CONTACT_ID_INDEX = 0;
    // The column index for the LOOKUP_KEY column
    private static final int LOOKUP_KEY_INDEX = 1;

    // Defines the text expression
    @SuppressLint("InlinedApi")
    private static final String SELECTION =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?" :
                    ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
    // Defines a variable for the search string
    private String mSearchString;
    // Defines the array to hold values that replace the ?
    private String[] mSelectionArgs = { mSearchString };

    //Define onItemClick
    @Override
    public void onItemClick(
        AdapterView<?> parent, View item, int position, long rowID) {
        // Get the Cursor
        Cursor cursor = ((SimpleCursorAdapter) parent.getAdapter()).getCursor();
        // Move to the selected contact
        cursor.moveToPosition(position);
        // Get the _ID value
        mContactId = cursor.getLong(CONTACT_ID_INDEX);
        // Get the selected LOOKUP KEY
        mContactKey = cursor.getString(LOOKUP_KEY_INDEX);
        // Create the contact's content Uri
        mContactUri = ContactsContract.Contacts.getLookupUri(mContactId, mContactKey);
        */
/*
         * You can use mContactUri as the content URI for retrieving
         * the details for a contact.
         *//*

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Put the result Cursor in the adapter for the ListView
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Delete the reference to the existing Cursor
        mCursorAdapter.swapCursor(null);

    }
}
*/
