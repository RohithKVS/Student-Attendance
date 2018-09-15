package com.example.rohit.login;

import android.graphics.Bitmap;

public class students_list
{
    private String name;
    private String rollno;
    private String id;
    private Bitmap image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public Bitmap getImage()
    {
        return image;
    }
    public void setimage(Bitmap image)
    {
        this.image=image;
    }

    @Override
    public String toString() {
            return name;
    }
}