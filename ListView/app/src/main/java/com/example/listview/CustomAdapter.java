package com.example.listview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<cauthu> ds; //data source of the list adapter

    private CustomAdapter filter;
    private ArrayAdapter<cauthu> filterList;

    public CustomAdapter(Context context, ArrayList<cauthu> ds) {
        this.context = context;
        this.ds = ds;
        this.filterList=ds;

    }

    @Override
    public int getCount() {
        return dsSinhVien.size();
    }

    @Override
    public Object getItem(int position) {
        return dsSinhVien.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view =  inflater.inflate(R.layout.activity_activitydongsinhvien, null);
        }
        SinhVien p = (SinhVien) getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txt1 = (TextView) view.findViewById(R.id.textViewHoTen);
            txt1.setText(p.getHoten());

        }
        return view;
    }
}
}