package com.chandler.android.aca.kindredspirits;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.chandler.android.aca.kindredspirits.R.id.imageView;

public class NoteDialogShow extends DialogFragment {

    private Note mNote;

    private ImageView mImageView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // All the other code goes here

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.note_show_dialog, null);

        TextView txtTitle = (TextView) dialogView.findViewById(R.id.txtTitle);

        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txtDescription);

        txtTitle.setText(mNote.getTitle());
        txtDescription.setText(mNote.getDescription());

/*        //Not done with this chunk of code
        stringUri = uri.toString("photo");

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("photo")){
            Uri = extras.getString("photo");
        }
        //Not done with this chunk of code*/


        ImageView ivImportant = (ImageView) dialogView.findViewById(R.id.imageViewImportant);
        ImageView ivTodo = (ImageView) dialogView.findViewById(R.id.imageViewTodo);
        ImageView ivIdea = (ImageView) dialogView.findViewById(R.id.imageViewIdea);

        ImageView mImageView = (ImageView) dialogView.findViewById(imageView);
        mImageView.setImageURI(mNote.getImage());


        if (!mNote.isImportant()){
            ivImportant.setVisibility(View.GONE);
        }

        if (!mNote.isTodo()){
            ivTodo.setVisibility(View.GONE);
        }

        if (!mNote.isIdea()){
            ivIdea.setVisibility(View.GONE);
        }

        /*ImageView mImageView = (ImageView) dialogView.findViewById(R.id.photoImageView);
        mImageView.setImageURI(mNote.getImage());*/

        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button btnSend = (Button) dialogView.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });


        return builder.create();

    }

    private void shareIt(){
        //Android provided sending intent
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        //What gets shared added in here

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mNote.getTitle());
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Note: " + mNote.getTitle() + "\n \n" + "Description: "+ mNote.getDescription());
        sharingIntent.putExtra(Intent.EXTRA_STREAM, mNote.getImage());

        //Start the share
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    // Receive a note from the MainActivity
    public void sendNoteSelected(Note noteSelected) {
        mNote = noteSelected;
    }

}

