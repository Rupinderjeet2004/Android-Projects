package com.example.android.librarymanagement;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveBooks extends AppCompatActivity {
    Button removeButt,bookStock;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_books);
        removeButt = (Button)findViewById(R.id.removebuttRemovebooksframe) ;
        bookStock = (Button)findViewById(R.id.booklistButtRemovebooksframe);
        deleteBooks();
        booksStock();
    }

    public void deleteBooks() {
        removeButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText id = (EditText) findViewById(R.id.bookidFremovebooks);
                EditText booktitle = (EditText)findViewById(R.id.booktitleFremovebooks);

                String ID = id.getText().toString();
                String BOOKTITLE = booktitle.getText().toString();

                int deletedbook = helper.remBooks(ID,BOOKTITLE);

                if (deletedbook > 0)
                    Toast.makeText(RemoveBooks.this, "Book Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RemoveBooks.this, "Book not Deleted ", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void booksStock(){
        bookStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = helper.getnewAddedBooks();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error!","Nothing Found");
                    return;
                }else{

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Id: "+ res.getString(0)+"\n");
                        buffer.append("BookTitle: "+ res.getString(1)+"\n");
                        buffer.append("BookAuthor: "+ res.getString(2)+"\n");
                        buffer.append("Date: "+ res.getString(3)+"\n\n");
                    }
                    //show all data
                    showMessage("Books List",buffer.toString());
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
