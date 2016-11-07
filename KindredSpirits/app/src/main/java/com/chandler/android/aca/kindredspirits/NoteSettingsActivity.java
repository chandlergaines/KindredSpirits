package com.chandler.android.aca.kindredspirits;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteSettingsActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    private boolean mSound;

    //this should not be set up like this, none should be 0, slow 1, and fast 2... can edit later
    public static final int FAST = 0;
    public static final int SLOW = 1;
    public static final int NONE = 2;

/*    public Drawable BKG1;
    public Drawable BKG2;
    public Drawable BKG3;*/

    private int mAnimOption;
    //private int mBackground;

/*    public static final int COLORS = R.drawable.colortiles;
    public static final int PEN = R.drawable.penandnote;
    public static final int WEB = R.drawable.web;*/

    private RelativeLayout mRelativeLayout;

    @BindView(R.id.imageBackground)
    ImageView mBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_settings);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

/*        Picasso
                .with(this)
                .load(R.drawable.colortiles)
                .resize(600,200)
                .centerCrop()
                .into();

        BKG1 = getResources().getDrawable(R.drawable.colortiles); // TODO: 9/28/16 scale down images and try again
        BKG2 = getResources().getDrawable(R.drawable.penandnote);
        BKG3 = getResources().getDrawable(R.drawable.web);*/

        mPrefs = getSharedPreferences("Note to self", MODE_PRIVATE);
        mEditor = mPrefs.edit();

        mSound = mPrefs.getBoolean("sound", true);

        CheckBox checkBoxSound = (CheckBox)findViewById(R.id.checkBoxSound);

        // mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        if(mSound){
            checkBoxSound.setChecked(true);
        } else{
            checkBoxSound.setChecked(false);
        }

        checkBoxSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("sound = ", "" + mSound);
                Log.i("isChecked = ", "" + isChecked);

                // If mSound is true make it false
                // If mSound is false make it true
                mSound = ! mSound;
                mEditor.putBoolean("sound", mSound);

            }
        });// End on checkChanged

        // Now for the radio buttons
        mAnimOption = mPrefs.getInt("anim option", FAST);

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        //Deselect all buttons
        radioGroup.clearCheck();

        //Which radio button should be selected?
        switch(mAnimOption){
            case FAST:
                radioGroup.check(R.id.radioFast);
                break;
            case SLOW:
                radioGroup.check(R.id.radioSlow);
                break;
            case NONE:
                radioGroup.check(R.id.radioNone);
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1){
                    switch (rb.getId()){
                        case R.id.radioFast:
                            mAnimOption = FAST;
                            break;
                        case R.id.radioSlow:
                            mAnimOption = SLOW;
                            break;
                        case R.id.radioNone:
                            mAnimOption = NONE;
                            break;
                    } //End switch block

                    mEditor.putInt("anim option", mAnimOption);
                }// End if statement
            }
        });

        //TRYING TO PICK BACKGROUNDS

        /*RadioGroup bkgRadio = (RadioGroup)findViewById(R.id.bkgRadio);

        //Deselect all buttons
        radioGroup.clearCheck();

        //Which radio button should be selected?
        switch(mBackground){
            case COLORS:
                radioGroup.check(R.id.bkg1);
                break;
            case PEN:
                radioGroup.check(R.id.bkg2);
                break;
            case WEB:
                radioGroup.check(R.id.bkg3);
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton bkgrb = (RadioButton) group.findViewById(checkedId);
                if (null != bkgrb && checkedId > -1) {
                    switch (bkgrb.getId()) {
                        case R.id.bkg1:
                            mBackground = COLORS;
                            mRelativeLayout.setBackground(BKG1);
                            break;
                        case R.id.bkg2:
                            mBackground = PEN;
                            mRelativeLayout.setBackground(BKG2);
                            break;
                        case R.id.bkg3:
                            mBackground = WEB;
                            mRelativeLayout.setBackground(BKG3);
                            break;
                    } //End switch block

                    mEditor.putInt("bkg option", mBackground);
            }// End if statement

            }  // END BACKGROUND MADNESS
        }); */
    } // End onCreate

    @Override
    protected void onPause(){
        super.onPause();

        //Save the settings here
        mEditor.commit();
    } // end onPause Override
} // end class
