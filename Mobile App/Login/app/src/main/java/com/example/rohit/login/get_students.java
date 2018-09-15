package com.example.rohit.login;

import android.app.Activity;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class get_students extends AsyncTask<String,Void,String> {
    AlertDialog.Builder alert;
    Context con;
    String dbid,sub,p1,p2,date;
    StringBuffer sb;
    String line=null;

    private ProgressDialog dialog;

    public get_students(Context c)
    {
        con=c;
    }


    @Override
    protected String doInBackground(String... params) {
        dbid=params[0];
        sub=params[1];
        date=params[2];
        p1=params[3];
        p2=params[4];
        try
        {
            URL url = new URL("http://grietonlineattendance.000webhostapp.com/studetails.php");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            String data= URLEncoder.encode("dbname","UTF-8")+"="+URLEncoder.encode(dbid,"UTF-8");
            data +="&"+URLEncoder.encode("sub","UTF-8")+"="+URLEncoder.encode(sub,"UTF-8");
            data +="&"+URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");
            data +="&"+URLEncoder.encode("p1","UTF-8")+"="+URLEncoder.encode(p1,"UTF-8");
            data +="&"+URLEncoder.encode("p2","UTF-8")+"="+URLEncoder.encode(p2,"UTF-8");


            BufferedWriter bufferedWriter=
                    new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();

            BufferedReader br=
                    new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            sb=new StringBuffer();
            while ((line=br.readLine())!=null)
            {
                sb.append(line+"\n");
            }

            return sb.toString();
        }
        catch (MalformedURLException e)
        {

        } catch (ProtocolException e) {

        } catch (IOException e) {

        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alert=new AlertDialog.Builder(con).setTitle("Alert").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity)con).finish();
            }
        });
        alert.setIcon(R.mipmap.attnmarked);
        alert.create();

        dialog= new ProgressDialog(con);
        dialog.setCanceledOnTouchOutside(false);
        this.dialog.setMessage("Loading");
        this.dialog.show();

    }

    @Override
    protected void onPostExecute(String s)
    {

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if(s!=null)
        {
            if(s.toLowerCase().equals("hi"+"\n"))
            {
                alert.setMessage("You have already taken attendance. Click OK to go back");
                alert.show();
            }
            else
            {
                Intent i=new Intent(con,Students.class);
                i.putExtra("stu",s);
                i.putExtra("sub",sub);
                i.putExtra("date",date);
                i.putExtra("p1",p1);
                i.putExtra("p2",p2);

                con.startActivity(i);
                ((Activity)con).finish();
            }
        }
        else
            Toast.makeText(con,"Unable to download data",Toast.LENGTH_SHORT).show();
    }
}