package com.example.notesappchallenges;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final List<Note> notes;

    public CustomAdapter(Context context, List<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notes.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Note item = getItem(position);

        if(convertView == null) {
            /*
            If convertView is null we have to inflate a new layout:
            --> We have to insert our custom made layout into the parent layout.
            --> Out custom layout is here R.layout.notes_layout.
            --> The parent layout is ViewGroup parent, this is the ListView.
            --> attachToRoor is false, so it won't be added at start.

            You made a new View, named convertView which is you layout added to the ListView
             */

            convertView = this.inflater.inflate(R.layout.notes_layout, parent, false);

            /*
            Here you make a new ViewHolder object, the ViewHolder holds reference to the TextViews.
            You link the ViewHolder objects to the convertView which holds your layout with two TextViews
            Here you can also link an ImageView if you have one
             */

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.textViewNote = (TextView) convertView.findViewById(R.id.note);

            // We set the view holder as tag of the convertView so we can access the view holder later on.
            convertView.setTag(viewHolder);
        }

        // Retrieve the view holder from the convertView
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        // Bind the values to the views
        viewHolder.textViewName.setText(item.getNoteName());
        viewHolder.textViewNote.setText(item.getNoteText());
//
//        convertView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Alert.alertDelete();
//                return false;
//            }
//        });
//
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AppContext.getContext(), "pressed", Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }
}
