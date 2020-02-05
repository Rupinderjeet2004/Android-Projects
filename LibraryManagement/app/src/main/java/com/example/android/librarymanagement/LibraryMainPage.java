package com.example.android.librarymanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LibraryMainPage extends AppCompatActivity {

    public Button issuebuttpage,returnbutt,issuedbookbutt,addnewbooksbutt,rembookbutt,logoutlib;
    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_main_page);
        issuebuttpage = (Button)findViewById(R.id.issueButtlibrarymainpage);
        returnbutt = (Button) findViewById(R.id.returnbookButtlibrarymainpage);
        issuedbookbutt = (Button) findViewById(R.id.issuedbooksButtlibrarymainpage);
        addnewbooksbutt = (Button) findViewById(R.id.addnewbooksButtlibrarymainpage);
        rembookbutt = (Button) findViewById(R.id.rembooksButtlibrarymainpage);
        logoutlib = (Button) findViewById(R.id.logoutButtlibrarymainpage);
        issueBook();
        returnBook();
        issuedBooks();
        addnewBooks();
        removeBooks();
        logOut();
    }

            public void issueBook(){
            issuebuttpage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LibraryMainPage.this, IssueButtonFrame.class);
                        startActivity(i);
                    }
                });
            }

            public void returnBook() {
            returnbutt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LibraryMainPage.this, ReturnButton.class);
                        startActivity(i);

                    }
                });
            }

            public void issuedBooks() {
            /*issuedbookbutt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LibraryMainPage.this, IssuedBooksButton.class);
                        startActivity(i);

                    }
                });*/

                issuedbookbutt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = helper.getIssuedBooks();
                        if(res.getCount() == 0){
                            //show message
                            showMessage("Error!","Nothing Found");
                            return;
                        }else{

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()){
                                buffer.append("Name: "+ res.getString(0)+"\n");
                                buffer.append("RollNo: "+ res.getString(1)+"\n");
                                buffer.append("Trade: "+ res.getString(2)+"\n");
                                buffer.append("Semester: "+ res.getString(3)+"\n");
                                buffer.append("PhoneNo: "+ res.getString(4)+"\n");
                                buffer.append("BookId: "+ res.getString(5)+"\n");
                                buffer.append("BookTitle: "+ res.getString(6)+"\n");
                                buffer.append("BookAuthor: "+ res.getString(7)+"\n");
                                buffer.append("IssueDate: "+ res.getString(8)+"\n");
                                buffer.append("Re-IssueDate: "+ res.getString(9)+"\n");
                                buffer.append("ReturnDate: "+ res.getString(10)+"\n");
                                buffer.append("Fine: "+ res.getString(11)+"\n\n");
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


            public void addnewBooks() {
            addnewbooksbutt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LibraryMainPage.this, AddNewBooks.class);
                        startActivity(i);

                    }
                });
            }

            public void removeBooks() {
            rembookbutt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LibraryMainPage.this, RemoveBooks.class);
                        startActivity(i);

                    }
                });
            }

            public void logOut() {
            logoutlib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LibraryMainPage.this, MainActivity.class);
                        startActivity(i);

                    }
                });

            }

}