package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void logoutUser(View view) {
        //logout user
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }
}