package com.example.notes.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.models.Note;
import com.example.notes.util.Utility;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    private static final String TAG = "NotesRecyclerAdapter";
    private ArrayList<Note> mNotes;
    private OnNoteListener mOnNoteListener;

    public NotesRecyclerAdapter(ArrayList<Note> notes, OnNoteListener onNoteListener) {
        this.mNotes = notes;
        this.mOnNoteListener = onNoteListener;
        Log.d(TAG, "NotesRecyclerAdapter: " + mNotes.size());
    }

    /**
     * Method responsible for creating/instantiating ViewHolder object
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    /**
     * Called for every single entry
     */
    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerAdapter.ViewHolder holder, int position) {

        try {
            String month = mNotes.get(position).getTimestamp().substring(0, 2); // exclusief 2
            month = Utility.getMonthFromNumber(month);
            String year = mNotes.get(position).getTimestamp().substring(3); // from 3d to end
            String timeStamp = month + " " + year;

            holder.timestamp.setText(timeStamp);
            holder.title.setText(mNotes.get(position).getTitle());
        } catch (NullPointerException e) {
            Log.d(TAG, "onBindViewHolder: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, timestamp;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            this.onNoteListener = onNoteListener;

            title = itemView.findViewById(R.id.note_title);
            timestamp = itemView.findViewById(R.id.note_timestamp);

            // attach onClickListener to entire ViewHolder
            itemView.setOnClickListener(this); // refers to the interface View.OnClickListener
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    /**
     * Use interface to detect the click and use the method in the activity to send the position of the clicked item
     */
    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
