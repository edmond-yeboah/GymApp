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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    private EditText email,password,cpassword;
    private Button register;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //linking to xml
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        cpassword = (EditText)findViewById(R.id.cpassword);
        register = (Button)findViewById(R.id.btnregister);

        //for firebaseauth
        mAuth = FirebaseAuth.getInstance();

        //for firebaseDatabase
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("user");

        //onclick method for the register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user input validations
                String useremail = email.getText().toString(); //getting the email
                String userpassword = password.getText().toString(); //getting the password
                String retypepassword = cpassword.getText().toString(); //getting the cpassword

                //check if fields are not empty
                if (!TextUtils.isEmpty(useremail) && !TextUtils.isEmpty(userpassword)){

                    //checking if email is valid
                    if (Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
                        //if email is valid, check if password match
                        if (userpassword.equals(retypepassword)){
                            //if password match too
                            registerUser(useremail,userpassword);//method to register user, declared below
                            //populate the user class
                            User newuser = new User(useremail,userpassword);
                        }else {
                            //if password do not match
                            Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                            //refocus on password field
                            password.requestFocus();
                            cpassword.requestFocus();
                        }

                    }else {
                        //if email is not valid
                        Toast.makeText(register.this, "Email must take the form example@example.com", Toast.LENGTH_SHORT).show();
                        //refocus on email field
                        email.requestFocus();
                    }

                }else {
                    //if user submits empty fields
                    Toast.makeText(register.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                    //requesting focus
                    email.requestFocus();
                }

            }
        });
    }

    //method to register user
    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //if task is successful get user information
                    Toast.makeText(register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }else{
                    Toast.makeText(register.this, "User not registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //update UI method
    private void updateUI(FirebaseUser user) {
        //store user info with id
        String keyId = reference.push().getKey();
        reference.child(keyId).setValue(user);
        //go to dashboard if all is successful
        Intent intent = new Intent(register.this, MainActivity.class);
        startActivity(intent);
    }



    //funtion for move to login page
    public void tologin(View view) {
        Intent intent = new Intent(register.this, login.class);
        startActivity(intent);
    }
}