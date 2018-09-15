package com.example.rohit.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class validate_login extends AsyncTask<String,Void,String>
{
    AlertDialog.Builder alert;
    Context con;
    String username,password;
    private ProgressDialog dialog;

    validate_login(Context c)
    {
        con=c;
    }



    @Override
    protected String  doInBackground(String... params)
    {
        username=params[0];
        password=params[1];

        try
        {

            URL url=new URL("http://grietonlineattendance.000webhostapp.com/login.php");

            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                String data= URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                data +="&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                BufferedWriter bufferedWriter=
                        new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();


                BufferedReader bufferedReader=
                        new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String result=bufferedReader.readLine();
                bufferedReader.close();
                httpURLConnection.disconnect();
                return result;


        }
        catch (UnsupportedEncodingException e)
        {
            alert.setMessage("Unsupported");
            alert.show();
        }
        catch (ProtocolException e)
        {
            alert.setMessage("Protocol");
            alert.show();
        }
        catch (MalformedURLException e)
        {
            alert.setMessage("Malformed");
            alert.show();
        }
        catch (IOException e)
        {
            alert.setMessage("IO");
            alert.show();
        }


        return null;
    }

    @Override
    protected void onPreExecute()
    {
        alert=new AlertDialog.Builder(con).setTitle("Alert").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.setIcon(R.mipmap.alert);
        alert.create();
        dialog= new ProgressDialog(con);
        dialog.setCanceledOnTouchOutside(false);
        this.dialog.setMessage("Loading");
        this.dialog.show();

    }

    @Override
    protected void onPostExecute(String aVoid) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if(aVoid.toLowerCase().equals("faculty"))
        {
            Intent i=new Intent(con,Year.class);
            i.putExtra("name",username);
            con.startActivity(i);
        }
        else if(aVoid.toLowerCase().equals("admin"))
        {
            Intent i=new Intent(con,Admin.class);
            i.putExtra("name",username);
            con.startActivity(i);
        }
        else
        {
            alert.setMessage(aVoid);
            alert.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {

    }
}