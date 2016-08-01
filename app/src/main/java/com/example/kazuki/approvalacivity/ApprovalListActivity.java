package com.example.kazuki.approvalacivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ApprovalListActivity extends AppCompatActivity {

    private ArrayList<ToBeApplovalListDO> mlist = null;
    private ToBeApplovalListAdapter mAdapter = null;
    private ToBeApplovalListDO mToBeApplovalListDO = null;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_list);

        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new ToBeApplovalListAdapter(ApprovalListActivity.this);

        new GetUserListTask().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int fPosition = position;
                if (!((mlist.get(fPosition)).is_bApproval())) {
                    new AlertDialog.Builder(ApprovalListActivity.this)
                            .setTitle("Check")
                            .setMessage("Will you approve it?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mToBeApplovalListDO = mlist.get(fPosition);
                                    mToBeApplovalListDO.set_bApproval(true);
                                    new updateToBeApplovalListDO().execute();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                } else {
                    Toast.makeText(ApprovalListActivity.this, "Apploved",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    //  Get userList from DynamoDB
    private class GetUserListTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... inputs) {

            mlist = DynamoDBManager.getUserList(ApprovalListActivity.this);

            return null;
        }

        protected void onPostExecute(Void result) {

            if (mlist == null) {
                return;
            }
            mAdapter.setTOBeApplovalList(mlist);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class updateToBeApplovalListDO extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... inputs) {

            DynamoDBManager.updateToBeApplovalListDO(mToBeApplovalListDO);

            return null;
        }

        protected void onPostExecute(Void result) {
            mAdapter.setTOBeApplovalList(mlist);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            Toast.makeText(ApprovalListActivity.this, "Succeed", Toast.LENGTH_LONG).show();


        }
    }
}
