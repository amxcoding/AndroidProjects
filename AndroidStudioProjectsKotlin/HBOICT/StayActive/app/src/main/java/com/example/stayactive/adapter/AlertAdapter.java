package com.example.stayactive.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stayactive.databinding.AlertItemLayoutBinding;
import com.example.stayactive.model.Alert;

import java.util.List;

/*
This class is used to fill in the recyclerview for the alerts.
This is the list of active alerts.
 */
public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {
    private static final String TAG = "AlertAdapter";

    /*
    Code below used for filling item in recyclerview
     */
    public static final DiffUtil.ItemCallback<Alert> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<Alert>() {
        @Override
        public boolean areItemsTheSame(
                @NonNull Alert oldAlert, @NonNull Alert newAlert) {
            return oldAlert.getAlertId() == newAlert.getAlertId();
        }

        @Override
        public boolean areContentsTheSame(
                @NonNull Alert oldAlert, @NonNull Alert newAlert) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldAlert.equals(newAlert);
        }
    };

    private final AsyncListDiffer<Alert> listDiffer = new AsyncListDiffer<Alert>(this, DIFF_CALLBACK);

    /*
    Function inflates the recyclerview items
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: " + listDiffer.getCurrentList().size());
        return new ViewHolder(AlertItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent,false
        ));
    }

    /*
    Function used to get the recyclerview item views like the textview and switch
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alert currentAlert = listDiffer.getCurrentList().get(position);
        holder.binding.tvAlertName.setText(currentAlert.getName());

    }

    /*
    Check the recyclerview list size
     */
    @Override
    public int getItemCount() {
        return listDiffer.getCurrentList().size();
    }

    public void submitList(List<Alert> list) {
        listDiffer.submitList(list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AlertItemLayoutBinding binding;

        public ViewHolder(AlertItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
