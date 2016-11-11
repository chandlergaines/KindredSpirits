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


    private int mAnimOption;
    //private int mBackground;


    private RelativeLayout mRelativeLayout;

    @BindView(R.id.imageBackground)
    ImageView mBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_settings_activity);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);


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


    } // End onCreate

    @Override
    protected void onPause(){
        super.onPause();

        //Save the settings here
        mEditor.commit();
    } // end onPause Override
} // end class
