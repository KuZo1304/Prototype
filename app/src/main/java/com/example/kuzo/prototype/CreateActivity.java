package com.example.kuzo.prototype;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateActivity extends Activity {
    Spinner spinner;
    RadioButton tagButton;
    RadioGroup tag;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        spinner = findViewById(R.id.spPlace);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this,R.array.Places, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        submit = findViewById(R.id.btSubmit);
        tag = findViewById(R.id.rgTags);
        tagButton = findViewById(R.id.rbMalls);
        tagButton.setChecked(true);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateActivity.this,PlacesActivity.class);
                int lid = spinner.getSelectedItemPosition();
                int selectedId=tag.getCheckedRadioButtonId();
                int tagint=1;
                //Toast.makeText(CreateActivity.this,tagButton.getText(), Toast.LENGTH_SHORT).show();
                switch (selectedId) {
                    case R.id.rbFood:
                        tagint = 3;
                        break;
                    case R.id.rbMalls:
                        tagint = 1;
                        break;
                    case R.id.rbMovies:
                        tagint = 2;
                        break;
                }

                Intent last = getIntent();
                Bundle b = last.getExtras();

                b.putInt("lid",lid);
                b.putInt("tag",tagint);
                //b.putInt("tagId",tagid);
                i.putExtras(b);


                //Intent main = getIntent();


//        Toast.makeText(CreateActivity.this, ""+contact+" age "+age, Toast.LENGTH_LONG).show();
//        b.putString("place",place);
//        i.putExtras(b);
                startActivity(i);

            }
        });


    }

//    public void onClickSubmit(View view){
//
//        Intent i = new Intent(CreateActivity.this,PlacesActivity.class);
//        spinner = findViewById(R.id.spPlace);
//        int place = spinner.getSelectedItemPosition();
//        tag = findViewById(R.id.rgTags);
//        tag.clearCheck();
//        int selectedId=tag.getCheckedRadioButtonId();
//        tagButton=findViewById(selectedId);
//        Toast.makeText(CreateActivity.this,tagButton.getText(), Toast.LENGTH_SHORT).show();
//
//
//
//
//
//        //TODO: extract value of tag radiogroup
//
//
////        tag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
////
////                    @Override
////                    public void onCheckedChanged(RadioGroup group, int checkedId) {
////                       int tagid;
////
////                        if (checkedId == R.id.rbFood) {
////                            //some code
////                            tagid=1;
////                        } else if (checkedId == R.id.rbMalls) {
////                            //some code
////                            tagid=2;
////                        } else if (checkedId == R.id.rbMovies){
////                            tagid=3;
////                        }
////
////                    }
////
////                });
//
//        Intent last = getIntent();
//        Bundle b = last.getExtras();
//
//        b.putInt("placeId",place);
//        //b.putInt("tagId",tagid);
//        i.putExtras(b);
//
//
//        //Intent main = getIntent();
//
//
////        Toast.makeText(CreateActivity.this, ""+contact+" age "+age, Toast.LENGTH_LONG).show();
////        b.putString("place",place);
////        i.putExtras(b);
//        startActivity(i);
//    }
}
