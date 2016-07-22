package com.example.kazuki.approvalacivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kazuki on 2016/07/15.
 */
public class ToBeApplovalListAdapter extends BaseAdapter{

    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<ToBeApplovalListDO> ToBeApplovalListDOList;

    public ToBeApplovalListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setTOBeApplovalList(ArrayList<ToBeApplovalListDO> toBeApplovalListDOList) {
        this.ToBeApplovalListDOList = toBeApplovalListDOList;
    }

    @Override
    public int getCount() {
        return ToBeApplovalListDOList.size();
    }

    @Override
    public Object getItem(int position) {
        return ToBeApplovalListDOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ToBeApplovalListDOList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.tobeapplovallist_row,parent,false);

        ((TextView)convertView.findViewById(R.id.Date)).setText(ToBeApplovalListDOList.get(position).getDate());
        ((TextView)convertView.findViewById(R.id.StudentsName)).setText(ToBeApplovalListDOList.get(position).getStudentsName());
        ((TextView)convertView.findViewById(R.id.ParentsName)).setText(ToBeApplovalListDOList.get(position).getDate());

        TextView textView = ((TextView)convertView.findViewById(R.id.approvalStatus));

        if (ToBeApplovalListDOList.get(position).is_bApproval()) {
            textView.setText("Apprpval");
            textView.setBackgroundColor(0x880000FF);
        } else {
            textView.setText("Non Apprpval");
            textView.setBackgroundColor(0x88FF0000);
        }
        return convertView;
    }
}
