package com.chandler.android.aca.kindredspirits;

import java.io.Serializable;
import java.util.List;

public class SafeContact implements Serializable{

    private String mContactName;
    private String mContactNumber;

    public SafeContact(){}

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        this.mContactName = contactName;
    }

    public String getContactNumber() {
        return mContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.mContactNumber = contactNumber;
    }


    public static class ContactResult {
        private List<SafeContact> results;

        public List<SafeContact> getResults() {
            return results;
        }
    }


}
