package com.chandler.android.aca.kindredspirits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BreathingActivity extends AppCompatActivity{
       // implements View.OnClickListener, Animation.AnimationListener{

    Animation mAnimZoomInOut;
   // Animation mAnimZoomOut;
    int mSeekSpeedProgress = 3000;
    int repeat;

    @BindView(R.id.imageViewBreathing) ImageButton mBreathingImage;
    @BindView(R.id.seekBar) SeekBar mSeekBar;
    @BindView(R.id.textViewSeeker) TextView mSeekerText;
    @BindView(R.id.buttonPause) Button mPauseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);

        ButterKnife.bind(this);
        mBreathingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAnimZoomInOut.setDuration(mSeekSpeedProgress);
                mBreathingImage.startAnimation(mAnimZoomInOut);

            }
        });

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBreathingImage.setAnimation(null);

            }
        });


        mSeekerText.setText("Change the pace using the seekbar");
        loadAnimations();
        repeat = 30;

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                mSeekSpeedProgress = value + 3000;
                mSeekerText.setText("" + (mSeekSpeedProgress/1000) + " seconds");
                mAnimZoomInOut.setDuration(mSeekSpeedProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void loadAnimations() {

        mAnimZoomInOut = AnimationUtils.loadAnimation(this, R.anim.zoom_in_out);

    }

   /* @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.imageViewBreathing:
                mAnimZoomInOut.setDuration(mSeekSpeedProgress);
                mAnimZoomInOut.setAnimationListener(this);
                mBreathingImage.startAnimation(mAnimZoomInOut);
                break;

            case R.id.buttonPause:
                v.clearAnimation();
                break;
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
*/
}
