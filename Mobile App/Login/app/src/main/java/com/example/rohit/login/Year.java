package com.example.rohit.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Year extends AppCompatActivity {
TextView name;
    Button first,second,third,fourth;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);
        name=(TextView) findViewById(R.id.textView6);
        String s=getIntent().getStringExtra("name");
        name.setText("Welcome "+s);
        first=(Button) findViewById(R.id.firstyr);
        second=(Button) findViewById(R.id.secondyr);
        third=(Button) findViewById(R.id.thirdyr);
        fourth=(Button) findViewById(R.id.fourthyr);
    }
    public void select(View v)
    {
        id=0;
        Intent i=new Intent(this,Section.class);
        if(v.getId()==R.id.firstyr)
        {
            id=1;
            i.putExtra("name",first.getText().toString());
        }
        else if(v.getId()==R.id.secondyr)
        {
            id=2;
            i.putExtra("name",second.getText().toString());
        }
        else if(v.getId()==R.id.thirdyr)
        {
            id=3;
            i.putExtra("name",third.getText().toString());
        }
        else if(v.getId()==R.id.fourthyr)
        {
            id=4;
            i.putExtra("name",fourth.getText().toString());
        }
        i.putExtra("id",id);
        startActivity(i);
    }
}
