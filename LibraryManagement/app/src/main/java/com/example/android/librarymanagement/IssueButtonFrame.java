package com.example.android.librarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IssueButtonFrame extends AppCompatActivity {

    public Button issuebookbutt,reissuebookbutt;

    public void init() {
        issuebookbutt = (Button) findViewById(R.id.issuebuttIssuebookframe);
        issuebookbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(IssueButtonFrame.this,IssueBookFrame.class);
                startActivity(i);

            }
        });

        reissuebookbutt = (Button) findViewById(R.id.reissuebuttIssuebuttframe);
        reissuebookbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(IssueButtonFrame.this,reissueFrame.class);
                startActivity(i);

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_button_frame);
        init();
    }
}
