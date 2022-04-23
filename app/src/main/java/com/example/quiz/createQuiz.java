package com.example.quiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class createQuiz extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView question1;
    TextView answer1;
    String question1Shpref;
    String answer1Shpref;

    public static final String mypreference = "myprefqnsans";
    public static final String Question1 = "question1Key";
    public static final String Answer1 = "answer1Key";

    EditText ed1,ed2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        ed1 = findViewById(R.id.question);
        ed2 = findViewById(R.id.answer);

        question1 = (TextView) findViewById(R.id.question);
        answer1 = (TextView) findViewById(R.id.answer);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Question1)) {
            question1.setText(sharedpreferences.getString(Question1, ""));
        }
        if (sharedpreferences.contains(Answer1)) {
            answer1.setText(sharedpreferences.getString(Answer1, ""));

        }

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), ViewQuiz.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    public void insert()
    {
        try
        {
            String question = ed1.getText().toString();
            String answer = ed2.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS test2(id INTEGER PRIMARY KEY AUTOINCREMENT,question VARCHAR,answer VARCHAR)");

            String sql = "insert into test2(question,answer)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,question);
            statement.bindString(2,answer);
            statement.execute();
            Toast.makeText(this,"Record addded",Toast.LENGTH_LONG).show();

            // Shared Pref start
            String n = question1.getText().toString();
            String e = answer1.getText().toString();

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Question1, n);
            editor.putString(Answer1, e);

            Toast.makeText(createQuiz.this, "SUCCESS SharedPref", Toast.LENGTH_LONG).show();
            // Shared Pref End

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
