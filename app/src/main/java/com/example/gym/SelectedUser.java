package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectedUser extends AppCompatActivity {
    TextView tvuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_user);

        tvuser = (TextView)findViewById(R.id.selectedUser);

        Intent intent = getIntent();

        if (intent.getExtras() != null){
            User user = (User) intent.getSerializableExtra("data");
            tvuser.setText(user.getFname());
        }
    }

    public void dashboard(View view) {
        Intent intent = new Intent(SelectedUser.this,Customers_list.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left,R.anim.exit_through_left);
        finish();
    }
}