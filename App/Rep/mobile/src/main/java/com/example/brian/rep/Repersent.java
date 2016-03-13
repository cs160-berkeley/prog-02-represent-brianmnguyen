
package com.example.brian.rep;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.*;
import io.fabric.sdk.android.Fabric;

import com.twitter.sdk.android.tweetui.TweetView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Repersent extends AppCompatActivity {

    private static final String TWITTER_KEY = "UnVCZWywm0gql2jrz7EePLXeA";
    private static final String TWITTER_SECRET = "xOeNRWesXKkQnnakbekOsfKppTNJsqtp9RhXkE8I4tpbiiji7y";
    private String url;
    private String[] actualNames;
    private String[] actualParties;
    private String[] actualEmails;
    private String[] actualWebsites;
    private String[] actualEndDates;
    private String[] actualTwitters;
    private String[] actualTitle;
    private String[] bioGuideIds;
    private String[] pic;
    private String bill0;
    private String com0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repersent);


        Intent in = getIntent();
        Bundle extras = in.getExtras();
        String zip1 = extras.getString("key");
        String lat = extras.getString("lat");
        String lon = extras.getString("long");
        String[] actualNames = extras.getStringArray("name");
        String[] actualParties = extras.getStringArray("party");
        String[] actualEmails = extras.getStringArray("email");
        String[] actualWebsites = extras.getStringArray("website");
        String[] actualEndDates = extras.getStringArray("enddate");
        String[] actualTwitters = extras.getStringArray("twitter");
        String[] bioGuideIds = extras.getStringArray("id");
        String[] actualTitle = extras.getStringArray("title");
        String[] bill0 = extras.getStringArray("bill0");


        Intent hi = getIntent();
        Bundle extras1 = hi.getExtras();
        actualNames = extras1.getStringArray("name");
        actualParties = extras1.getStringArray("party");
        actualEmails = extras1.getStringArray("email");
        actualWebsites = extras1.getStringArray("website");
        actualEndDates = extras1.getStringArray("enddate");
        actualTwitters = extras1.getStringArray("twitter");
        bioGuideIds = extras1.getStringArray("id");

//        // TODO: Use a more specific parent
//        final ViewGroup parentView = (ViewGroup) getWindow().getDecorView().getRootView();
//        // TODO: Base this Tweet ID on some data from elsewhere in your app
//        long tweetId = 631879971628183552L;
//        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
//            @Override
//            public void success(Result<Tweet> result) {
//                TweetView tweetView = new TweetView(Repersent.this, result.data);
//                parentView.addView(tweetView);
//            }
//            @Override
//            public void failure(TwitterException exception) {
//                Log.d("TwitterKit", "Load Tweet failure", exception);
//            }
//        });

        String apikey = "5998102ea0f44979bdb72e8fcf154815";
//
//        if (lat == null) {
//            getCongressInfoByZip(zip1);
//        } else {
//            getCongressInfoByCoord(lat,lon);
//        }


        if (bioGuideIds != null) {
            piccreate(bioGuideIds);
            ListView listview = (ListView) findViewById(R.id.listView);

            Adapter adapter = new Adapter(this, actualNames, pic, actualEmails, actualWebsites, actualParties, actualTitle);
            listview.setAdapter(adapter);

        }







