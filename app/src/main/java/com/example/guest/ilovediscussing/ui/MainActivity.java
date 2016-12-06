package com.example.guest.ilovediscussing.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.ilovediscussing.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.username) EditText mUsername;
    @Bind(R.id.password) EditText mPassword;
    @Bind(R.id.signInButton) Button mSignInButton;
    @Bind(R.id.signUpButton) Button mSignUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSignInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mSignInButton){
           String username = mUsername.getText().toString();
            String password = mPassword.getText().toString();
            Intent intent = new Intent(MainActivity.this, CategoryListActivity.class);
            Log.d("MainActivity", username);
            Log.d("MainActivity", password);
            startActivity(intent);

        }
    }


}
