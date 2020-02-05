package com.example.android.librarymanagement;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNewBooks extends AppCompatActivity {

    EditText editTextdate,editTextbookId,editTextbookTitle,editTextbookauthor;
    Button saveButton,bookList;
    DatabaseHelper helper = new DatabaseHelper(this);
    int d,m,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_books);
        editTextbookId = (EditText)findViewById(R.id.bookidFaddnewbooks);
        editTextbookTitle = (EditText)findViewById(R.id.booktitleFaddnewbooks);
        editTextbookauthor = (EditText)findViewById(R.id.bookauthorFaddnewbooks);
        editTextdate = (EditText)findViewById(R.id.dateFaddnewbooks);
        saveButton = (Button) findViewById(R.id.savebuttaddnewwbooksframe);
        bookList = (Button) findViewById(R.id.bookListbuttAddnewbooks);
        datePicker();
        SaveButton();
        BookList();
    }

    public void datePicker(){
        editTextdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Calendar calendar = Calendar.getInstance();
                d=calendar.get(Calendar.DAY_OF_MONTH);
                m=calendar.get(Calendar.MONTH);
                y=calendar.get(Calendar.YEAR);
                DatePickerDialog pickerDialog = new DatePickerDialog(AddNewBooks.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1+=1;
                        editTextdate.setText(+ i2 + "-" + i1 + "-"+i);
                    }
                }, y,m,d);
                pickerDialog.show();
            }
        });
    }

    public void SaveButton(){

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText bookId = (EditText)findViewById(R.id.bookidFaddnewbooks);
                EditText bookTitle = (EditText)findViewById(R.id.booktitleFaddnewbooks);
                EditText bookAuthor = (EditText)findViewById(R.id.bookauthorFaddnewbooks);
                EditText date = (EditText)findViewById(R.id.dateFaddnewbooks);

                String Id = bookId.getText().toString();
                String Title = bookTitle.getText().toString();
                String Author = bookAuthor.getText().toString();
                String Date = date.getText().toString();

                if(Id.equals("") || Title.equals("") || Author.equals("") || Date.equals(""))
                {
                    Toast.makeText(AddNewBooks.this, "All Fields Required", Toast.LENGTH_LONG).show();
                }else{
                    boolean isInserted =  helper.addNewBooks(Id,Title,Author,Date);
                    if(isInserted == true )
                        Toast.makeText(AddNewBooks.this, "Books Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AddNewBooks.this, "Book Already Exist", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void BookList(){
        bookList.setOnClickListener(new View.OnClickListener() {
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
