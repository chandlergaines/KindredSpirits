package com.chandler.android.aca.kindredspirits;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Hotline implements Serializable{

    @SerializedName("hotlines")
    private int mHotlineItem;

    @SerializedName("title")
    private String mHotlineTitle;

    @SerializedName("description")
    private String mHotlineDescription;

    @SerializedName("number")
    private String mHotlineNumber;

    public Hotline(){

    }


    public int getHotlineItem() {
        return mHotlineItem;
    }

    public void setHotlineItem(int hotlineItem) {
        mHotlineItem = hotlineItem;
    }

    public String getHotlineNumber() {
        return mHotlineNumber;
    }

    public void setHotlineNumber(String hotlineNumber) {
        this.mHotlineNumber = hotlineNumber;
    }

    public String getHotlineTitle() {
        return mHotlineTitle;
    }

    public void setHotlineTitle(String hotlineTitle) {
        this.mHotlineTitle = hotlineTitle;
    }

    public String getHotlineDescription() {
        return mHotlineDescription;
    }

    public void setHotlineDescription(String hotlineDescription) {
        this.mHotlineDescription = hotlineDescription;
    }

    public static class HotlineResult{
        private List<Hotline> results;

        public List<Hotline> getResults() {
            return results;
        }
    }

    //todo create a string array resource
}
