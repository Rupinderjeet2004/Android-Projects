package com.example.android.librarymanagement;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLibrarian extends AppCompatActivity {

    Button savebutt,librarianlist;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_librarian);
        savebutt = (Button)findViewById(R.id.savebuttaddLibrarian);
        librarianlist = (Button)findViewById(R.id.librarianListbutt);
        saveButton();
        librarianList();
    }

    public void saveButton()
    {
        savebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText)findViewById(R.id.userFieldremLibrarian);
                EditText password = (EditText)findViewById(R.id.passFieldaddLibrarian);

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") && pass.equals(""))
                {
                     Toast.makeText(AddLibrarian.this, "All Fields Required", Toast.LENGTH_LONG).show();
                }
                else{

                   boolean isInserted =  helper.addLibrarian(user,pass);
                   if(isInserted == true)
                      Toast.makeText(AddLibrarian.this, "Data Inserted", Toast.LENGTH_LONG).show();
                   else
                       Toast.makeText(AddLibrarian.this, "Data not Inserted", Toast.LENGTH_LONG).show();


                }

            }
        });

    }

    public void librarianList(){
        librarianlist.setOnClickListener(new View.OnClickListener() {
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
}
