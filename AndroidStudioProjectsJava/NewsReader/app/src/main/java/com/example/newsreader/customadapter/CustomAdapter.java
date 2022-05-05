package com.example.newsreader.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsreader.R;
import com.example.newsreader.WebPages;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<WebPages> webPagesList;

    public CustomAdapter(Context context, List<WebPages> webPagesList) {
        this.inflater = LayoutInflater.from(context);
        this.webPagesList = webPagesList;
    }

    @Override
    public int getCount() {
        return webPagesList.size();
    }

    @Override
    public WebPages getItem(int position) {
        return webPagesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return webPagesList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final WebPages item = getItem(position);

        if(convertView == null) {
            /*
            If convertView is null we have to inflate a new layout:
            --> We have to insert our custom made layout into the parent layout.
            --> Out custom layout is here R.layout.listview_layout.
            --> The parent layout is the ListView.
            --> attachToRoot is false, so it won't be added at start.

            You made a new View, named convertView which is you layout added to the ListView
             */

            convertView = this.inflater.inflate(R.layout.listview_layout, parent, false);

            /*
            Here you make a new ViewHolder object, the ViewHolder holds reference to the TextViews/ImageViews.
            You link the ViewHolder objects to the convertView (=R.layout.listview_layout) which holds your layout with the TextViews/ImageViews
             */

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textViewPage);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageViewPage);

            // We set the view holder as tag of the convertView so we can access the view holder later on.
            // w.o.w. we set the viewHolder as tag for R.layout.listview_layout, so we can access the viewHolder later on
            // we do this because the viewHolder is made inside this if statement to access it later we need a reference to it
            convertView.setTag(viewHolder);
        }

        // Retrieve the view holder from the convertView
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        // Bind the values to the views
        viewHolder.textView.setText(item.getTitle());
        //viewHolder.imageView.setImageResource();

        return convertView;
    }
}
