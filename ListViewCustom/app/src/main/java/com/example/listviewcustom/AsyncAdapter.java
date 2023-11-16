package com.example.listviewcustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AsyncAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contact> list;

    public AsyncAdapter(Context context, ArrayList<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_mer, null);
            viewHolder.tvName = (TextView)view.findViewById(R.id.tvname);
            viewHolder.tvID = (TextView)view.findViewById(R.id.tvid);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.tvName.setText(list.get(i).getName());
        viewHolder.tvID.setText(list.get(i).getId());
        return view;
    }

    public class ViewHolder {
        TextView tvName, tvID;
    }
}
