package com.chandler.android.aca.kindredspirits;

import java.io.Serializable;
import java.util.List;

public class Updates implements Serializable{

    private String mSubject;

    private String mMessage;

    public Updates() {

    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        this.mSubject = subject;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }


    public static class UpdatesResult {
        private List<Updates> results;

        public List<Updates> getResults() {
            return results;
        }
    }

}

