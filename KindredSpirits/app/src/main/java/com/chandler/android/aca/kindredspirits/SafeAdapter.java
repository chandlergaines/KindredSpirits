package com.chandler.android.aca.kindredspirits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SafeAdapter extends RecyclerView.Adapter<UpdatesViewHolder>{

    private List<SafeContact> mContactList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    RecyclerView mRecyclerView;
    UpdatesAdapter mAdapter;
    SafeContact contact;

    public SafeAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContactList = new ArrayList<>();
    } //todo you changed this before, added the List param


    @Override
    public UpdatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UpdatesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (mContactList == null) ? 0 : mContactList.size();
    }

    public void setContactList(List<SafeContact> contactList){
        this.mContactList.clear();
        this.mContactList.addAll(contactList);
        notifyDataSetChanged();
    }

    public List<SafeContact> getContactList() {
        return mContactList;
    }

}
