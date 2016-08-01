package com.example.kazuki.approvalacivity;

import android.content.Context;
import android.util.Log;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/07/14.
 */
public class DynamoDBManager {

        private static final String TAG = "DynamoDBManager";
        /*
         * Retrieves the table description and returns the table status as a string.
         */
        public static String getTestTableStatus() {

            try {
                AmazonDynamoDBClient ddb = MainActivity.clientManager
                        .ddb();

                DescribeTableRequest request = new DescribeTableRequest()
                        .withTableName(AWSConfiguration.TEST_TABLE_NAME);
                DescribeTableResult result = ddb.describeTable(request);

                String status = result.getTable().getTableStatus();
                return status == null ? "" : status;

            } catch (ResourceNotFoundException e) {
            } catch (AmazonServiceException ex) {

            }

            return "";
        }

        /*
         * Inserts ten users with userNo from 1 to 10 and random names.
         */
        public static String insertUsers(ToBeApplovalListDO newToBeApplovalListDO) {
            AmazonDynamoDBClient ddb = MainActivity.clientManager
                    .ddb();
            DynamoDBMapper mapper = new DynamoDBMapper(ddb);
            String sRet = "Success";

            try {
                    Log.d(TAG, "Inserting users");
                    mapper.save(newToBeApplovalListDO);
                    Log.d(TAG, "Users inserted");

            } catch (AmazonServiceException ex) {
                Log.e(TAG, "Error inserting users");
                sRet = "False";
            }
            return sRet;
        }

        /*
         * Scans the table and returns the list of users.
         */
        public static ArrayList<ToBeApplovalListDO> getUserList(Context context) {

            AmazonClientManager clientManager = new AmazonClientManager(context);
            ClientConfiguration clientConfiguration = new ClientConfiguration();
            CognitoCachingCredentialsProvider credentialsProvider =
            new CognitoCachingCredentialsProvider(context,
                    AWSConfiguration.IDENTITY_POOL_ID,
                    Regions.US_EAST_1,
                    clientConfiguration
            );

            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);

            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);

            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            try {
                PaginatedScanList<ToBeApplovalListDO> result = mapper.scan(
                        ToBeApplovalListDO.class, scanExpression);

                ArrayList<ToBeApplovalListDO> resultList = new ArrayList<ToBeApplovalListDO>();
                for (ToBeApplovalListDO up : result) {
                    resultList.add(up);
                }
                return resultList;

            } catch (AmazonServiceException ex) {
                Log.e(TAG, "getUserList");
            }

            return null;
        }

        /*
         * Retrieves all of the attribute/value pairs for the specified user.
         */
        public static ToBeApplovalListDO getToBeApplovalListDO(String userName) {

            AmazonDynamoDBClient ddb = MainActivity.clientManager
                    .ddb();
            DynamoDBMapper mapper = new DynamoDBMapper(ddb);

            try {
                ToBeApplovalListDO ToBeApplovalListDO = mapper.load(ToBeApplovalListDO.class,
                        userName);

                return ToBeApplovalListDO;

            } catch (AmazonServiceException ex) {
                Log.e(TAG, "getToBeApplovalListDO");
            }

            return null;
        }

        /*
         * Updates one attribute/value pair for the specified user.
         */
        public static void updateToBeApplovalListDO(ToBeApplovalListDO toBeApplovalListDO) {

            AmazonDynamoDBClient ddb = MainActivity.clientManager
                    .ddb();
            DynamoDBMapper mapper = new DynamoDBMapper(ddb);

            try {
                mapper.save(toBeApplovalListDO);

            } catch (AmazonServiceException ex) {
            }
        }

}
