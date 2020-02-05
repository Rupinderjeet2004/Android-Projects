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

public class ReturnBooks extends AppCompatActivity {
    EditText editTextdate,editTextrno,editTextbookid;
    Button returnButt,issuedBooks;
    int d,m,y;
    Spinner trade,sem;
    ArrayList<String> tradelist = new ArrayList<String>();
    ArrayList<String> semlist = new ArrayList<String>();
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_books);
        editTextrno = (EditText)findViewById(R.id.rnoFreturnbooksframe);
        editTextbookid = (EditText)findViewById(R.id.bookidFreturnbooksframe);
        returnButt = (Button)findViewById(R.id.savebuttreturnbookframe);
        issuedBooks = (Button)findViewById(R.id.issuedbooksbuttreturnbooksframe);
        editTextdate = (EditText)findViewById(R.id.dateFreturnbooksframe);
        classSpinner();
        datePicker();
        onReturn();
        onIssued();
    }

    public void classSpinner()
    {
        trade = findViewById(R.id.spinner21RETBT);
        sem = findViewById(R.id.spinner22RETBS);

        tradelist.add("CSE");
        tradelist.add("CIVIL");
        tradelist.add("MECHANICAL");
        tradelist.add("ECE");
        tradelist.add("ELECTRICAL");

        semlist.add("1st");
        semlist.add("2nd");
        semlist.add("3rd");
        semlist.add("4th");
        semlist.add("5th");
        semlist.add("6th");

        ArrayAdapter<String> tradeadapter = new ArrayAdapter<String>(
                ReturnBooks.this,android.R.layout.simple_spinner_dropdown_item,tradelist);
        ArrayAdapter<String> semadapter = new ArrayAdapter<String>(
                ReturnBooks.this,android.R.layout.simple_spinner_dropdown_item,semlist);

        trade.setAdapter(tradeadapter);
        sem.setAdapter(semadapter);
    }

    public void datePicker(){
        editTextdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Calendar calendar = Calendar.getInstance();
                d=calendar.get(Calendar.DAY_OF_MONTH);
                m=calendar.get(Calendar.MONTH);
                y=calendar.get(Calendar.YEAR);
                DatePickerDialog pickerDialog = new DatePickerDialog(ReturnBooks.this, new DatePickerDialog.OnDateSetListener() {
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

    public void onReturn()
    {
        returnButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno = editTextrno.getText().toString();
                String getTrade = trade.getSelectedItem().toString();
                String getsem = sem.getSelectedItem().toString();
                String bookid = editTextbookid.getText().toString();
                String date = editTextdate.getText().toString();

                if(rno.equals("") || bookid.equals("")
                        || date.equals(""))
                {
                    Toast.makeText(ReturnBooks.this, "All Fields Required", Toast.LENGTH_LONG).show();
                }else {
                    boolean isInserted =  helper.onReturn(rno,getTrade,getsem,bookid,date);
                    if(isInserted == true)
                        Toast.makeText(ReturnBooks.this, "Data Updated", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(ReturnBooks.this, "Data not Updated", Toast.LENGTH_LONG).show();


                }
            }
        });
    }

    public void onIssued(){

        issuedBooks.setOnClickListener(new View.OnClickListener() {
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
}
