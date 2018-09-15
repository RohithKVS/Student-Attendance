package com.example.rohit.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class studentdisplay extends AppCompatActivity {
    TextView stulist;
    AlertDialog.Builder alert;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdisplay);
        i=new Intent(this,Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        alert=new AlertDialog.Builder(this).setTitle("Confirm").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Bye",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.create();
        alert.setMessage("Are you sure you want to logout?");
        alert.setIcon(R.mipmap.goback);
        stulist=(TextView) findViewById(R.id.stulist);
        ArrayList<String> stu=(ArrayList<String>) getIntent().getSerializableExtra("stu");
        String s="";
        for(int i=0;i<stu.size();i++)
            s=s+"\n"+stu.get(i);
        if(s.equals(""))
            s="All are absent";
        stulist.setText(s);

    }
    public void logout(View v)
    {
        alert.show();
    }

    @Override
    public void onBackPressed() {

    }
}
