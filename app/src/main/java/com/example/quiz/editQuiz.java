package com.example.quiz;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editQuiz extends AppCompatActivity {

    EditText ed1,ed2,ed4;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        ed1 = findViewById(R.id.question);
        ed2 = findViewById(R.id.answer);
        ed4 = findViewById(R.id.id);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);
        b3 = findViewById(R.id.bt3);


        Intent i = getIntent();

        String t1 = ((Intent) i).getStringExtra("id").toString();
        String t2 = i.getStringExtra("question").toString();
        String t3 = i.getStringExtra("answer").toString();

        ed4.setText(t1);
        ed1.setText(t2);
        ed2.setText(t3);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ViewQuiz.class);
                startActivity(i);

            }
        });




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });

    }

    public void Delete()
    {
        try
        {
            String id = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);


            String sql = "delete from test2 where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted", Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }
    }
    public void Edit()
    {
        try
        {
            String question = ed1.getText().toString();
            String answer = ed2.getText().toString();

            String id = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);


            String sql = "update test2 set question = ?,answer=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,question);
            statement.bindString(2,answer);

            statement.bindString(3,id);
            statement.execute();
            Toast.makeText(this,"Record Updated",Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }

    }

}
