package com.chandler.android.aca.kindredspirits;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HotlineViewHolder extends RecyclerView.ViewHolder {

    private Hotline mHotline;

    public HotlineViewHolder(View itemView) {
        super(itemView);

        TextView txtTitle = (TextView) itemView.findViewById(R.id.hotlineTextTitle);

        TextView txtDescription = (TextView) itemView.findViewById(R.id.hotlineTextDesc);

        TextView txtNumber = (TextView) itemView.findViewById(R.id.hotlineNumber);

     /*   txtTitle.setText(mHotline.getHotlineTitle());
        txtDescription.setText(mHotline.getHotlineTitle());
        txtNumber.setText(mHotline.getHotlineNumber());*/

        Button callButton = (Button) itemView.findViewById(R.id.button);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo put dialer stuff in here
            }
        });

    }

    // Receive a note from the MainActivity //old
    public void sendHotlineSelected(Hotline hotlineSelected) {
        mHotline = hotlineSelected;
    }

}