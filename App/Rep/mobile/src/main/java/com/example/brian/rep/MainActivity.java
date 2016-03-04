package com.example.brian.rep;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent in = getIntent();
        Bundle extras = in.getExtras();

        if (extras != null) {

            Intent intentZip = new Intent(this, Repersent.class);
            EditText zipText = (EditText) findViewById(R.id.zipcode);

//        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//        EditText watchtext = (EditText) findViewById(R.id.zipcode);

            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            String zip1 = extras.getString("zip");

            sendIntent.putExtra("from", "currLoc");
            sendIntent.putExtra("zip", "94401");
            startService(sendIntent);

            intentZip.putExtra("key", "94401");

            startActivity(intentZip);


        }




    }

    public void zipcode(View view) {
        Intent intentZip = new Intent(this, Repersent.class);
        EditText zipText = (EditText) findViewById(R.id.zipcode);

//        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//        EditText watchtext = (EditText) findViewById(R.id.zipcode);

        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
        sendIntent.putExtra("from", "currLoc");
        sendIntent.putExtra("zip", "94704");
        startService(sendIntent);

        String zip = zipText.getText().toString();
        intentZip.putExtra("key", zip);
//        sendIntent.putExtra("key", zip);
        startActivity(intentZip);
//        startService(sendIntent);
        Log.i("THE EAGEL HAS LANDED1", "I THINK");
    }



    public void current(View view) {
        Intent intentcurrent = new Intent(this, Repersent.class);
        intentcurrent.putExtra("key", "95070");

        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
        sendIntent.putExtra("from", "currLoc");
        sendIntent.putExtra("zip", "95070");
        startService(sendIntent);
//        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//        sendIntent.putExtra("key", "95070");
        startActivity(intentcurrent);
//        startService(sendIntent);
        Log.i("THE EAGEL HAS LANDED", "I THINK2");
    }


}
