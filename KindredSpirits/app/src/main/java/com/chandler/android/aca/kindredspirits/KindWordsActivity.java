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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KindWordsActivity extends AppCompatActivity implements SensorEventListener{

    private KindBook mKindBook = new KindBook();
    String kindness;

    private final static float ACC = 15; //todo keep this or no?
    SensorManager mSensorManager;

    @BindView(R.id.kindText) TextView mKindnessTextView;
    @BindView(R.id.kindBtn) Button mKindButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_words);

        ButterKnife.bind(this);

        mKindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kindness = mKindBook.getKindness();
                mKindnessTextView.setText(kindness);
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
