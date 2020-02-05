package com.example.android.librarymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class IssuedBooksButton extends AppCompatActivity {

    Spinner trade,sem;
    ArrayList<String> tradelist = new ArrayList<String>();
    ArrayList<String> semlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issued_books_button);


        trade = findViewById(R.id.spinner15IBBT);
        sem = findViewById(R.id.spinner16IBBS);

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
                IssuedBooksButton.this,android.R.layout.simple_spinner_dropdown_item,tradelist);
        ArrayAdapter<String> semadapter = new ArrayAdapter<String>(
                IssuedBooksButton.this,android.R.layout.simple_spinner_dropdown_item,semlist);

        trade.setAdapter(tradeadapter);
        sem.setAdapter(semadapter);
    }
}
