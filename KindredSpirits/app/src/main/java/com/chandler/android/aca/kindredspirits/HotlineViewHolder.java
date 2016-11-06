package com.chandler.android.aca.kindredspirits;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class HotlineViewHolder extends RecyclerView.ViewHolder {

    private Hotline mHotline;
    public TextView mTitle;
    public TextView mDescription;
    public TextView mNumber;
    public Button mCallButton;
    Button mDismiss;
    FrameLayout mFrameLayout;

    public HotlineViewHolder(View itemView) {
        super(itemView);

        mTitle = (TextView) itemView.findViewById(R.id.hotlineTextTitle);

        mDescription = (TextView) itemView.findViewById(R.id.hotlineTextDesc);

        mNumber = (TextView) itemView.findViewById(R.id.hotlineNumberTxt);

        mCallButton = (Button) itemView.findViewById(R.id.fragButton);

        mDismiss = (Button) itemView.findViewById(R.id.fragDismiss);

        mFrameLayout = (FrameLayout) itemView.findViewById(R.id.fragmentHolder);

    }

    // Receive a note from the MainActivity //old
    public void sendHotlineSelected(Hotline hotlineSelected) {
        mHotline = hotlineSelected;
    }

}