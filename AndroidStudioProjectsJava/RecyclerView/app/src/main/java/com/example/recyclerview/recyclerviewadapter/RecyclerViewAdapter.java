package com.example.recyclerview.recyclerviewadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerview.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Extend RecyclerView.Adapter
 * Inside choose the ViewHolderClass you created
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter"; // for debugging

    // All the variables needed for the class
    private List<String> mImageNames;
    private List<String> mImagesUrls;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, List<String> mImageNames, List<String> mImagesUrls) {
        this.mImageNames = mImageNames;
        this.mImagesUrls = mImagesUrls;
        this.mContext = mContext;
    }

    /**
     * Method responsible for inflating the view
     * Puts your own layout in the position where it should be
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Method that changes based on what your layouts are and what you want them to look like
     * Method gets called every single time a new item is being added to the list
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        // set image
        Glide.with(mContext)
                .asBitmap()
                .load(mImagesUrls.get(position))
                .into(holder.circleImageView);

        // set text name in textview
        holder.textViewName.setText(mImageNames.get(position));

        // if list item is clicked makes a log and a toast
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Tells teh adapter how many lists items are in your list, if 0 you wont see anything in your list
     * @return
     */
    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    /**
     * ViewHolder Class holds each image + text individually in memory
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView textViewName;
        ConstraintLayout relativeLayout; // used to attach the onClickListener

        public ViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.circleImageView);
            textViewName = itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }
    }


}
