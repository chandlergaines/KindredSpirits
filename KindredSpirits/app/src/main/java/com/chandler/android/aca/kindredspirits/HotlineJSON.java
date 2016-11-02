package com.chandler.android.aca.kindredspirits;

public class HotlineJSON {

    private int mItem;
    private String mTitle;
    private String mDesc;
    private String mNum;

    public int getItem() {
        return mItem;
    }

    public void setItem(int item) {
        mItem = item;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getNum() {
        return mNum;
    }

    public void setNum(String num) {
        mNum = num;
    }

    public static class HotlineJSONReturn{
        private HotlineJSON mHotlineJSON;
        public HotlineJSON getHotlineJSON(){
            return mHotlineJSON;
        }
    }
}
