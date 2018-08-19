package com.example.kuzo.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
    }

    public void onClickReview(View view){
        Intent i = new Intent(ReviewActivity.this,VisitedUsersActivity.class);
        startActivity(i);
    }
}
