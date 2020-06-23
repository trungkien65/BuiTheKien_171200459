package com.example.buithekien_171200459;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class    CustomAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private ArrayList<Song> personArrayList;
    private ArrayList<Song> filterList;
    private CustomFilter filter;

    public CustomAdapter(Context context, ArrayList<Song> personArrayList) {
        this.context = context;
        this.personArrayList = personArrayList;
        this.filterList = personArrayList;
    }

    @Override
    public int getCount() {
        return personArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return personArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.activity_main2, null);
        }
        Song person = (Song) getItem(position);
        if (person != null) {
            TextView textView = (TextView) view.findViewById(R.id.textView);
            TextView textView2 = (TextView) view.findViewById(R.id.textView2);
            TextView textView3 = (TextView) view.findViewById(R.id.textView3);
            textView.setText(person.getName_song());
            textView2.setText(person.getName_singer());
            textView3.setText(person.getTime());
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();
                ArrayList<Song> filters = new ArrayList<>();
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getName_singer().toUpperCase().contains(constraint)) {
                        Song person = new Song(filterList.get(i).getId(), filterList.get(i).getName_song(), filterList.get(i).getName_singer(), filterList.get(i).getTime());
                        filters.add(person);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            personArrayList = (ArrayList<Song>) results.values;
            notifyDataSetChanged();
        }
    }
}
