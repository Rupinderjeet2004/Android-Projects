package com.example.android.librarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class librarianLogin extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    Button loginbuttLP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);
        loginbuttLP = (Button) findViewById(R.id.loginButtlibrarianloginframe);
        onClickButt();
    }
        public void onClickButt ()
        {
            loginbuttLP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditText usrname = (EditText) findViewById(R.id.librarian_login_userF);
                    EditText password = (EditText) findViewById(R.id.librarian_login_passF);

                    String user = usrname.getText().toString();
                    String pass = password.getText().toString();

                    String passF = helper.searchLibrarian(user);


                    if (user.equals("") || pass.equals("")) {
                        Toast mess = Toast.makeText(librarianLogin.this, "All Fields Required", Toast.LENGTH_LONG);
                        mess.show();
                    } else {
                        if (pass.equals(passF)) {
                            Intent i = new Intent(librarianLogin.this, LibraryMainPage.class);
                            startActivity(i);
                        } else {
                            Toast mess = Toast.makeText(librarianLogin.this, "Username and Password don't match", Toast.LENGTH_LONG);
                            mess.show();
                        }
                    }


                }
            });

        }
    }
