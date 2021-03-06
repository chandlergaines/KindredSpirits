package com.chandler.android.aca.kindredspirits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HotlineAdapter extends RecyclerView.Adapter<HotlineViewHolder>{

    private List<Hotline> mHotlineList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    RecyclerView mRecyclerView;
    HotlineAdapter mAdapter;
    Hotline hotline;

    public HotlineAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mHotlineList = new ArrayList<>();
    } //todo you changed this before, added the List param

    @Override
    public HotlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.hotline_listitem, parent, false);
        HotlineViewHolder viewHolder = new HotlineViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HotlineViewHolder holder, int position) {
        Hotline mHotline = mHotlineList.get(position);

       /* TextView mTitle = (TextView) mRecyclerView.findViewById(R.id.hotlineTextTitle);
        TextView mDescription = (TextView) mRecyclerView.findViewById(R.id.hotlineTextDesc);
        TextView mNumber = (TextView) mRecyclerView.findViewById(R.id.hotlineNumberTxt);
        Button mCallButton = (Button) mRecyclerView.findViewById(R.id.buttonCall);*/

        holder.mTitle.setText(mHotline.getTitle());
        holder.mDescription.setText(mHotline.getDescription());
        holder.mNumber.setText(mHotline.getNumber());
    }

    @Override
    public int getItemCount() {
        return (mHotlineList == null) ? 0 : mHotlineList.size();
    }

    public void setHotlineList(List<Hotline> hotlineList){
        this.mHotlineList.clear();
        this.mHotlineList.addAll(hotlineList);
        notifyDataSetChanged();
    }

    public List<Hotline> getHotlineList() {
        return mHotlineList;
    }






























   /* @Override
    public Hotline getItem(int whichItem) {
        return mHotlineList.get(whichItem);
    }

    @Override
    public long getItemId(int whichItem) {
        return whichItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }*/ //commented out to toy with adapters

    /*@Override
    public View getView(int whichItem, View view, ViewGroup viewGroup) {

        // Implement this method next
        // Has view been inflated already
        if (view == null) {
            // If not, do so here
            // First create a layout inflater
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Now instantiate view using inflater.inflate
            // using the listitem layout
            view = inflater.inflate(R.layout.hotline_listitem, viewGroup, false);
            // The false parameter is necessary
            // because of the way we want to use listitem
        } // End if

        TextView txtTitle = (TextView) view.findViewById(R.id.hotlineTextTitle);
        TextView txtDescription = (TextView) view.findViewById(R.id.hotlineTextDesc);
        TextView txtNumber = (TextView) view.findViewById(R.id.hotlineNumberTxt);

        Hotline item = mHotlineList.get(whichItem);

        txtTitle.setText(item.getTitle());
        txtDescription.setText(item.getDescription());
        txtNumber.setText(item.getNumber());

        return view;
    }*/ //David said I don't need this based on our movie app
}


