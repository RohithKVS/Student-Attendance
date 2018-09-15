package com.example.rohit.login;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity{
    AlertDialog.Builder alert;
    EditText username,pass;
    Button button;
    CheckBox checkBox;
    Boolean saved;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isConnected(this))
            buildDialog(this).show();
        else
        {
            setContentView(R.layout.activity_login);
            username=(EditText) findViewById(R.id.username);
            pass=(EditText) findViewById(R.id.password);
            button=(Button) findViewById(R.id.button);
            checkBox=(CheckBox) findViewById(R.id.checkBox);
            sharedPreferences=getSharedPreferences("loginref",MODE_PRIVATE);
            editor=sharedPreferences.edit();
            saved=sharedPreferences.getBoolean("save",true);
            if(saved==true)
            {
                username.setText(sharedPreferences.getString("name",null));
                pass.setText(sharedPreferences.getString("pswd",null));
            }
        }
        alert=new AlertDialog.Builder(this).setTitle("Confirm").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.setIcon(R.mipmap.goback);
        alert.setMessage("Are you sure you want to exit from Online Attendance?");
        alert.create();

    }
    public void login(View v)
    {
        if(!isConnected(this))
            buildDialog(this).show();
        else
        {
            String user=username.getText().toString();
            String passw=pass.getText().toString();
            if(user.equals("")||passw.equals("")) {
                Toast.makeText(this,"Enter details",Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(checkBox.isChecked())
                {
                    editor.putBoolean("save",true);
                    editor.putString("name",user);
                    editor.putString("pswd",passw);
                    editor.commit();
                }
                else
                {
                    editor.putBoolean("save",true);
                    editor.putString("name","");
                    editor.putString("pswd","");
                    editor.commit();
                }
                validate_login b=new validate_login(this);
                b.execute(user,passw);

            }
        }
    }
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
        return false;
    }

    public AlertDialog buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setIcon(R.mipmap.alert);
        builder.setMessage("You must have an internet connection to access this application. Press OK to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        builder.setCancelable(false);
        AlertDialog alert=builder.create();
        return alert;
    }

    @Override
    public void onBackPressed() {
        alert.show();


    }
}