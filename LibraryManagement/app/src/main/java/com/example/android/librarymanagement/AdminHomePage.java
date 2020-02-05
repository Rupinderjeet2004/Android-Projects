package com.example.android.librarymanagement;

import android.content.Intent;
import android.database.Cursor;
import android.media.ExifInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomePage extends AppCompatActivity {

    public Button addlib, remlib, liblist, seedetails, logout, changecolor;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        addlib = (Button) findViewById(R.id.addLibrarianadminmainpage);
        remlib = (Button) findViewById(R.id.remLibrarianadminmainpage);
        liblist = (Button) findViewById(R.id.librarianListadminmainpage);
        seedetails = (Button) findViewById(R.id.seeDetailsadminmainpage);
        logout = (Button) findViewById(R.id.logOutadminmainpage);
        changecolor = (Button) findViewById(R.id.changeColoradminmainpage);
        addLibrarian();
        removeLibrarian();
        librarianList();
        seeDetail();
        logOut();
        changeColor();


    }

    public void addLibrarian() {

        addlib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomePage.this, AddLibrarian.class);
                startActivity(i);
            }
        });
    }

    public void removeLibrarian() {

        remlib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomePage.this, RemoveLibrarian.class);
                startActivity(i);
            }
        });
    }

    public void librarianList() {
        liblist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = helper.getLibrarianList();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error!","Nothing Found");
                    return;
                }else{

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Id: "+ res.getString(0)+"\n");
                        buffer.append("Username: "+ res.getString(1)+"\n\n");
                    }
                    //show all data
                    showMessage("Librarian List",buffer.toString());
                }
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void seeDetail() {

        seedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomePage.this, seeDetails.class);
                startActivity(i);
            }
        });
    }

    public void logOut() {

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomePage.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void changeColor() {

    }
}


