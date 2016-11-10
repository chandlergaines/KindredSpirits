package com.chandler.android.aca.kindredspirits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KindWordsActivity extends AppCompatActivity {

    //TODO Work out the logic of how to add and remove arrays based on the checks

    private KindBook mKindBook = new KindBook();
    String kindness;
    String[] chosen;

    @BindView(R.id.kindText)
    TextView mKindnessTextView;
    @BindView(R.id.kindBtn)
    Button mKindButton;
    @BindView(R.id.kindCheck)
    CheckBox mKindCheck;
    @BindView(R.id.knopeCheck)
    CheckBox mKnopeCheck;
    @BindView(R.id.affirmationCheck)
    CheckBox mAffirmationCheck;
    @BindView(R.id.realityCheck)
    CheckBox mRealityCheck;

    @BindView(R.id.imageBackground)
    ImageView mBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kind_words_activity);

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

        mKindBook.mArrayList.add(mKindBook.mKindness);
        mKindBook.mArrayList.add(mKindBook.mKnope);
        mKindBook.mArrayList.add(mKindBook.mAffirmations);
        mKindBook.mArrayList.add(mKindBook.mReality);

        mKindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (mKindCheck.isChecked()) {
                    mKindBook.mArrayList.add(mKindBook.mKindness);
                }*/
                if (!mKindCheck.isChecked()) {
                    mKindBook.mArrayList.remove(mKindBook.mKindness);
                }

                /*if (mKnopeCheck.isChecked()) {
                    mKindBook.mArrayList.add(mKindBook.mKnope);
                }*/
                if (!mKnopeCheck.isChecked()) {
                    mKindBook.mArrayList.remove(mKindBook.mKnope);
                }

                /*if (mAffirmationCheck.isChecked()) {
                    mKindBook.mArrayList.add(mKindBook.mAffirmations);
                }*/
                if (!mAffirmationCheck.isChecked()) {
                    mKindBook.mArrayList.remove(mKindBook.mAffirmations);
                }

                /*if (mRealityCheck.isChecked()) {
                    mKindBook.mArrayList.add(mKindBook.mReality);
                }*/
                if (!mRealityCheck.isChecked()) {
                    mKindBook.mArrayList.remove(mKindBook.mReality);
                }

                if (mKindBook.mArrayList.size() != 0) {
                    chosen = getArrays();
                    kindness = getKindness();
                } else {
                    kindness = "Please select a category.";
                }

                mKindnessTextView.setText(kindness);
                mKindButton.setText("ANOTHER!");
            }

        });

        mKindCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mKindBook.mArrayList.add(mKindBook.mKindness);
            }
        });
        mKnopeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mKindBook.mArrayList.add(mKindBook.mKnope);
            }
        });
        mAffirmationCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mKindBook.mArrayList.add(mKindBook.mAffirmations);
            }
        });
        mRealityCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mKindBook.mArrayList.add(mKindBook.mReality);
            }
        });

    }

    public String[] getArrays() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mKindBook.mArrayList.size());
        chosen = mKindBook.mArrayList.get(randomNumber);

        return chosen;
    }

    public String getKindness() {
        String kindness;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(chosen.length);
        kindness = chosen[randomNumber];

        return kindness;
    }


    public void onCheckboxClicked(View view) {
        //Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {

        }
    }

}

//Stuff I may use later for reference or for sensor managing


/*    private final static float ACC = 15; //todo keep this or no?
    SensorManager mSensorManager;


    public String[] beRandom(){
        array = mKindBook.getArrays();
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(array.length);
        array = mKindBook.mArrayList[randomNumber];

        return array;
    }





        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);





    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] values = event.values;
        if ((Math.abs(values[0]) > ACC || Math.abs(values[1]) > ACC || Math
                .abs(values[2]) > ACC)) { // X, Y, Z axis to measure location and force on those axes
            kindness = mKindBook.getKindness();
            mKindnessTextView.setText(kindness);

            for (float i=0; i<200_000; i++) {

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }*/

