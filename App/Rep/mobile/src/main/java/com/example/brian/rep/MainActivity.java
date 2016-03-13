package com.example.brian.rep;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "UnVCZWywm0gql2jrz7EePLXeA";
    private static final String TWITTER_SECRET = "xOeNRWesXKkQnnakbekOsfKppTNJsqtp9RhXkE8I4tpbiiji7y";


    private Button button1;
    private Button button2;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String mLatitudeText;
    private String mLongitudeText;
    private String url;
    private String url1;
    private String url2;
    private String county;
    public boolean flip;
    private String[] actualNames;
    private String[] actualTitle;
    private String[] actualParties;
    private String[] actualEmails;
    private String[] actualWebsites;
    private String[] actualEndDates;
    private String[] actualTwitters;
    private String[] bioGuideIds;
    private String[] billss;
    private String[] bio;
    private String bills0;
    private String bills1;
    private String bills2;
    private String bills3;
    private StringBuilder bill0;
    private StringBuilder bill1;
    private StringBuilder bill2;
    private StringBuilder bill3;
    private int counter = 0;
    private StringBuilder watch;
    private String towatch;
    private String[] build;
    private String coms0;
    private String[] commss;
    private String[] countys;
    private StringBuilder com0;
    private String here = "94704";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


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

    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText = String.valueOf(mLastLocation.getLatitude());
            mLongitudeText = String.valueOf(mLastLocation.getLongitude());
        }

    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}





    public void zipcode(View view) {
        counter +=1;
        Intent intentZip = new Intent(this, Repersent.class);
        EditText zipText = (EditText) findViewById(R.id.zipcode);

//        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//        EditText watchtext = (EditText) findViewById(R.id.zipcode);

        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);



        String zip = zipText.getText().toString();
        getCountybyZip(zip);
        getCongressInfoByZip(zip);
        if (bioGuideIds != null){
            getbills();
            getcommittess();
            intentZip.putExtra("bill0", bills0);
            intentZip.putExtra("com0", coms0);
        }





        intentZip.putExtra("key", zip);
        intentZip.putExtra("name", actualNames);
        intentZip.putExtra("email", actualEmails);
        intentZip.putExtra("website", actualWebsites);
        intentZip.putExtra("enddate", actualEndDates);
        intentZip.putExtra("twitter", actualTwitters);
        intentZip.putExtra("id", bioGuideIds);
        intentZip.putExtra("party", actualParties);
        intentZip.putExtra("title", actualTitle);
        intentZip.putExtra("bill0", billss);
        intentZip.putExtra("com0", commss);




        sendIntent.putExtra("from", "zip");
        sendIntent.putExtra("zip", "94704");


//        sendIntent.putExtra("key", zip);
        if ( counter == 4) {
            counter = 0;

            if (actualNames!= null){
                build = new String[actualNames.length];
                StringBuilder watch = new StringBuilder();
                for (int i = 0; i < build.length; i++) {
                    watch.append(actualNames[i] + ":");
                    watch.append(actualParties[i] + ":");
                    watch.append(actualTitle[i] + ";");
                }
                if (countys != null){
                    watch.append(countys[0] + ":");
                    watch.append(countys[1] + ";");
                }
                String towatch = watch.toString();
                sendIntent.putExtra("names", towatch);
                startActivity(intentZip);
                startService(sendIntent);
                Log.d("here", "" + towatch);


            }


        }
//        startService(sendIntent);
        Log.i("THE EAGEL HAS LANDED1", "I THINK");
    }



    public void current(View view) throws ExecutionException, InterruptedException {
        counter +=1;
        Intent intentZip = new Intent(this, Repersent.class);
        EditText zipText = (EditText) findViewById(R.id.zipcode);

//        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//        EditText watchtext = (EditText) findViewById(R.id.zipcode);

        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);



        String zip = zipText.getText().toString();
        getCountybyZip(here);
        getCongressInfoByZip(here);
        if (bioGuideIds != null){
            getbills();
            getcommittess();
            intentZip.putExtra("bill0", bills0);
            intentZip.putExtra("com0", coms0);
        }





        intentZip.putExtra("key", zip);
        intentZip.putExtra("name", actualNames);
        intentZip.putExtra("email", actualEmails);
        intentZip.putExtra("website", actualWebsites);
        intentZip.putExtra("enddate", actualEndDates);
        intentZip.putExtra("twitter", actualTwitters);
        intentZip.putExtra("id", bioGuideIds);
        intentZip.putExtra("party", actualParties);
        intentZip.putExtra("title", actualTitle);
        intentZip.putExtra("bill0", billss);
        intentZip.putExtra("com0", commss);




        sendIntent.putExtra("from", "zip");
        sendIntent.putExtra("zip", "94704");


