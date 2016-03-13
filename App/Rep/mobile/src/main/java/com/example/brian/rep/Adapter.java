package com.example.brian.rep;

/**
 * Created by Brian on 3/11/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<String> {

    //DECLARATIONS
    String[] names = {};
    String[] pictures = {};
    String[] emails = {};
    String[] websites = {};
    String[] party = {};
    String[] title = {};
    Context c;
    LayoutInflater inflater;
    //    String[] bioguideIds;
    String[] urls;

    public Adapter(Context context, String[] names, String[] picStrings, String[] emails, String[] websites, String[] party, String[] title) {
        super(context, R.layout.model, names);

        this.c = context;
        this.names = names;
        this.pictures = pictures;
        this.emails = emails;
        this.websites = websites;
        this.party = party;
        this.title = title;
        urls = picStrings;
//        Log.d("id array length", String.valueOf(ids.length));
//        bioguideIds = ids;
//        Log.d("gotten here", "AFTER ASSIGNING BIOGUIDEIDS");
//        fillUrls(ids);
//        Log.d("after fillUrls", "HERE");
    }

//    public void fillUrls(String[] ids) {
//        urls = new String[ids.length];
//        Log.d("urls length", String.valueOf(urls.length));
//        for (int i = 0; i < ids.length; i++) {
////                         "https://theunitedstates.io/images/congress/[size]/[bioguide].jpg"
//            String url = "https://theunitedstates.io.images/congress/225x275/" + ids[i] + ".jpg";
//            Log.d("url is", url);
//            urls[i] = url;
//        }
//    }

    //INNER CLASS TO HOLD OUR VIEWS FOR EACH ROW
    public class ViewHolder {

        TextView names;
        TextView emails;
        TextView websites;
        TextView party;
        ImageView image;
        TextView title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.model, null);
        }

        //our viewHolder object
        ViewHolder holder = new ViewHolder();

        //initialize views
        holder.names = (TextView) convertView.findViewById(R.id.senator);
        holder.image = (ImageView) convertView.findViewById(R.id.imagesenator);
        holder.websites = (TextView) convertView.findViewById(R.id.website);
        holder.emails = (TextView) convertView.findViewById(R.id.email);
        holder.party = (TextView) convertView.findViewById(R.id.party);
        holder.title = (TextView) convertView.findViewById(R.id.title);



        //Assign data
        //set the picture based upon the url
//        holder.image.setImageResource(images[position]);
        Picasso.with(c).load(urls[position]).resize(200, 200).into(holder.image);
//        Picasso.with(this.getContext()).load(urls[position]).into(holder.image);
        holder.names.setText(names[position]);
        holder.websites.setText(websites[position]);
        holder.emails.setText(emails[position]);
        holder.party.setText(party[position]);
        holder.title.setText(title[position]);



        return convertView;
    }
}
