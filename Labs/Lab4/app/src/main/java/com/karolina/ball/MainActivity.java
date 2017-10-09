package com.karolina.ball;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor sensor;
    private ImageView imageView;//Ball variable
    private int radius;//Ball radius
    private int width;//Screen width
    private int height;//Screen height
    private boolean isGravity = true;//Is Gravity sensor is available
    private float[] gravity = new float[3];//Values of gravity on axes
    //Variables for low-pass filter
    private float time;//Current frame time
    private float timePrev = System.nanoTime();//Previous frame time
    private float timeConst = 0.297f;//Time constant for low-pass filter
    private float alpha;//Auxiliary variable for low-pass filter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        //Check whether Gravity sensotr is available.
        //If not, we should use Accelerometer sensor with low-pass filter
        if(sensor == null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isGravity = false;
        }
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        imageView = (ImageView) findViewById(R.id.ball);
        FrameLayout.LayoutParams paramsBall = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        radius = paramsBall.width/2;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY || event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float mult = 10f;//Variable for bigger speed of changing position of the ball
            if(isGravity) {
                gravity[0] = event.values[0];
                gravity[1] = event.values[1];
                gravity[2] = event.values[2];
            }
            else{
                //It's accelerometer sensor. We need to use low-pass filter.
                time = System.nanoTime();
                float dt = ((time - timePrev)/ (float)(Math.pow(10, 9)));
                timePrev = time;
                alpha = timeConst/(timeConst + dt);
                gravity[0] = alpha*gravity[0] + (1 - alpha)*event.values[0];
                gravity[1] = alpha*gravity[1] + (1 - alpha)*event.values[1];
                gravity[2] = alpha*gravity[0] + (1 - alpha)*event.values[2];
            }

            imageView.setX(imageView.getX() - mult * gravity[0]);
            imageView.setY(imageView.getY() + mult * gravity[1]);
            float x = imageView.getX();
            float y = imageView.getY();
            //Checking whether ball is not behind the screen
            if(x <=0) {
                imageView.setX(0);
            }
            if(x + 2*radius>= width){
                imageView.setX(width - 2*radius);
            }
            if(y<= 0) {
                imageView.setY(0);
            }
            if(y + 2*radius>= height){
                imageView.setY(height - 2*radius);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
