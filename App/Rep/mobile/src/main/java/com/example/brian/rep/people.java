package com.example.brian.rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class people extends AppCompatActivity {

    private String[] actualNames;
    private String[] actualid;
    private String[] actualParties;
    private String[] actualEmails;
    private String[] actualWebsites;
    private String[] actualEndDates;
    private String[] actualTitle;
    private String[] bioGuideIds;
    private String[] pic;
    private String[] bill4;
    private String[] com4;
    private String[] pic1;
    private String bills;
    private String bill1;
    private String bill0;
    private String bill2;
    private String com0;
    private String com1;
    private String com2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        Intent hi = getIntent();
        Bundle extras1 = hi.getExtras();
        bill4 = extras1.getStringArray("bill0");
        actualEndDates = extras1.getStringArray("enddate");
        actualNames = extras1.getStringArray("name");
        actualTitle = extras1.getStringArray("title");
        actualid = extras1.getStringArray("title");
        com4 = extras1.getStringArray("com0");
        bioGuideIds = extras1.getStringArray("id");
        if (bill4 != null){
            pic = new String[bill4.length];
            for (int i = 0; i < pic.length; i++) {
                if (i == 0) {
                    bill0 = bill4[i];
                }
                if (i == 1) {
                    bill1 = bill4[i];
                }
                if (i == 2) {
                    bill2 = bill4[i];
                }
            }


            TextView bill5 = (TextView) findViewById(R.id.bill0);
            TextView bill6 = (TextView) findViewById(R.id.bill1);
            TextView bill7 = (TextView) findViewById(R.id.bill2);

            bill5.setText(bill0);
            bill6.setText(bill1);
            bill7.setText(bill2);


        }

        if (com4 != null) {
            pic1 = new String[com4.length];
            for (int i = 0; i < pic.length; i++) {
                if (i == 0) {
                    com0 = com4[i];
                }
                if (i == 1) {
                    com1 = com4[i];
                }
                if (i == 2) {
                    com2 = com4[i];
                }
            }

            TextView bill5 = (TextView) findViewById(R.id.com0);
            TextView bill6 = (TextView) findViewById(R.id.com1);
            TextView bill7 = (TextView) findViewById(R.id.com2);

            bill5.setText(com0);
            bill6.setText(com1);
            bill7.setText(com2);

            TextView end = (TextView) findViewById(R.id.enddate);
            end.setText("End of term:" + " " + actualEndDates[0]);

            TextView name = (TextView) findViewById(R.id.name);
            name.setText(actualTitle[0] + " " + actualNames[0]);


            ImageView image = (ImageView) findViewById(R.id.image);
            Picasso.with(this)
                    .load("https://theunitedstates.io/images/congress/225x275/" + bioGuideIds[0] + ".jpg").resize(200,200)
                    .into(image);

        }





    }


    public void repersent(View view) {
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        String zip1 = extras.getString("key");
        Intent zip = new Intent(this, Repersent.class);
        zip.putExtra("key", zip1);
        startActivity(zip);
    }



}