//        TextView zip = (TextView) findViewById(R.id.zipcode1);
//        TextView party = (TextView) findViewById(R.id.party);
//        TextView party1 = (TextView) findViewById(R.id.party1);
//        TextView party2 = (TextView) findViewById(R.id.party2);
//        TextView senator = (TextView) findViewById(R.id.senator);
//        TextView senator1 = (TextView) findViewById(R.id.senator1);
//        TextView rep = (TextView) findViewById(R.id.rep);
//        TextView twitter = (TextView) findViewById(R.id.twitter);
//        TextView twitter1 = (TextView) findViewById(R.id.twitter1);
//        TextView twitter2 = (TextView) findViewById(R.id.twitter2);
//        TextView website = (TextView) findViewById(R.id.website);
//        TextView website1 = (TextView) findViewById(R.id.website1);
//        TextView website2 = (TextView) findViewById(R.id.website2);
//        TextView email = (TextView) findViewById(R.id.email);
//        TextView email1 = (TextView) findViewById(R.id.email1);
//        TextView email2 = (TextView) findViewById(R.id.email2);
//        ImageView pic = (ImageView) findViewById(R.id.imagesenator);
//        ImageView pic1 = (ImageView) findViewById(R.id.imagesenator1);
//        ImageView pic2 = (ImageView) findViewById(R.id.imagerep);
//
//
//        if (zip1.equals("94704")) {
//
//            zip.setText("94704, California");
//            party.setText("democrat party");
//            party1.setText("democrat party");
//            party2.setText("democra partyt");
//            senator.setText("Senator Dianne Feinstein");
//            senator1.setText("Senator Barbara Boxer");
//            rep.setText("Repersentative Barbara lee");
//            twitter.setText("I’ll be on @NewsHour tonight to discuss the Supreme Court vacancy. Look up your local @PBS station");
//            twitter1.setText("Republicans want an elected president to make this appointment. Good news: we have a TWICE-elected @POTUS ");
//            twitter2.setText("Proud to work w/ Ahmadiyya Muslim community to promote tolerance and fight Islamic extremism ");
//            website.setText("www.feinstein.senate.gov/public/");
//            website1.setText("www.boxer.senate.gov/public/");
//            website2.setText("lee.house.gov/");
//            email.setText("dianne.feinstein@gmail.com");
//            email1.setText("barbara.boxer@gmail.com");
//            email2.setText("barbara.lee@gmail.com");
//
//            Drawable myDrawable = getResources().getDrawable(R.drawable.dianne);
//            pic.setImageDrawable(myDrawable);
//            Drawable myDrawable1 = getResources().getDrawable(R.drawable.barbara);
//            pic1.setImageDrawable(myDrawable1);
//            Drawable myDrawable2 = getResources().getDrawable(R.drawable.lee);
//            pic2.setImageDrawable(myDrawable2);
//            String zip2 = zip1;
//        } else if (zip1.equals("95070")) {
//
//            zip.setText("95070, California");
//            party.setText("democrat party");
//            party1.setText("democrat party");
//            party2.setText("democra party");
//            senator.setText("Senator Dianne Feinstein");
//            senator1.setText("Senator Barbara Boxer");
//            rep.setText("Repersentative Michael Honda");
//            twitter.setText("I’ll be on @NewsHour tonight to discuss the Supreme Court vacancy. Look up your local @PBS station");
//            twitter1.setText("Republicans want an elected president to make this appointment. Good news: we have a TWICE-elected @POTUS ");
//            twitter2.setText("Wishing Michael Witherell a great first day as the new director of Lawrence Berkeley #NationalLab!");
//            website.setText("www.feinstein.senate.gov/public/");
//            website1.setText("www.boxer.senate.gov/public/");
//            website2.setText("honda.house.gov/");
//            email.setText("dianne.feinstein@gmail.com");
//            email1.setText("barbara.boxer@gmail.com");
//            email2.setText("michael.honda@gmail.com");
//
//            Drawable myDrawable = getResources().getDrawable(R.drawable.dianne);
//            pic.setImageDrawable(myDrawable);
//            Drawable myDrawable1 = getResources().getDrawable(R.drawable.barbara);
//            pic1.setImageDrawable(myDrawable1);
//            Drawable myDrawable2 = getResources().getDrawable(R.drawable.honda);
//            pic2.setImageDrawable(myDrawable2);
//            String zip2 = zip1;
//        } else {
//            zip.setText("94401, California");
//            party.setText("democrat party");
//            party1.setText("democrat party");
//            party2.setText("democra party");
//            senator.setText("Senator Dianne Feinstein");
//            senator1.setText("Senator Barbara Boxer");
//            rep.setText("Repersentative Jackie Speir");
//            twitter.setText("I’ll be on @NewsHour tonight to discuss the Supreme Court vacancy. Look up your local @PBS station");
//            twitter1.setText("Republicans want an elected president to make this appointment. Good news: we have a TWICE-elected @POTUS ");
//            twitter2.setText("Donald Trump Sucks, he needs to get a life and move on");
//            website.setText("www.feinstein.senate.gov/public/");
//            website1.setText("www.boxer.senate.gov/public/");
//            website2.setText("speir.house.gov/");
//            email.setText("dianne.feinstein@gmail.com");
//            email1.setText("barbara.boxer@gmail.com");
//            email2.setText("Jackie.Speir@gmail.com");
//
//            Drawable myDrawable = getResources().getDrawable(R.drawable.dianne);
//            pic.setImageDrawable(myDrawable);
//            Drawable myDrawable1 = getResources().getDrawable(R.drawable.barbara);
//            pic1.setImageDrawable(myDrawable1);
//            Drawable myDrawable2 = getResources().getDrawable(R.drawable.jackie);
//            pic2.setImageDrawable(myDrawable2);
//        }
    }

    String textView = null;
    public void getCongressInfoByZip(String zipcode) {
        String apikey = "5998102ea0f44979bdb72e8fcf154815";
        String url = "https://congress.api.sunlightfoundation.com/legislators/locate?zip=" + zipcode + "&apikey=" + apikey;
        Log.d("textView1", "" + url);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(url);
        } else {
            textView = "No network connection available.";
        }
        Log.d("textView1", "" + textView);
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

    protected void piccreate(String[] bioGuideIds) {
        pic = new String[bioGuideIds.length];
        for (int i = 0; i < pic.length; i++) {
            String url = "https://theunitedstates.io/images/congress/225x275/" + bioGuideIds[i] + ".jpg";
            pic[i] = url;
        }

    }


    public void moreinfo(View view) {
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        String zip1 = extras.getString("key");
        String[] bill0 = extras.getStringArray("bill0");
        String[] com0 = extras.getStringArray("com0");
        String[] enddate = extras.getStringArray("enddate");
        String[] id = extras.getStringArray("id");
        String[] name = extras.getStringArray("name");
        String[] title = extras.getStringArray("title");


        Intent people = new Intent(this, people.class);

        people.putExtra("enddate", enddate );
        people.putExtra("name", name );
        people.putExtra("id", id );
        people.putExtra("bill0", bill0);
        people.putExtra("com0", com0);
        people.putExtra("title", title);
        people.putExtra("id", id);
        Log.d("textView4", "" + bill0);

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
            try {
                JSONObject object = (JSONObject) new JSONTokener(textView).nextValue();
                JSONArray array = object.getJSONArray("results"); //1 array, repNum objects
                actualNames = new String[array.length()];
                actualParties = new String[array.length()];
                actualEmails = new String[array.length()];
                actualWebsites = new String[array.length()];
                actualEndDates = new String[array.length()];
                actualTwitters = new String[array.length()];
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
                    bioGuideIds[i] = subObject.getString("bioguide_id");
                    Log.d("textView", "" + bioGuideIds[i]);
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
            Log.d("textView", "" + contentAsString);
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



}

