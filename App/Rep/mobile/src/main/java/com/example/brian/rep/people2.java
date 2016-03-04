package com.example.brian.rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class people2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people2);

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

