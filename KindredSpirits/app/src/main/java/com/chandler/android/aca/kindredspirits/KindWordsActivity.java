package com.chandler.android.aca.kindredspirits;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KindWordsActivity extends AppCompatActivity implements SensorEventListener{

    private KindBook mKindBook = new KindBook();
    String kindness;
    String[] array;

    private final static float ACC = 15; //todo keep this or no?
    SensorManager mSensorManager;

    @BindView(R.id.kindText) TextView mKindnessTextView;
    @BindView(R.id.kindBtn) Button mKindButton;
    @BindView(R.id.kindCheck) CheckBox mKindCheck;
    @BindView(R.id.knopeCheck) CheckBox mKnopeCheck;
    @BindView(R.id.affirmationCheck) CheckBox mAffirmationCheck;
    @BindView(R.id.realityCheck) CheckBox mRealityCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_words);

        ButterKnife.bind(this);

        mKindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mKindCheck.isChecked()) {
                    mKindBook.mArrayList.add(mKindBook.mKindness);
                } else {
                    mKindBook.mArrayList.remove(mKindBook.mKindness);
                }

                if (mKnopeCheck.isChecked()) {
                    mKindBook.mArrayList.add(mKindBook.mKnope);
                } else {
                    mKindBook.mArrayList.remove(mKindBook.mKnope);
                }

                if (mAffirmationCheck.isChecked()) {
                    mKindBook.mArrayList.add(mKindBook.mAffirmations);
                } else {
                    mKindBook.mArrayList.remove(mKindBook.mAffirmations);
                }

                if (mRealityCheck.isChecked()) {
                    mKindBook.mArrayList.add(mKindBook.mReality);
                } else {
                    mKindBook.mArrayList.remove(mKindBook.mReality);
                }

                array = mKindBook.getArrays();
                Random randomGenerator = new Random();
                kindness = "Please select a category.";
                int randomNumber = randomGenerator.nextInt(array.length);
                kindness = array[randomNumber];

                mKindnessTextView.setText(kindness);
                mKindButton.setText("ANOTHER!");
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

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

    public void onCheckboxClicked(View view) {
        //Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {

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
    }
}
