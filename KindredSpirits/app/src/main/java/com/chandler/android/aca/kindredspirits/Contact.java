package com.chandler.android.aca.kindredspirits;

import org.json.JSONException;
import org.json.JSONObject;

public class Contact {

    private String mContactName;
    private String mContactNumber;

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        mContactName = contactName;
    }

    public String getContactNumber() {
        return mContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        mContactNumber = contactNumber;
    }

    private static final String JSON_NAME = "name";
    private static final String JSON_NUMBER = "number";

    public Contact(JSONObject jo) throws JSONException {

        mContactName =  jo.getString(JSON_NAME);
        mContactNumber = jo.getString(JSON_NUMBER);
    }

    public Contact(){}

    public JSONObject convertToJSON() throws JSONException{

        JSONObject jo = new JSONObject();

        jo.put(JSON_NAME, mContactName);
        jo.put(JSON_NUMBER, mContactNumber);

        return jo;
    }

}
