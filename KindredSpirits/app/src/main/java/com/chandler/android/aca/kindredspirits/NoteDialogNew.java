package com.chandler.android.aca.kindredspirits;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteDialogNew extends DialogFragment{

    private static final int CAMERA_REQUEST = 123;
    private ImageView mImageView;
    private Button mAddPhoto;
    //todo bindview these things when you have the sanity


    public String mCurrentPhotoPath;
    // The filepath for the photo
    // set to hold image.absolutepath
    //Where the captured image is stored

    public Uri mImageUri = Uri.EMPTY;
    public String mImageString;

    // Create a new note
    Note newNote = new Note();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // All the rest of the code goes here

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.note_new_dialog, null);

        //binding the things
        final EditText editTitle = (EditText) dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = (EditText) dialogView.findViewById(R.id.editDescription);
        final CheckBox checkBoxIdea = (CheckBox) dialogView.findViewById(R.id.checkBoxIdea);
        final CheckBox checkBoxTodo = (CheckBox) dialogView.findViewById(R.id.checkBoxTodo);
        final CheckBox checkBoxImportant = (CheckBox) dialogView.findViewById(R.id.checkBoxImportant);
        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);

        mAddPhoto = (Button) dialogView.findViewById(R.id.btnAddPhoto);
        mImageView = (ImageView) dialogView.findViewById(R.id.photoImageView);

        builder.setView(dialogView);

        // Handle the cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Handle the OK button
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Set its variables to match the users' entries on the form
                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDescription.getText().toString());
                newNote.setIdea(checkBoxIdea.isChecked());
                newNote.setTodo(checkBoxTodo.isChecked());
                newNote.setImportant(checkBoxImportant.isChecked());

                if (mImageUri != null){
                    newNote.setImageString(mImageString);}

                // Get a reference to MainActivity
                NoteActivity callingActivity = (NoteActivity) getActivity();

                // Pass newNote back to MainActivity
                callingActivity.createNewNote(newNote);

                // Quit the dialog
                dismiss();
            }

        });

        mAddPhoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File photoFile = null;
                try{
                    photoFile = createImageFile();
                } catch (IOException ex){
                    //Error occured while creating the file
                    Log.e("error", "error creating file");
                }
                //Continue only if the file was successfully created
                if(photoFile != null){
                    mImageUri = Uri.fromFile(photoFile);
                    //used to get the Uri which points to the new file

                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            //putExtra used to tell the camera app to make this uri point to the saved image
                            Uri.fromFile(photoFile));
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        return builder.create();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {


            try {
                newNote.setImageUri(Uri.parse(mImageUri.toString()));
                mImageView.setImageURI(Uri.parse(mImageUri.toString()));

                //This is important and needs to go along to ShowNote

            }catch(Exception e){
                Log.e("Error","Uri not set");
            }

        }else{
            mImageUri = Uri.EMPTY;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mImageUri != Uri.EMPTY) {
            // Make sure we don't run out of memory
            BitmapDrawable bd = (BitmapDrawable) mImageView.getDrawable();
            bd.getBitmap().recycle();
            mImageView.setImageBitmap(null);}
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // filename
                ".jpg",         // extension
                storageDir      // folder
        );

        // Save for use with ACTION_VIEW Intent
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

}