//        sendIntent.putExtra("key", zip);
        if ( counter == 4) {
            counter = 0;

            if (actualNames!= null){
                build = new String[actualNames.length];
                StringBuilder watch = new StringBuilder();
                for (int i = 0; i < build.length; i++) {
                    watch.append(actualNames[i] + ":");
                    watch.append(actualParties[i] + ":");
                    watch.append(actualTitle[i] + ";");
                }
                if (countys != null){
                    watch.append(countys[0] + ":");
                    watch.append(countys[1] + ";");
                }
                String towatch = watch.toString();
                sendIntent.putExtra("names", towatch);
                startActivity(intentZip);
                startService(sendIntent);
                Log.d("here", "" + towatch);


            }


        }

    }

    String textView = null;

    public void getCongressInfoByZip(String zipcode) {
        flip = false;
        String apikey = "5998102ea0f44979bdb72e8fcf154815";
        String url2 = "https://congress.api.sunlightfoundation.com/legislators/locate?zip=" + zipcode + "&apikey=" + apikey;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask3().execute(url2);
        } else {
            textView = "No network connection available.";
        }
    }

    public void getCongressInfoByCoord(String mLat, String mLon) {
        String apikey = "5998102ea0f44979bdb72e8fcf154815";
        String url = "https://congress.api.sunlightfoundation.com/legislators/locate?latitude=" + mLat + "&longitude=" + mLon + "&apikey=" + apikey;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(url);
        } else {
            textView = "No network connection available.";
        }
    }

    public void getCountybyZip(String zipCode) {
        flip = true;
        Log.d("hi", "zip");
        String apikey = "AIzaSyBDc5Nf9lJaH-T3PfbZOkYoBTOIg3S0oL0";
        url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + zipCode + "&region=us&key=" + apikey;
        Log.d("url", ""+url);


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask2().execute(url);
        } else {
            textView = "No network connection available.";
        }

        Log.d("textView", "" + textView);



    }

    public void getbills() {
        bio = new String[bioGuideIds.length];
        for (int i = 0; i < bio.length; i++) {
            if (i == 0){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/bills?sponsor_id=" + bioGuideIds[i] + "&apikey=" + apikey;
                Log.d("sunlight", "" + url1);
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagebill0().execute(url1);
                } else {
                    textView = "No network connection available.";
                }

            }
            if (i == 1){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/bills?sponsor_id=" + bioGuideIds[i] + "&apikey=" + apikey;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagebill1().execute(url1);
                } else {
                    textView = "No network connection available.";
                }

                Log.d("bills", "" + textView);
            }
            if (i == 2){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/bills?sponsor_id=" + bioGuideIds[i] + "&apikey=" + apikey;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagebill2().execute(url1);
                } else {
                    textView = "No network connection available.";
                }

                Log.d("bills", "" + textView);
            }
            if (i == 3){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/bills?sponsor_id=" + bioGuideIds[i] + "&apikey=" + apikey;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagebill3().execute(url1);
                } else {
                    textView = "No network connection available.";
                }

                Log.d("bills", "" + textView);
            }
            if (i == 4){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/bills?sponsor_id=" + bioGuideIds[i] + "&apikey=" + apikey;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagebill0().execute(url);
                } else {
                    textView = "No network connection available.";
                }

                Log.d("bills", "" + textView);
            }



        }

    }

    public void getcommittess() {
        bio = new String[bioGuideIds.length];
        for (int i = 0; i < bio.length; i++) {
            if (i == 0){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/committees?member_ids=" + bioGuideIds[i] + "&apikey=" + apikey;
                Log.d("sunlight", "" + url1);
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagecom().execute(url1);
                } else {
                    textView = "No network connection available.";
                }

            }
            if (i == 1){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/committees?member_ids=" + bioGuideIds[i] + "&apikey=" + apikey;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagecom().execute(url1);
                } else {
                    textView = "No network connection available.";
                }

                Log.d("bills", "" + textView);
            }
            if (i == 2){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/committees?member_ids=" + bioGuideIds[i] + "&apikey=" + apikey;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagecom().execute(url1);
                } else {
                    textView = "No network connection available.";
                }

                Log.d("bills", "" + textView);
            }
            if (i == 3){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/committees?member_ids=" + bioGuideIds[i] + "&apikey=" + apikey;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagecom().execute(url1);
                } else {
                    textView = "No network connection available.";
                }

                Log.d("bills", "" + textView);
            }
            if (i == 4){
                String apikey = "5998102ea0f44979bdb72e8fcf154815";
                String url1 = "https://congress.api.sunlightfoundation.com/committees?member_ids=" + bioGuideIds[i] + "&apikey=" + apikey;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpagebill0().execute(url);
                } else {
                    textView = "No network connection available.";
                }

                Log.d("bills", "" + textView);
            }



        }

    }







    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            textView = result;
            JSONObject obj = null;
            try {
                JSONObject myJObject = new JSONObject(textView);
                String county = myJObject.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(2).getString("long_name");
                Log.d("textView", "" + county);
            } catch (JSONException e) {
                // Appropriate error handling code
            }

        }
    }

    private class DownloadWebpageTask2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            textView = result;
            Log.i("GET VIEW", "PRINT IT" + textView);
            JSONObject obj = null;
            try {
                Log.i("SUCCESS YOUE IN O POSET", "POSET LIKE COSET");
                countys = new String[2];
                JSONObject myJObject = new JSONObject(textView);
                Log.i("BRIAN IS AN IDIOOT", "AM I RITE" + myJObject);
                JSONArray county = myJObject.getJSONArray("results").getJSONObject(0).getJSONArray("address_components");
                Log.i("WHAT IS GOING ON ", county.toString());
                for (int i = 0;i < county.length() ;  i++){
                    JSONArray hi = county.getJSONObject(i).getJSONArray("types");
                    Log.i("WHAT IS HI", hi.toString());
                    if(hi.get(0).toString().equals("administrative_area_level_2")){
                        Log.i("HIII" ,county.getJSONObject(i).get("long_name").toString());
                        countys[0] = county.getJSONObject(i).get("long_name").toString();

                    }
                    if(hi.get(0).toString().equals("administrative_area_level_1")){
                        Log.i("HIII" ,county.getJSONObject(i).get("long_name").toString());
                        countys[1] = county.getJSONObject(i).get("long_name").toString();

                    }
                }
                Log.d("textView", "" + county);


            } catch (JSONException e) {
                // Appropriate error handling code
            }

        }
    }

    private class DownloadWebpageTask3 extends AsyncTask<String, Void, String> {
        @Override

        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            textView = result;
            try {
                JSONObject object = (JSONObject) new JSONTokener(textView).nextValue();
                JSONArray array = object.getJSONArray("results"); //1 array, repNum objects
                actualNames = new String[array.length()];
                actualParties = new String[array.length()];
                actualEmails = new String[array.length()];
                actualWebsites = new String[array.length()];
                actualEndDates = new String[array.length()];
                actualTwitters = new String[array.length()];
                actualTitle = new String[array.length()];
                bioGuideIds = new String[array.length()];
                for (int i = 0; i < array.length(); i++) {
                    JSONObject subObject = array.getJSONObject(i);
                    actualNames[i] = subObject.getString("first_name") + " " + subObject.getString("last_name");
                    String party = subObject.getString("party");
                    actualParties[i] = party;
                    if (party.equals("D")) {
                        actualParties[i] = party.concat("emocrat");
                    } else if (party.equals("R")){
                        actualParties[i] = party.concat("epublican");
                    } else if (party.equals("I")) {
                        actualParties[i] = party.concat("ndependent");
                    }
                    actualEmails[i] = subObject.getString("oc_email");
                    Log.d("textView", "" + actualEmails[i]);
                    actualWebsites[i] = subObject.getString("website");
                    actualEndDates[i] = subObject.getString("term_end");
                    actualTwitters[i] = subObject.getString("twitter_id");
                    actualTwitters[i] = subObject.getString("twitter_id");
                    actualTitle[i] = subObject.getString("title");
                    bioGuideIds[i] = subObject.getString("bioguide_id");
                    Log.d("textView", "" + bioGuideIds[i]);
                }


            } catch (JSONException e) {

            }

        }
    }
    private class DownloadWebpagecom extends AsyncTask<String, Void, String> {
        @Override

        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            textView = result;
            try {
                commss = new String[10];
                JSONObject details = new JSONObject(textView);
                StringBuilder com0 = new StringBuilder();
                JSONArray results = details.getJSONArray("results");
                for (int i = 0; i < 10; i++) {
                    JSONObject explrObject = results.getJSONObject(i);
                    String name = explrObject.getString("name");
                    Log.d("name", "" + name);
                    com0.append(name);
                    commss[i] = explrObject.getString("name");
                }
                coms0 = com0.toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadWebpagebill0 extends AsyncTask<String, Void, String> {
        @Override

        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            textView = result;
            try {
                billss = new String[6];
                JSONObject details = new JSONObject(textView);
                StringBuilder bill0 = new StringBuilder();
                JSONArray results = details.getJSONArray("results");
                for (int i = 0; i < 6; i++) {
                    JSONObject explrObject = results.getJSONObject(i);
                    String name = explrObject.getString("short_title");
                    String date = explrObject.getString("introduced_on");
                    if (name!="null") {
                        bill0.append(date+": ");
                        bill0.append(name);
                        Log.d("lol", "" + name);
                        bill0.append("\n\n");
                        billss[i] = explrObject.getString("short_title") + " " + explrObject.getString("introduced_on");

                    }

                }
                bills0 = bill0.toString();

            } catch (JSONException e) {

            }

        }
    }
    private class DownloadWebpagebill1 extends AsyncTask<String, Void, String> {
        @Override

        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            textView = result;
            try {
                JSONObject details = new JSONObject(textView);
                StringBuilder bill1 = new StringBuilder();
                JSONArray results = details.getJSONArray("results");
                for (int i = 0; i < 6; i++) {
                    JSONObject explrObject = results.getJSONObject(i);
                    String name = explrObject.getString("short_title");
                    String date = explrObject.getString("introduced_on");
                    if (name!="null") {
                        bill1.append(date+": ");
                        bill1.append(name);
                        bill1.append("\n\n");
                    }
                }
            } catch (JSONException e) {

            }

        }
    }

    private class DownloadWebpagebill2 extends AsyncTask<String, Void, String> {
        @Override

        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            textView = result;
            try {
                JSONObject details = new JSONObject(textView);
                StringBuilder bill2 = new StringBuilder();
                JSONArray results = details.getJSONArray("results");
                for (int i = 0; i < 6; i++) {
                    JSONObject explrObject = results.getJSONObject(i);
                    String name = explrObject.getString("short_title");
                    String date = explrObject.getString("introduced_on");
                    if (name!="null") {
                        bill2.append(date+": ");
                        bill2.append(name);
                        bill2.append("\n\n");
                    }
                }
            } catch (JSONException e) {

            }

        }
    }

    private class DownloadWebpagebill3 extends AsyncTask<String, Void, String> {
        @Override

        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            textView = result;
            try {
                JSONObject details = new JSONObject(textView);
                StringBuilder bill3 = new StringBuilder();
                JSONArray results = details.getJSONArray("results");
                for (int i = 0; i < 6; i++) {
                    JSONObject explrObject = results.getJSONObject(i);
                    String name = explrObject.getString("short_title");
                    String date = explrObject.getString("introduced_on");
                    if (name!="null") {
                        bill3.append(date+": ");
                        bill3.append(name);
                        bill3.append("\n\n");
                    }
                }
            } catch (JSONException e) {

            }

        }
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();

            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        return new String(result);
    }


