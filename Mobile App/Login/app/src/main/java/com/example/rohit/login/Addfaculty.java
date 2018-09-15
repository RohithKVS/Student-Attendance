package com.example.rohit.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Addfaculty extends AppCompatActivity {
    EditText facultyname,faculty_password;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfaculty);
        facultyname=(EditText) findViewById(R.id.facultyname);
        faculty_password=(EditText) findViewById(R.id.faculty_password);
        b=(Button) findViewById(R.id.submit);
    }
    public void submit(View v)
    {
        String user=facultyname.getText().toString();
        String passw=faculty_password.getText().toString();
        add_faculty a=new add_faculty(this);
        a.execute(user,passw);
    }
}
