package com.example.rohit.login;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class Students extends AppCompatActivity {

    AlertDialog.Builder alert;
    String stu,sub,date,p1,p2;
    ListView lv;
    display_students d;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        stu=getIntent().getStringExtra("stu");
        sub=getIntent().getStringExtra("sub");
        date=getIntent().getStringExtra("date");
        p1=getIntent().getStringExtra("p1");
        p2=getIntent().getStringExtra("p2");
        lv=(ListView)findViewById(R.id.listView2);
        Button b=(Button) findViewById(R.id.submitattdn);

        alert=new AlertDialog.Builder(this).setTitle("Confirm").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.create();
        alert.setMessage("You have not saved the attendance. Going to the previous window does not allow you to take attendance for the day. Are you sure?");
        alert.setIcon(R.mipmap.goback);

        d=new display_students(this,stu,lv,b);
        d.execute(sub,date,p1,p2);

    }

    @Override
    public void onBackPressed() {
        alert.show();
    }
}