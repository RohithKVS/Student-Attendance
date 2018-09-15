package com.example.rohit.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class generate_attdnsheet extends AsyncTask<Void,Void,String>
{

    Context con;
    ProgressDialog dialog;

    public generate_attdnsheet(Context c)
    {
        con=c;
    }

    @Override
    protected String doInBackground(Void... params)
    {
        try
        {

            URL url=new URL("http://grietonlineattendance.000webhostapp.com/createtable.php");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            BufferedReader bufferedReader=
                    new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String result=bufferedReader.readLine();
            bufferedReader.close();
            httpURLConnection.disconnect();
            return result;
        }
        catch (ProtocolException e) {

        }
        catch (MalformedURLException e)
        {

        }
        catch (IOException e)
        {

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        dialog= new ProgressDialog(con);
        dialog.setCanceledOnTouchOutside(false);
        this.dialog.setMessage("Generating");
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(con,s,Toast.LENGTH_LONG).show();
    }
}