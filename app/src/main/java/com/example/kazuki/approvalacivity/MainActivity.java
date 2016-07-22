package com.example.kazuki.approvalacivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static AmazonClientManager clientManager = null;
    private Button mDirectorBtn;
    private Button mUserRequestBtn;
    private Button mUserCheckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientManager = new AmazonClientManager(this);
        mDirectorBtn = (Button)findViewById(R.id.directorbtn);
        mDirectorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ApprovalListActivity.class);
                startActivity(intent);
            }
        });

        mUserRequestBtn = (Button)findViewById(R.id.UserRequestBtn);
        mUserRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ApplovalRequestActivity.class);
                startActivity(intent);
            }
        });

        mUserCheckBtn = (Button)findViewById(R.id.UserCheckBtn);
        mUserCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckingStatusActivity.class);
                startActivity(intent);
            }
        });



    }
}
