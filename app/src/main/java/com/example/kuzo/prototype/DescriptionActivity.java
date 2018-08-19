package com.example.kuzo.prototype;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DescriptionActivity extends AppCompatActivity {

    Button users;
    TextView tvTitle;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        users = (Button)findViewById(R.id.btUser);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvDescription  = (TextView)findViewById(R.id.tvDescription);

        Intent last = getIntent();
        Bundle b = last.getExtras();
        final String title = b.getString("title");
        final String desc = b.getString("desc");

        tvDescription.setText(desc);


        //Toast.makeText(DescriptionActivity.this, title, Toast.LENGTH_LONG).show();




//        Response.Listener<String> responseListener = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
//                    JSONObject jsonResponse = new JSONObject(response);
//                    String description = jsonResponse.getString("desc");
//                    String success = jsonResponse.getString("success");
//
//                    Toast.makeText(DescriptionActivity.this, "Hello", Toast.LENGTH_LONG).show();
//
//                    if (success=="true") {
//
//
//                        tvDescription.setText(description);
//
//                    } else {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(DescriptionActivity.this);
//                        builder.setMessage("Login Failed")
//                                .setNegativeButton("Retry", null)
//                                .create()
//                                .show();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        };
//
//        responseListener.notify();
//
//        DescriptionRequest descriptionRequest = new DescriptionRequest(title, responseListener);
//        RequestQueue queue = Volley.newRequestQueue(DescriptionActivity.this);
//        queue.add(descriptionRequest);
//
    }

    public void onClickUsers(View view){
        //Toast.makeText(DescriptionActivity.this,"To users...",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(DescriptionActivity.this,VisitedUsersActivity.class);
        Intent last = getIntent();
        Bundle b = last.getExtras();
        i.putExtras(b);
        startActivity(i);
    }


}
