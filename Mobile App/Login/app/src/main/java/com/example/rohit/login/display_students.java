package com.example.rohit.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class display_students extends AsyncTask<String,Void,Integer>{

    Context con;
    String data,s;
    String sub,date,p1,p2;
    ListView lv;
    JSONObject jo=null;
    JSONArray js;
    Button b;
    ListViewAdapter l;
    ArrayList<String> stu;
    Intent i;
    AlertDialog.Builder alert;
    AlertDialog stualert;
    int count;
    LayoutInflater inflater;
    TextView total,present,absent;
    store_attendance attendance;

    private ProgressDialog dialog;
    ArrayList<students_list> students=new ArrayList<>();

    public display_students(Context c,String d, ListView lv,Button b)
    {
        con=c;
        data=d;
        this.lv=lv;
        this.b=b;
    }

    @Override
    protected void onProgressUpdate(Void... values) {

    }

    @Override
    protected Integer doInBackground(String... params) {
        sub=params[0];
        date=params[1];
        p1=params[2];
        p2=params[3];

        try
        {
            js= new JSONArray(data);
            students_list s;
            for(int i=0;i<js.length();i++)
            {
                jo=js.getJSONObject(i);

                String id=jo.getString("id");
                String name=jo.getString("name");
                String rollno=jo.getString("rollno");

                String img="http://exams.griet.in/photosrgb/"+rollno+".JPG";

                Bitmap image= BitmapFactory.decodeStream(new URL(img).openStream());

                s=new students_list();
                s.setId(id);
                s.setName(name);
                s.setRollno(rollno);
                s.setimage(image);


                students.add(s);
            }
            return 1;
        }

        catch (JSONException e)
        {

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }

        return 0;
    }

    @Override
    protected void onPreExecute() {
        dialog= new ProgressDialog(con);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Loading");
        dialog.show();

        alert=new AlertDialog.Builder(con).setTitle("Confirm").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                attendance.execute(sub,date,p1,p2);
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.stutable, null);
        alert.setIcon(R.mipmap.attnmarked);
        alert.setView(view);
        stualert=alert.create();
        total=(TextView) view.findViewById(R.id.total);
        present=(TextView) view.findViewById(R.id.present);
        absent=(TextView) view.findViewById(R.id.absent);

    }

    @Override
    protected void onPostExecute(Integer integer) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if(integer==0)
        {
            Toast.makeText(con,"Unable",Toast.LENGTH_LONG).show();
        }
        else
        {
            l=new ListViewAdapter(con,students);
            lv.setAdapter(l);
            count=lv.getAdapter().getCount();
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    stu=l.getToa();
                    int size=stu.size();
                    s="";
                    for(int i=0;i<size;i++)
                    {
                        if(s.equals(""))
                            s=stu.get(i);
                        else
                            s=s+","+stu.get(i);
                    }

                    total.setText(count+"");
                    present.setText(size+"");
                    absent.setText((count-size)+"");
                    stualert.show();

                    //send s to database
                    attendance=new store_attendance(con,s,stu);
                }
            });

        }
    }
}