package com.example.kazuki.approvalacivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CheckingStatusActivity extends AppCompatActivity {

    private Button mBtn;
    private EditText mEditUserNameView;
    private String mUserName = null;
    private ToBeApplovalListDO mToBeApplovalListDO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_status);

        mBtn = (Button)findViewById(R.id.CheckStart);
        mEditUserNameView = (EditText)findViewById(R.id.EditUserName);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName = mEditUserNameView.getText().toString();
                new getToBeApplovalListDOTask().execute();
            }
        });
        mEditUserNameView.setFocusable(true);
    }

    private class getToBeApplovalListDOTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... voids) {
            if (!mUserName.isEmpty()){
                mToBeApplovalListDO = DynamoDBManager.getToBeApplovalListDO(mUserName);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mToBeApplovalListDO == null){
                return;
            }
            ((TextView)findViewById(R.id.UserName)).setText(mToBeApplovalListDO.getUserName());
            ((TextView)findViewById(R.id.ParentsName)).setText(mToBeApplovalListDO.getParentsName());
            ((TextView)findViewById(R.id.StudentsName)).setText(mToBeApplovalListDO.getStudentsName());
            ((TextView)findViewById(R.id.date)).setText(mToBeApplovalListDO.getStudentsName());
            if (mToBeApplovalListDO.is_bApproval()) {
                ((TextView)findViewById(R.id.Status)).setText("Approval");
            } else  {
                ((TextView)findViewById(R.id.Status)).setText("Non Approval");
            }
        }
    }
}
