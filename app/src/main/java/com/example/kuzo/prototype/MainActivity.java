package com.example.kuzo.prototype;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
//                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
//                Bundle b = new Bundle();
//                b.putString("username",username);
//                b.putString("password",password);
//                intent.putExtras(b);
//                MainActivity.this.startActivity(intent);


////                Toast.makeText(MainActivity.this, "OnClick", Toast.LENGTH_LONG).show();
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            JSONObject jsonResponse = new JSONObject(response);
                            String success = jsonResponse.getString("success");
                            //Toast.makeText(MainActivity.this, success, Toast.LENGTH_LONG).show();

                            if (success=="true") {

                                int age = jsonResponse.getInt("age");
                                long contact = Long.parseLong(jsonResponse.getString("contact"));
                                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                                Bundle b = new Bundle();
                                b.putLong("contact", contact);
                                b.putInt("age", age);
                                b.putString("username",username);
                                b.putString("password",password);

                                intent.putExtras(b);

                                MainActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });


    }

//    public void onLoginClick(View view){
//        Toast.makeText(MainActivity.this, "Account Created Successfully!!", Toast.LENGTH_SHORT).show();
//        Intent main = new Intent(MainActivity.this,CreateActivity.class);
//        Bundle b = new Bundle();
//        user = (EditText) findViewById(R.id.etUsername);
//        pass = (EditText) findViewById(R.id.etPassword);
//        String username = user.getText().toString();
//        String password = pass.getText().toString();
//        b.putString("user", username);
//        b.putString("pass", password);
//        main.putExtras(b);
//        startActivity(main);
//    }

    public void onSignupClick(View view){
        Intent in = new Intent(MainActivity.this,SignupActivity.class);
        startActivity(in);
    }
}
