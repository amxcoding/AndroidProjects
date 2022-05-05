package com.example.datebasedemo.listviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.datebasedemo.Item;
import com.example.datebasedemo.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<Item> items;

    public CustomAdapter(Context context, List<Item> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Item item = (Item) getItem(position);

        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.recyclerview_layout, parent, false);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.textViewName = convertView.findViewById(R.id.task_name); // the textView in custom layout
            viewHolder.textViewDescription = convertView.findViewById(R.id.task_desc);

            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.textViewName.setText(item.getName());
        viewHolder.textViewDescription.setText(item.getDescription());

        return convertView;
    }
}
