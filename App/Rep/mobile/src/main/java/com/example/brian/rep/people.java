package com.example.brian.rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class people extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);




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
