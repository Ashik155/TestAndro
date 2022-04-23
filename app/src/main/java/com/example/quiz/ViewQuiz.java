package com.example.quiz;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ViewQuiz extends AppCompatActivity {


    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from test2",null);
        int id = c.getColumnIndex("id");
        int question = c.getColumnIndex("question");
        int answer = c.getColumnIndex("answer");
        titles.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final  ArrayList<model> stud = new ArrayList<model>();


        if(c.moveToFirst())
        {
            do{
                model stu = new model();
                stu.id = c.getString(id);
                stu.question = c.getString(question);
                stu.answer = c.getString(answer);
                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(question) + " \t "  + c.getString(answer) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();



        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                model stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(), editQuiz.class);
                i.putExtra("id",stu.id);
                i.putExtra("question",stu.question);
                i.putExtra("answer",stu.answer);
                startActivity(i);

            }
        });

    }
}
