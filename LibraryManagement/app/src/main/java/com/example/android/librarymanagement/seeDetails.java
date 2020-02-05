package com.example.android.librarymanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class seeDetails extends AppCompatActivity {

    Button issuedBooks,returnedBooks,bookStock;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_details);
        returnedBooks = (Button)findViewById(R.id.returnedBooksButtSeeDetailsFrame);
        issuedBooks = (Button)findViewById(R.id.AIBSeeDetailsframe) ;
        bookStock = (Button)findViewById(R.id.bookstockButtseedetailframe);
        returnedBooks();
        allBooks();
    }

    public void returnedBooks() {
        returnedBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(seeDetails.this,returnedBooksAdmin.class);
                startActivity(i);
            }
        });
    }

    public void allBooks(){
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
