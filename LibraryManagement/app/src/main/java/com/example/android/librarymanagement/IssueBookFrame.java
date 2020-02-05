package com.example.android.librarymanagement;

import android.app.DatePickerDialog;
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

public class IssueBookFrame extends AppCompatActivity {

    EditText editTextname,editTextrno,editTextphno,editTextbookid,editTextbooktitle,editTextbookauthor,editTextdate;
    Button  reset,issuebutt;
    int d,m,y;
    Spinner trade,sem;
    ArrayList<String> tradelist = new ArrayList<String>();
    ArrayList<String> semlist = new ArrayList<String>();
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_book_frame);

        editTextname = (EditText)findViewById(R.id.nameFissuebookframe);
        editTextrno = (EditText)findViewById(R.id.rnoFissuebookframe);
        editTextphno = (EditText)findViewById(R.id.phnoFissuebookframe);
        editTextbookid = (EditText)findViewById(R.id.bookidFissuebookframe);
        editTextbooktitle = (EditText)findViewById(R.id.booktitleFissuebookframe);
        editTextbookauthor = (EditText)findViewById(R.id.bookauthorFissuebookframe);
        editTextdate = (EditText)findViewById(R.id.issuedateFissuebookframe);

        reset = (Button)findViewById(R.id.resetButtissuebookframe);
        issuebutt = (Button)findViewById(R.id.issueButtissuebookframe);
        spinnerData();
        datePicker();
        onResetClick();
        onIssueClick();
    }

    public void spinnerData(){
        trade = findViewById(R.id.spinner9IBFT);
        sem = findViewById(R.id.spinner10IBFS);

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

        ArrayAdapter<String> tradeAdapter = new ArrayAdapter<String>(
                IssueBookFrame.this,android.R.layout.simple_spinner_dropdown_item,tradelist);
        ArrayAdapter<String> semAdapter = new ArrayAdapter<String>(
                IssueBookFrame.this,android.R.layout.simple_spinner_dropdown_item,semlist);

        trade.setAdapter(tradeAdapter);
        sem.setAdapter(semAdapter);
    }

    public void datePicker(){

        editTextdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Calendar calendar = Calendar.getInstance();
                d=calendar.get(Calendar.DAY_OF_MONTH);
                m=calendar.get(Calendar.MONTH);
                y=calendar.get(Calendar.YEAR);
                DatePickerDialog pickerDialog = new DatePickerDialog(IssueBookFrame.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1+=1;
                        editTextdate.setText(+ i2 + "-" + i1 + "-"+i);
                    }
                }, y, m, d);
                pickerDialog.show();
            }
        });
    }

    public void onResetClick(){
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextname.setText("");
                editTextrno.setText("");
                editTextphno.setText("");
                editTextbookid.setText("");
                editTextbooktitle.setText("");
                editTextbookauthor.setText("");
                editTextdate.setText("");
            }
        });
    }

    public void onIssueClick(){
        issuebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextname.getText().toString();
                String rollno = editTextrno.getText().toString();
                String getTrade = trade.getSelectedItem().toString();
                String getsem = sem.getSelectedItem().toString();
                String phone = editTextphno.getText().toString();
                String bookid = editTextbookid.getText().toString();
                String booktitle = editTextbooktitle.getText().toString();
                String bookauthor = editTextbookauthor.getText().toString();
                String date = editTextdate.getText().toString();

                if(name.equals("") || rollno.equals("")
                        || phone.equals("") || bookid.equals("")
                        || booktitle.equals("") || bookauthor.equals("")
                        || date.equals(""))
                {
                    Toast.makeText(IssueBookFrame.this, "All Fields Required", Toast.LENGTH_LONG).show();
                }else {
                    boolean isInserted =  helper.issueBooks(name,rollno,getTrade,getsem,phone,bookid,booktitle,bookauthor,date);
                    if(isInserted == true)
                        Toast.makeText(IssueBookFrame.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(IssueBookFrame.this, "Data not Inserted", Toast.LENGTH_LONG).show();


                }
            }

        });
    }
}
