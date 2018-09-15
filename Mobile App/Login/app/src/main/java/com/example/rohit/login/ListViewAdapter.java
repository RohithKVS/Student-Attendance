package com.example.rohit.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.ImageLoader;

import java.security.PublicKey;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    Context con;
    ArrayList<students_list> s;
    LayoutInflater inflater;
    boolean checkboxstate[];
    ViewHolder viewHolder;
    ArrayList<String> student= new ArrayList<>();
    String toa="";

    public ListViewAdapter(Context c, ArrayList<students_list> a)
    {
        con=c;
        s=a;
        checkboxstate=new boolean[a.size()];
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class ViewHolder
    {
        TextView name,rollno;
        CheckBox mark;
        ImageView stuimage;
    }


    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int position) {
        return s.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView= inflater.inflate(R.layout.displaymodel,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView) convertView.findViewById(R.id.stuname);
            viewHolder.rollno=(TextView) convertView.findViewById(R.id.rollno);
            viewHolder.mark=(CheckBox) convertView.findViewById(R.id.mark);
            viewHolder.stuimage=(ImageView) convertView.findViewById(R.id.stuimage);
            convertView.setTag(viewHolder);
        }
        else
        viewHolder=(ViewHolder) convertView.getTag();

        final students_list s=(students_list) this.getItem(position);
        viewHolder.name.setText(s.getName());
        viewHolder.rollno.setText(s.getRollno());
        viewHolder.stuimage.setImageBitmap(s.getImage());


        viewHolder.mark.setChecked(checkboxstate[position]);
        viewHolder.mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked())
                {
                    checkboxstate[position]=true;
                    student.add(s.getRollno());
                }
                else
                {
                    checkboxstate[position]=false;
                    student.remove(s.getRollno());
                }
            }
        });



        return convertView;
    }

    public ArrayList<String> getToa()
    {
        return student;
    }

}