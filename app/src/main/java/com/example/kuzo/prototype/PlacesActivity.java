package com.example.kuzo.prototype;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PlacesActivity extends AppCompatActivity {


    ListView lv ;
    Context context;
    MyAdapter adapter;
    //static String desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        context = this;
       //get values from previous activity

        lv = (ListView)findViewById(R.id.lvPlaces);


       adapter = new MyAdapter(PlacesActivity.this,generateData());
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {

                TextView tvLabel=v.findViewById(R.id.label);
                final String place=tvLabel.getText().toString();

                Intent i = new Intent(PlacesActivity.this,DescriptionActivity.class);

                //TODO:Pass the same bundle around from mainactivity
                Intent last = getIntent();
                Bundle b = last.getExtras();

                i.putExtras(b);
                startActivity(i);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText(PlacesActivity.this, response, Toast.LENGTH_LONG).show();
                            JSONObject jsonResponse = new JSONObject(response);
                            String success = jsonResponse.getString("success");
                            //Toast.makeText(PlacesActivity.this, success, Toast.LENGTH_LONG).show();

                            if (success=="true") {
                                String desc =jsonResponse.getString("desc");
                                Intent intent = new Intent(PlacesActivity.this, DescriptionActivity.class);


                                Intent last = getIntent();
                                Bundle b = last.getExtras();

                                b.putString("title",place);


                                b.putString("desc",desc);
                                intent.putExtras(b);
                                PlacesActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(PlacesActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }                    }

                };

                DescriptionRequest descriptionRequest = new DescriptionRequest(place, responseListener);
                RequestQueue queue = Volley.newRequestQueue(PlacesActivity.this);
                queue.add(descriptionRequest);


            }
        });

    }


    private ArrayList<Item> generateData() {

        final ArrayList<Item> placesArray = new ArrayList<>();

        Intent last = getIntent();
        Bundle b = last.getExtras();
        int lid = b.getInt("lid");
        int tag = b.getInt("tag");


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                    JSONArray jsonResponse = new JSONArray(response);
                    int length = jsonResponse.length();

                    //Toast.makeText(PlacesActivity.this, success, Toast.LENGTH_LONG).show();

                    if (length > 0) {
                        for (int i = 0; i < length; i++) {
                            JSONObject temp = jsonResponse.getJSONObject(i);
                            String title = temp.getString("title");
                            Item buf = new Item(title);
                            placesArray.add(buf);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PlacesActivity.this);
                    builder.setMessage("No Locations Found")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //Toast.makeText(PlacesActivity.this,""+lid+tag, Toast.LENGTH_SHORT).show();
        PlaceRequest placeRequest = new PlaceRequest(lid, tag, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PlacesActivity.this);
        queue.add(placeRequest);

        return placesArray;
    }



}
