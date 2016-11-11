package com.chandler.android.aca.kindredspirits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class UpdatesAdapter extends RecyclerView.Adapter<UpdatesViewHolder>{

    private List<Updates> mUpdatesList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    RecyclerView mRecyclerView;
    UpdatesAdapter mAdapter;
    Updates updates;

    public UpdatesAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mUpdatesList = new ArrayList<>();
    } //todo you changed this before, added the List param

    @Override
    public UpdatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.updates_listitem, parent, false);
        UpdatesViewHolder viewHolder = new UpdatesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UpdatesViewHolder holder, int position) {
        Updates mUpdates = mUpdatesList.get(position);

        if(mUpdates != null) {
            holder.mSubject.setText(mUpdates.getSubject());
            holder.mMessage.setText(mUpdates.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return (mUpdatesList == null) ? 0 : mUpdatesList.size();
    }

    public void setUpdatesList(List<Updates> updatesList){
        this.mUpdatesList.clear();
        this.mUpdatesList.addAll(updatesList);
        notifyDataSetChanged();
    }

    public List<Updates> getUpdatesList() {
        return mUpdatesList;
    }

}


