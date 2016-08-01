package com.example.kazuki.approvalacivity;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by kazuki on 2016/07/14.
 */

@DynamoDBTable(tableName = "teataws-mobilehub-373292042-toBeApplovalList")
public class ToBeApplovalListDO {
    private int _id;
    private String _userName;
    private String _date;
    private String _parentsName;
    private String _studentsName;
    private boolean _bApproval;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @DynamoDBHashKey(attributeName = "userName")
    @DynamoDBAttribute(attributeName = "userName")
    public String getUserName() {
        return _userName;
    }

    public void setUserName(final String _userName) {
        this._userName = _userName;
    }
    @DynamoDBAttribute(attributeName = "date")
    public String getDate() {
        return _date;
    }

    public void setDate(final String _date) {
        this._date = _date;
    }
    @DynamoDBAttribute(attributeName = "parentsName")
    public String getParentsName() {
        return _parentsName;
    }

    public void setParentsName(final String _parentsName) {
        this._parentsName = _parentsName;
    }
    @DynamoDBAttribute(attributeName = "studentsName")
    public String getStudentsName() {
        return _studentsName;
    }

    public void setStudentsName(final String _studentsName) {
        this._studentsName = _studentsName;
    }

    @DynamoDBAttribute(attributeName = "approvaled")
    public boolean is_bApproval() {
        return _bApproval;
    }
    public void set_bApproval(boolean _bApproval) {
        this._bApproval = _bApproval;
    }
}

