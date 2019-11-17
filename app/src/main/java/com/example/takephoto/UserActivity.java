package com.example.takephoto;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent = getIntent();
        String usernameStr = intent.getStringExtra("username");
        TextView usernameTV = findViewById(R.id.usernameTV);
        usernameTV.setText(usernameStr);
    }

    public void backBtnClick(View view) {
        EditText ageET = findViewById(R.id.ageET);
        EditText emailET = findViewById(R.id.emailET);
        Intent dataIntent = new Intent();
        dataIntent.putExtra("userAge",ageET.getText().toString());
        dataIntent.putExtra("userEmail",emailET.getText().toString());
        setResult(Activity.RESULT_OK,dataIntent);
        finish();
    }
}

