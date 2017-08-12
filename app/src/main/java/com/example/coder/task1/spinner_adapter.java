package com.example.coder.task1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mohamed Abd Elaziz on 8/11/2017.
 */

public class spinner_adapter extends BaseAdapter {
    private List<String> objects ;
    private Context context;
    public spinner_adapter(List<String> objects, Context context) {
        this.objects = objects;
        this.context = context;
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
    public View getView(int position, View view, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    view=inflater.inflate(R.layout.spinner_list_item,null) ;
    TextView textView = (TextView) view.findViewById(R.id.spinnerTarget);
     textView.setText(objects.get(position));
            return view ;
        }

}
