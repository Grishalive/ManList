package com.grishalive.manlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class CustomAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Model> objects;

    CustomAdapter(Context context, ArrayList<Model> models) {
        ctx = context;
        objects = models;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item, parent, false);
        }

        Model p = getModel(position);

        ((TextView) view.findViewById(R.id.textViewName)).setText(p.getName());
        ((TextView) view.findViewById(R.id.textViewDate)).setText(p.getDate());
        ((TextView) view.findViewById(R.id.textViewPhone)).setText(p.getPhone());
        return view;
    }

    Model getModel(int position) {
        return ((Model) getItem(position));
    }

    public void add(Model model) {
        objects.add(model);
        Collections.sort(objects);
    }
}
