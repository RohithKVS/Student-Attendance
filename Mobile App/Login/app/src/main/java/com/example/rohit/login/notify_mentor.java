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
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class notify_mentor extends AsyncTask<Void,Void,String> {

    Context con;
    ProgressDialog dialog;

    public notify_mentor(Context c)
    {
        con=c;
    }

    @Override
    protected String doInBackground(Void... params)
    {
        try
        {
            String string1 = "17:00:00";
            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);

            String string2 = "23:59:59";
            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);
            Date d = Calendar.getInstance().getTime();
            String string3=new SimpleDateFormat("HH:mm:ss").format(d);

            Date currtime = new SimpleDateFormat("HH:mm:ss").parse(string3);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(currtime);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime()))
            {
                URL url=new URL("http://grietonlineattendance.000webhostapp.com/notify_mentor.php");
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
            else
                return "Mentors can be notified only between 5PM and 12AM";
        }

        catch (MalformedURLException e)
        {

        } catch (IOException e) {

        } catch (ParseException e) {

        }
        return null;
    }

    @Override
    protected void onPreExecute()
    {
        dialog= new ProgressDialog(con);
        dialog.setCanceledOnTouchOutside(false);
        this.dialog.setMessage("Notifying");
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(String s)
    {
        if (dialog.isShowing())
        {
            dialog.dismiss();
        }
        Toast.makeText(con,s,Toast.LENGTH_LONG).show();
    }
}