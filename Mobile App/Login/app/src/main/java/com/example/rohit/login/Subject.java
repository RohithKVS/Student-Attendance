package com.example.rohit.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Subject extends AppCompatActivity {
    String dbid,subj,p1,p2,date;
    EditText sub,from,to;
    int i1,i2;
    get_students g;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        dbid=getIntent().getStringExtra("dbid").toLowerCase();
        Toast.makeText(this,"Enter the subject name in short form.\n    Eg: Software Engineering as SE",Toast.LENGTH_LONG).show();
        sub=(EditText) findViewById(R.id.sub);
        from=(EditText) findViewById(R.id.from);
        to=(EditText) findViewById(R.id.to);
        date=new SimpleDateFormat("ddMMMyyyy", Locale.getDefault()).format(new Date()).toLowerCase();
        g=new get_students(this);
    }
    public void getdata(View v)
    {
        subj = sub.getText().toString().toUpperCase();
        p1 = from.getText().toString();
        p2 = to.getText().toString();

        if (subj.equals(""))
            Toast.makeText(this, "Enter subject", Toast.LENGTH_SHORT).show();
        if(p1.equals(""))
            Toast.makeText(this, "Enter Period 1", Toast.LENGTH_SHORT).show();
        if(p2.equals(""))
            Toast.makeText(this, "Enter Period 2", Toast.LENGTH_SHORT).show();
        else
        {
            i1 = Integer.parseInt(p1);
            i2 = Integer.parseInt(p2);
            if ((i1 > 0 && i1 < 8) && (i2 > 0 && i2 < 8))
            {
                if (i1 <= i2)
                {
                    int i3 = i2 - i1;
                    if (i3 == 0 || i3 == 1 || i3 == 2)
                    {
                       // Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
                        g.execute(dbid,subj,date,p1,p2);
                    }
                    else
                        Toast.makeText(this, "Maximum of 3 periods allowed", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "Invalid entry", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Enter periods from 1 to 7 only", Toast.LENGTH_SHORT).show();
        }
    }
}
