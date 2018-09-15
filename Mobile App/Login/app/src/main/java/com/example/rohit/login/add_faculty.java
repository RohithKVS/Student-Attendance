package com.example.rohit.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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

public class add_faculty extends AsyncTask<String,Void,String> {
    Context con;
    String facultyname,password;
    ProgressDialog dialog;
    public add_faculty(Context c)
    {
        con=c;
    }
    @Override
    protected String doInBackground(String... params) {
        facultyname=params[0];
        password=params[1];
        try
        {
            URL url=new URL("http://grietonlineattendance.000webhostapp.com/add_faculty.php");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            String data= URLEncoder.encode("facultyname","UTF-8")+"="+URLEncoder.encode(facultyname,"UTF-8");
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
        } catch (UnsupportedEncodingException e) {

        } catch (ProtocolException e) {

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
        return null;
    }

    @Override
    protected void onPreExecute() {

        dialog= new ProgressDialog(con);
        dialog.setCanceledOnTouchOutside(false);
        this.dialog.setMessage("Adding");
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