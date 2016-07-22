package com.example.kazuki.approvalacivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.retry.RetryUtils;

public class ApplovalRequestActivity extends AppCompatActivity {

    private EditText userNameTextView;
    private EditText studentsNameTextView;
    private EditText parentsNameTextView;
    private Button registerBtn;
    private DatePicker datePicker;
    private ToBeApplovalListDO mToBeApplovalListDO;
    private String sInsertResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apploval_request);

        userNameTextView = (EditText)findViewById(R.id.UserName);
        studentsNameTextView = (EditText)findViewById(R.id.StudentsName);
        parentsNameTextView = (EditText)findViewById(R.id.ParentsName);
        registerBtn = (Button)findViewById(R.id.login);

        // ContextでDatePickerを作成
        datePicker = (DatePicker)findViewById(R.id.datrPick);

        mToBeApplovalListDO = new ToBeApplovalListDO();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpApprovalRequest();
    }

    private void setUpApprovalRequest() {

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToBeApplovalListDO.setUserName(userNameTextView.getText().toString());
                mToBeApplovalListDO.setStudentsName(studentsNameTextView.getText().toString());
                mToBeApplovalListDO.setParentsName(parentsNameTextView.getText().toString());
                mToBeApplovalListDO.setDate(String.format("%d/%d/%d",datePicker.getYear(), datePicker.getMonth() +1 , datePicker.getDayOfMonth()));;
                new insertUsersTask().execute();
            }
        });
    }
    private class insertUsersTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... voids) {
            sInsertResult = DynamoDBManager.insertUsers(mToBeApplovalListDO);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),sInsertResult, Toast.LENGTH_LONG).show();
        }
    }
}
