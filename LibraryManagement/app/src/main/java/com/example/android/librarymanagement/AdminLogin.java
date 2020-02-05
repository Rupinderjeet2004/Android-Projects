package com.example.android.librarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    Button loginbutt;
    String passF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        loginbutt = (Button) findViewById(R.id.loginButtadminmainpage);
        onClickButt();
    }

    public void onClickButt() {
        loginbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText usrname = (EditText) findViewById(R.id.admin_login_userF);
                EditText password = (EditText) findViewById(R.id.passFieldadminlogin);

                String user = usrname.getText().toString();
                String pass = password.getText().toString();
                passF = helper.searchAdmin(user);

                if (user.equals("") || pass.equals("")) {
                    Toast mess = Toast.makeText(AdminLogin.this, "All Fields Required", Toast.LENGTH_LONG);
                    mess.show();
                } else {
                    if (pass.equals(passF)) {
                        Intent i = new Intent(AdminLogin.this, AdminHomePage.class);
                        startActivity(i);
                    } else {
                        Toast mess = Toast.makeText(AdminLogin.this, "Username and Password don't match", Toast.LENGTH_LONG);
                        mess.show();
                    }
                }
            }
        });


    }

}

