package com.dev_ver.news.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dev_ver.news.R;
import com.dev_ver.news.dashboard;

public class Login extends AppCompatActivity {
    public void loginButton(View view)
    {
        EditText username=findViewById(R.id.loginid);
        EditText password=findViewById(R.id.passwordget);
       String name = username.getText().toString();
       String pass= password.getText().toString();
        if(name.equals("Tester")&&pass.equals("HowIsTheApp"))
        {Intent intent=new Intent(getApplicationContext(), dashboard.class);
        findViewById(R.id.loginButton).setEnabled(false);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Your Entered Username or Password is Incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}