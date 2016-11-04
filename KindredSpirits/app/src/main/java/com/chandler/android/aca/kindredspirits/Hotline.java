package com.chandler.android.aca.kindredspirits;

import java.io.Serializable;
import java.util.List;

public class Hotline implements Serializable{

    private String mTitle;

    private String mDescription;

    private String mNumber;

    public Hotline(){

    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public static class HotlineResult{
        private List<Hotline> results;

        public List<Hotline> getResults() {
            return results;
        }
    }

    //todo create a string array resource
}
