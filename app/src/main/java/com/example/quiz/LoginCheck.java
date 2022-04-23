package com.example.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginCheck extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView username;
    TextView password;
    String usernameShpref;
    String passwordShpref;
    String usernamestring;
    String passwordstring;

    public static final String mypreference = "mypref";
    public static final String Username = "usernameKey";
    public static final String Password = "passwordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logincheck);
        username = (TextView) findViewById(R.id.etUsername);
        password = (TextView) findViewById(R.id.etPassword);



        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Username)) {
            username.setText(sharedpreferences.getString(Username, ""));
        }
        if (sharedpreferences.contains(Password)) {
            password.setText(sharedpreferences.getString(Password, ""));

        }

    }

    public void clear(View view) {
        username = (TextView) findViewById(R.id.etUsername);
        password = (TextView) findViewById(R.id.etPassword);
        username.setText("");
        password.setText("");

    }

    public void Check(View view) {
        usernameShpref = sharedpreferences.getString(Username, "");
        passwordShpref = sharedpreferences.getString(Password, "");
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        usernamestring = username.getText().toString();
        passwordstring = password.getText().toString();

        if (usernameShpref.equals(usernamestring) && passwordShpref.equals(passwordstring))
        {
            Toast.makeText(this,"Login Success", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), Home.class);
            intent.putExtra("message_name",usernamestring);

            startActivity(intent);
        }
        else {
            Toast.makeText(this,"Login Fail! Please try again!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
