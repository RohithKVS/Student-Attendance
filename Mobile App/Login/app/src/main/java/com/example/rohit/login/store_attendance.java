package com.example.rohit.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class store_attendance extends AsyncTask<String,Void,String> {
    Context con;
    String str,sub,date,p1,p2;
    ArrayList<String> stu;
    ProgressDialog dialog;

    public store_attendance(Context c, String s, ArrayList<String> stu){
        con=c;
        str=s;
        this.stu=stu;
    }

    @Override
    protected String doInBackground(String ... params) {
        sub=params[0];
        date=params[1];
        p1=params[2];
        p2=params[3];

        try
        {
            URL url=new URL("http://grietonlineattendance.000webhostapp.com/store_attendance.php");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            String data= URLEncoder.encode("presentees","UTF-8")+"="+URLEncoder.encode(str,"UTF-8");
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
            String result=br.readLine();
            br.close();
            httpURLConnection.disconnect();
            return result;
        }
        catch (MalformedURLException e) {

        } catch (IOException e) {

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        dialog= new ProgressDialog(con);
        dialog.setCanceledOnTouchOutside(false);
        this.dialog.setMessage("Sending data...Please wait");
        this.dialog.show();

    }

    @Override
    protected void onPostExecute(String s) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if(s!=null)
        {
            Toast.makeText(con,s,Toast.LENGTH_SHORT).show();
            Intent i=new Intent(con,studentdisplay.class);
            i.putExtra("stu",stu);
            con.startActivity(i);
        }
        else
            Toast.makeText(con,"Unable to send data",Toast.LENGTH_SHORT).show();
    }
}