package com.example.tarde.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements SensorEventListener {

    private Sensor sAcelerometro;
    private Sensor sMagnetico;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sMagnetico = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, sAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sMagnetico, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //event.accuracy;
        float[] values = event.values;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Toast.makeText(this, "x: " + values[0] + ", y: " + values[1] + ", z:" + values[2] +" G", Toast.LENGTH_SHORT).show();
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            Toast.makeText(this, "x: " + values[0] + ", y: " + values[1] + ", z:" + values[2] + " uT", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
