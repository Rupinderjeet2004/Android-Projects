package com.example.android.librarymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveLibrarian extends AppCompatActivity {

    Button remlib;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_librarian);
        remlib = (Button) findViewById(R.id.removeButtremovelibrarianframe);
        deleteLib();
    }

    public void deleteLib() {
        remlib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText) findViewById(R.id.userFieldremLibrarian);
                String user = username.getText().toString();
                int deletedlibs = helper.remLibrarian(user);

                if (deletedlibs > 0)
                    Toast.makeText(RemoveLibrarian.this, "Librarian Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RemoveLibrarian.this, "Librarian not Deleted ", Toast.LENGTH_LONG).show();
            }
        });
    }

}