//    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
//
//
//        private Exception exception;
//        protected void onPreExecute() {
//        }
//
//        protected String doInBackground(Void... urls) {
//            Log.d("hi", "hi");
//            DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
//            HttpPost httppost = new HttpPost(url);
//// Depends on your web service
//            httppost.setHeader("Content-type", "application/json");
//
//            InputStream inputStream = null;
//            String result = null;
//            try {
//                Log.d("hi", "shit");
//                HttpResponse response = httpclient.execute(httppost);
//                HttpEntity entity = response.getEntity();
//
//                inputStream = entity.getContent();
//                // json is UTF-8 by default
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
//                StringBuilder sb = new StringBuilder();
//
//                String line = null;
//                while ((line = reader.readLine()) != null)
//                {
//                    Log.d("while", "while");
//                    sb.append(line + "\n");
//                }
//                reader.close();
//                result = sb.toString();
//                Log.d("hi", "fuck");
//                return result;
//            } catch (Exception e) {
//                Log.d("hi", "dam");
//                return  null;
//            }
//            finally {
//                Log.d("hi", "fuckshit");
//                try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
//            }
//        }
//
//        protected void onPostExecute(String response) {
//            if(response == null) {
//                response = "THERE WAS AN ERROR";
//                Log.d("hi", "damfuck");
//            }
//            try {
//                JSONObject myJObject = new JSONObject(response);
//                Log.d("county", "" + myJObject.getString("administrative_area_level_2"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }



}

