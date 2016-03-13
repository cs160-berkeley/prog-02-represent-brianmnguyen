package com.example.brian.rep;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends WearableActivity  {

    String[] Peeps;
    //---The images to display---
    //---Here we use a 2D array for row/column indexing---
    private SensorManager mSensorManager;

    private ShakeEventListener mSensorListener;

    private String zip1;

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("WE CREATE", "THINGS HERE");
        final Resources res = getResources();
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);

        Intent in = getIntent();
        String zip1 = in.getStringExtra("LOCATION");
        Log.i("THIS IS IT WE", "ARE SO FINISHED HERE OMG " + zip1);
        Peeps = zip1.split(";");
        //---Assigns an adapter to provide the content for this pager---
        pager.setAdapter(new ImageAdapter(this, zip1));
        DotsPageIndicator dotsPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        dotsPageIndicator.setPager(pager);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        Log.i("NOT DONE", "LAWL");

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener()
        {


            public void onShake() {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                Random rn = new Random();
                Integer number = (int) rn.nextFloat() * 10000;

                sendIntent.putExtra("zip", "94401");
                Log.i("FUCK", "LAWL" + number.toString());
                startService(sendIntent);
            }
        });


    }

    public class ImageAdapter extends GridPagerAdapter{
        final Context mContext;
        String zip;
        public ImageAdapter(final Context context, String zip1) {
            mContext = context;
            zip = zip1;
            Log.i("DID WE EVEN", "LIKE REALLY GE HERE" + zip);
        }


        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount(int i) {
            return Peeps.length;
        }

        //---Go to current column when scrolling up or down (instead of default column 0)---
        @Override
        public int getCurrentColumnForRow(int row, int currentColumn) {
            return currentColumn;
        }

        //---Return our car image based on the provided row and column---
        @Override
        public Object instantiateItem(ViewGroup viewGroup, int row, int col) {
            Log.i("DID WE EVEN", "LIKE REALLY GE HERE" + zip);

            TextView textView;
            textView = new TextView(mContext);
            if (col < Peeps.length-1){
                String[] ary2 = Peeps[col].split(":");
                textView.setText( ary2[2]+"\n" +ary2[0]+"\n" + ary2[1]);
                textView.setBackgroundColor(Color.rgb(30, 144, 255));
                textView.setTextColor(0xffffffff);
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                        sendIntent.putExtra("zip", "Dianne");
                        Log.i("THE EAGEL HAS LANDED", "LOL");
                        startService(sendIntent);

                    }
                });

            }
            else{
                String[] ary2 = Peeps[col].split(":");
                textView.setText("2012 Presidential Vote\n"+ary2[0]+ "\n" + ary2[1]+ "\n"+"\n Obama 55% Romney 45%");
                textView.setBackgroundColor(Color.rgb(30, 144, 255));
                textView.setTextColor(0xffffffff);

            }

            textView.setGravity(Gravity.CENTER);
            viewGroup.addView(textView);

            return textView;
        }

        @Override
        public void destroyItem(ViewGroup viewGroup, int i, int i2, Object o) {
            viewGroup.removeView((View) o);
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view.equals(o);
        }
    }
}





