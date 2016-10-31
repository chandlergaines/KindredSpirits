package com.chandler.android.aca.kindredspirits;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HotlineDialogShow extends DialogFragment {

    private Hotline mHotline;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // All the other code goes here

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_hotline_show, null);

        TextView txtTitle = (TextView) dialogView.findViewById(R.id.textView7);

        TextView txtDescription = (TextView) dialogView.findViewById(R.id.textView8);

        TextView txtNumber = (TextView) dialogView.findViewById(R.id.hotlineNumber);

        txtTitle.setText(mHotline.getHotlineTitle());
        txtDescription.setText(mHotline.getHotlineTitle());
        txtNumber.setText(mHotline.getHotlineNumber());

        Button callButton = (Button) dialogView.findViewById(R.id.button);

        builder.setView(dialogView).setTitle("Hotline");

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo put dialer stuff in here
            }
        });


        return builder.create();

    }

    // Receive a note from the MainActivity
    public void sendHotlineSelected(Hotline hotlineSelected) {
        mHotline = hotlineSelected;
    }

}