package com.example.takephoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private int USER_ACTIVITY_CODE = 100;
    String userEmail="";
    String userAge="";
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginBtnClick(View view) {
        //explicit intent
        EditText usernameET = findViewById(R.id.usernameET);
        EditText passwordET = findViewById(R.id.passwordET);
        userName= usernameET.getText().toString();
        String password = passwordET.getText().toString();
        if(checkIdentity(userName,password)) {
            Intent userIntent = new Intent(this, UserActivity.class);
            userIntent.putExtra("username",userName);
//            startActivity(userIntent);
            startActivityForResult(userIntent,USER_ACTIVITY_CODE);
        }
        else{
            Toast.makeText(this,"Wrong username & password combination",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode== Activity.RESULT_OK && requestCode == USER_ACTIVITY_CODE){
            userAge = data.getStringExtra("userAge");
            userEmail = data.getStringExtra("userEmail");

            if(checkValidEmail(userEmail)) {
                TextView userDescTV = findViewById(R.id.descTV);
                userDescTV.setText("User age: " + userAge + "\nEmail: " + userEmail);
                userDescTV.setVisibility(View.VISIBLE);

                EditText passwordET = findViewById(R.id.passwordET);
                passwordET.setVisibility(View.INVISIBLE);
                Button loginBtn = findViewById(R.id.loginBtn);
                loginBtn.setVisibility(View.INVISIBLE);
                TextView passwordTV = findViewById(R.id.passwordTV);
                passwordTV.setVisibility(View.INVISIBLE);
            }
            else{
                Toast.makeText(this,"Invalid email",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean checkIdentity(String username, String password) {
        if(username.equals("truyenle") && password.equals("1"))
            return true;
        else
            return false;
    }

    public void sendEmailToUser(View view) {
        //Implicit intent
        Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Hello "+ userName);
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "This is an email for "+userName+", Age: "+userAge);
        String [] recipients = new String[]{userEmail};
        emailIntent.putExtra(Intent.EXTRA_EMAIL,recipients);

        startActivity(Intent.createChooser(emailIntent,"Send email from ..."));

    }

    public void takePhotoBtnClick(View view) {
//        Button btnCamera = (Button)findViewById(R.id.btnCamera);
        Intent Intent3=new   Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        startActivity(Intent3);
    }
}
