package com.example.brian.rep;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Repersent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repersent);

        Intent in = getIntent();
        Bundle extras = in.getExtras();
        String zip1 = extras.getString("key");

        TextView zip = (TextView) findViewById(R.id.zipcode1);
        TextView party = (TextView) findViewById(R.id.party);
        TextView party1 = (TextView) findViewById(R.id.party1);
        TextView party2 = (TextView) findViewById(R.id.party2);
        TextView senator = (TextView) findViewById(R.id.senator);
        TextView senator1 = (TextView) findViewById(R.id.senator1);
        TextView rep = (TextView) findViewById(R.id.rep);
        TextView twitter = (TextView) findViewById(R.id.twitter);
        TextView twitter1 = (TextView) findViewById(R.id.twitter1);
        TextView twitter2 = (TextView) findViewById(R.id.twitter2);
        TextView website = (TextView) findViewById(R.id.website);
        TextView website1 = (TextView) findViewById(R.id.website1);
        TextView website2 = (TextView) findViewById(R.id.website2);
        TextView email = (TextView) findViewById(R.id.email);
        TextView email1 = (TextView) findViewById(R.id.email1);
        TextView email2 = (TextView) findViewById(R.id.email2);
        ImageView pic = (ImageView) findViewById(R.id.imagesenator);
        ImageView pic1 = (ImageView) findViewById(R.id.imagesenator1);
        ImageView pic2 = (ImageView) findViewById(R.id.imagerep);


        if (zip1.equals("94704")) {

            zip.setText("94704, California");
            party.setText("democrat party");
            party1.setText("democrat party");
            party2.setText("democra partyt");
            senator.setText("Senator Dianne Feinstein");
            senator1.setText("Senator Barbara Boxer");
            rep.setText("Repersentative Barbara lee");
            twitter.setText("I’ll be on @NewsHour tonight to discuss the Supreme Court vacancy. Look up your local @PBS station");
            twitter1.setText("Republicans want an elected president to make this appointment. Good news: we have a TWICE-elected @POTUS ");
            twitter2.setText("Proud to work w/ Ahmadiyya Muslim community to promote tolerance and fight Islamic extremism ");
            website.setText("www.feinstein.senate.gov/public/");
            website1.setText("www.boxer.senate.gov/public/");
            website2.setText("lee.house.gov/");
            email.setText("dianne.feinstein@gmail.com");
            email1.setText("barbara.boxer@gmail.com");
            email2.setText("barbara.lee@gmail.com");

            Drawable myDrawable = getResources().getDrawable(R.drawable.dianne);
            pic.setImageDrawable(myDrawable);
            Drawable myDrawable1 = getResources().getDrawable(R.drawable.barbara);
            pic1.setImageDrawable(myDrawable1);
            Drawable myDrawable2 = getResources().getDrawable(R.drawable.lee);
            pic2.setImageDrawable(myDrawable2);
            String zip2 = zip1;
        } else if (zip1.equals("95070")) {

            zip.setText("95070, California");
            party.setText("democrat party");
            party1.setText("democrat party");
            party2.setText("democra party");
            senator.setText("Senator Dianne Feinstein");
            senator1.setText("Senator Barbara Boxer");
            rep.setText("Repersentative Michael Honda");
            twitter.setText("I’ll be on @NewsHour tonight to discuss the Supreme Court vacancy. Look up your local @PBS station");
            twitter1.setText("Republicans want an elected president to make this appointment. Good news: we have a TWICE-elected @POTUS ");
            twitter2.setText("Wishing Michael Witherell a great first day as the new director of Lawrence Berkeley #NationalLab!");
            website.setText("www.feinstein.senate.gov/public/");
            website1.setText("www.boxer.senate.gov/public/");
            website2.setText("honda.house.gov/");
            email.setText("dianne.feinstein@gmail.com");
            email1.setText("barbara.boxer@gmail.com");
            email2.setText("michael.honda@gmail.com");

            Drawable myDrawable = getResources().getDrawable(R.drawable.dianne);
            pic.setImageDrawable(myDrawable);
            Drawable myDrawable1 = getResources().getDrawable(R.drawable.barbara);
            pic1.setImageDrawable(myDrawable1);
            Drawable myDrawable2 = getResources().getDrawable(R.drawable.honda);
            pic2.setImageDrawable(myDrawable2);
            String zip2 = zip1;
        } else {
            zip.setText("94401, California");
            party.setText("democrat party");
            party1.setText("democrat party");
            party2.setText("democra party");
            senator.setText("Senator Dianne Feinstein");
            senator1.setText("Senator Barbara Boxer");
            rep.setText("Repersentative Jackie Speir");
            twitter.setText("I’ll be on @NewsHour tonight to discuss the Supreme Court vacancy. Look up your local @PBS station");
            twitter1.setText("Republicans want an elected president to make this appointment. Good news: we have a TWICE-elected @POTUS ");
            twitter2.setText("Donald Trump Sucks, he needs to get a life and move on");
            website.setText("www.feinstein.senate.gov/public/");
            website1.setText("www.boxer.senate.gov/public/");
            website2.setText("speir.house.gov/");
            email.setText("dianne.feinstein@gmail.com");
            email1.setText("barbara.boxer@gmail.com");
            email2.setText("Jackie.Speir@gmail.com");

            Drawable myDrawable = getResources().getDrawable(R.drawable.dianne);
            pic.setImageDrawable(myDrawable);
            Drawable myDrawable1 = getResources().getDrawable(R.drawable.barbara);
            pic1.setImageDrawable(myDrawable1);
            Drawable myDrawable2 = getResources().getDrawable(R.drawable.jackie);
            pic2.setImageDrawable(myDrawable2);
        }
    }


    public void moreinfo(View view) {
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        String zip1 = extras.getString("key");

        Intent people = new Intent(this, people.class);
        people.putExtra("key", zip1 );
        startActivity(people);
    }

    public void moreinfo1(View view) {
        Intent people1 = new Intent(this, people2.class);
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        String zip1 = extras.getString("key");
        people1.putExtra("key", zip1);
        startActivity(people1);
    }

    public void moreinfo2(View view) {
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        String zip1 = extras.getString("key");
        Intent people2 = new Intent(this, people3.class);
        people2.putExtra("key", zip1);
        startActivity(people2);
    }

    public void mainactivity(View view) {
        Intent zip = new Intent(this, MainActivity.class);
        startActivity(zip);
    }




}
