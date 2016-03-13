package com.example.brian.rep;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

public class    PhoneListenerService extends WearableListenerService {

    //   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
    private static final String senator1 = "/Dianne";
    private static final String senator2 = "/94401";


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.i("THE EAGEL HAS LANDED", "LAWL");
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase( senator1 ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, people.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("LOCATION", "94704");
            Log.d("T", "about to start watch MainActivity with Dianne ");
            startActivity(intent);
        } else if ( messageEvent.getPath().equalsIgnoreCase( senator2 ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("LOCATION", "94401");
            Log.d("T", "about to start watch MainActivity with MAINNN BITCHEZ");
            startActivity(intent);

        } else {
            Log.i("THE EAGEL HAS LANDED", "TRICK");

        }

    }
}
