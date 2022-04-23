package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    Button myBtncreatequiz, myBtnstartquiz;
    TextView receiver_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String str = intent.getStringExtra("message_name");
        receiver_msg = (TextView) findViewById(R.id.textView3);
        String newStr;
        newStr = "Welcome, " + str;
        receiver_msg.setText(newStr);


        myBtncreatequiz = (Button) findViewById(R.id.createquiz);


        myBtncreatequiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), createQuiz.class);
                startActivity(intent);
            }
        });


    }
}
