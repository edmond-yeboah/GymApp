package com.example.gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private EditText email, password;
    private Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initializing variables
        email = (EditText)findViewById(R.id.lemail);
        password = (EditText)findViewById(R.id.lpassword);
        login = (Button)findViewById(R.id.login);

        //for firebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //setting an onclick listener for the login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the values from the email and password fields
                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();

                //input validations
                if (!TextUtils.isEmpty(useremail) && !TextUtils.isEmpty(userpassword)){
                    //if email and password fields are not empty, check if user entered email
                    if(Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
                        //if what is entered matches an email address, login user in
                        loginUser(useremail,userpassword);
                    }
                }
            }
        });
    }

    //login method
    private void loginUser(String useremail, String userpassword) {
        //loggin user in
        mAuth.signInWithEmailAndPassword(useremail,userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            updateUI(currentUser);

                        }else {
                            Toast.makeText(login.this, "Login Unsuccessful!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    //updateUI method definition
    private void updateUI(FirebaseUser currentUser) {
        //go to dashboard
        Intent intent = new Intent(login.this, MainActivity.class);
        startActivity(intent);
    }

    //check if user is logged in on app start
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            //get user data and go to dashboard
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    //method to go to register form
    public void toregister(View view) {
        Intent intent = new Intent(login.this, register.class);
        startActivity(intent);
    }
}