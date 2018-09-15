package com.example.rohit.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Admin extends AppCompatActivity {
    TextView name;
    Button add_faculty,attdn,logout;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        name=(TextView) findViewById(R.id.welcome_admin);
        String s=getIntent().getStringExtra("name");
        name.setText("Welcome "+s);
        add_faculty=(Button) findViewById(R.id.add_faculty);
        attdn=(Button) findViewById(R.id.attdn);
        logout=(Button) findViewById(R.id.admin_logout);
    }
    public void admin_func(View v)
    {
        if(v.getId()==R.id.add_faculty)
        {
            i=new Intent(this,Addfaculty.class);
            startActivity(i);
        }
        else if(v.getId()==R.id.attdn)
        {
            generate_attdnsheet g=new generate_attdnsheet(this);
            g.execute();
        }
        else if(v.getId()==R.id.notify)
        {
            notify_mentor n=new notify_mentor(this);
            n.execute();
        }
        else if(v.getId()==R.id.admin_logout)
        {
            i=new Intent(this,Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(getApplicationContext(),"Bye",Toast.LENGTH_LONG).show();
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
