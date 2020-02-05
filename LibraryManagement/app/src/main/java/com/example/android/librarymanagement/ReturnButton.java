package com.example.android.librarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReturnButton extends AppCompatActivity {

    public Button returnbookbutt,finebutt;

    public void init(){

        returnbookbutt = (Button) findViewById(R.id.returnbookbuttreturnbookframe);
        returnbookbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ReturnButton.this,ReturnBooks.class);
                startActivity(i);

            }
        });

        finebutt = (Button) findViewById(R.id.finebuttreturnbookframe);
        finebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ReturnButton.this,Fine.class);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_button);
        init();
    }
}
