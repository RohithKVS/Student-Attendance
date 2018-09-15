package com.example.rohit.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Section extends AppCompatActivity {
    TextView year;
    Button csea,cseb,csec,csed;
    int id;
    String dbid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        year=(TextView) findViewById(R.id.year);
        String yr=getIntent().getStringExtra("name");
        year.setText(yr);
        id=getIntent().getExtras().getInt("id");
        csea=(Button) findViewById(R.id.csea);
        cseb=(Button) findViewById(R.id.cseb);
        csec=(Button) findViewById(R.id.csec);
        csed=(Button) findViewById(R.id.csed);

    }
    public void fetch(View v)
    {
        switch (v.getId())
        {
            case R.id.csea:
                if(id==1)
                    dbid="CSE1A";
                else if(id==2)
                    dbid="CSE2A";
                else if(id==3)
                    dbid="CSE3A";
                else if(id==4)
                    dbid="CSE4A";
                break;
            case R.id.cseb:
                if(id==1)
                    dbid="CSE1B";
                else if(id==2)
                    dbid="CSE2B";
                else if(id==3)
                    dbid="CSE3B";
                else if(id==4)
                    dbid="CSE4B";
                break;
            case R.id.csec:
                if(id==1)
                    dbid="CSE1C";
                else if(id==2)
                    dbid="CSE2C";
                else if(id==3)
                    dbid="CSE3C";
                else if(id==4)
                    dbid="CSE4C";
                break;
            case R.id.csed:
                if(id==1)
                    dbid="CSE1D";
                else if(id==2)
                    dbid="CSE2D";
                else if(id==3)
                    dbid="CSE3D";
                else if(id==4)
                    dbid="CSE4D";
                break;
            default:
                Toast.makeText(this,"CSE",Toast.LENGTH_SHORT).show();

        }
        if(dbid.equals("CSE4B"))
        {
            Intent i=new Intent(this,Subject.class);
            i.putExtra("dbid",dbid);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"This page is yet to be designed",Toast.LENGTH_LONG).show();
        }
    }
}
