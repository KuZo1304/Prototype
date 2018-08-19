package com.example.kuzo.prototype;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class VisitedUsersActivity extends AppCompatActivity {


    ListView lv ;
    Context context;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited_users);
        lv = (ListView)findViewById(R.id.lvUser);
        //populateUsersList();
        context = this;

        adapter = new MyAdapter(VisitedUsersActivity.this,generateData());
        lv.setAdapter(adapter);

        //reactOnClickListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                // when user clicks on ListView Item , onItemClick is called
                // with position and View of the item which is clicked
                // we can use the position parameter to get index of clicked item
                TextView tvLabel = v.findViewById(R.id.label);
                String users = tvLabel.getText().toString();


//                AlertDialog dialog = new AlertDialog.Builder(context).create();
//                dialog.setTitle("SMS From : "+b.getString("username"));
//                dialog.setIcon(android.R.drawable.ic_dialog_info);
//                dialog.setMessage(b.getString("password"));
//                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which)
//                            {
//
//                                dialog.dismiss();
//                                return;
//                            }
//                        });
//                dialog.show();

                Intent last = getIntent();
                Bundle b = last.getExtras();
                b.putString("users",users);


            }
        });
    }

//    private void populateUsersList() {
//        String[] users = {"kunal","gautam","sohail"};
//        ArrayAdapter<String> useradap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
//        lv.setAdapter(useradap);
//    }

    private ArrayList<Item> generateData() {
        final ArrayList<Item> usersArray = new ArrayList<>();

        //TODO:access database
//        usersArray.add(new Item("Item 1"));
//        usersArray.add(new Item("Item 2"));
//        usersArray.add(new Item("Item 3"));

        //return items;

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Toast.makeText(VisitedUsersActivity.this, response, Toast.LENGTH_LONG).show();
                    JSONArray jsonResponse = new JSONArray(response);
                    int length = jsonResponse.length();

                    //Toast.makeText(VisitedUsersActivity.this,"hello"+length, Toast.LENGTH_LONG).show();

                    if (length > 0) {
                        for (int i = 0; i < length; i++) {
                            JSONObject temp = jsonResponse.getJSONObject(i);
                            String users = temp.getString("users");
                            Item buf = new Item(users);
                            usersArray.add(buf);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(VisitedUsersActivity.this);
                        builder.setMessage("No Users Found")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Intent last = getIntent();
        Bundle b = last.getExtras();
        String title = b.getString("title");
        //Toast.makeText(VisitedUsersActivity.this,title, Toast.LENGTH_SHORT).show();
        VisitedUsersRequest visitedUsersRequest = new VisitedUsersRequest(title, responseListener);
        RequestQueue queue = Volley.newRequestQueue(VisitedUsersActivity.this);
        queue.add(visitedUsersRequest);

        return usersArray;
    }



}
