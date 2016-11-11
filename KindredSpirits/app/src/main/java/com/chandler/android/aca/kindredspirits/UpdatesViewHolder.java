package com.chandler.android.aca.kindredspirits;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class UpdatesViewHolder extends RecyclerView.ViewHolder {

    private Updates mUpdates;
    public TextView mSubject;
    public TextView mMessage;

    public UpdatesViewHolder(View itemView) {
        super(itemView);

        mSubject = (TextView) itemView.findViewById(R.id.updatesSubject);

        mMessage = (TextView) itemView.findViewById(R.id.updatesMessage);

    }

    // Receive a note from the MainActivity //old
    public void sendUpdatesSelected(Updates updatesSelected) {
        mUpdates = updatesSelected;
    }

}