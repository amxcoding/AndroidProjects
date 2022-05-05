package com.example.stayactive.fragments.alert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.stayactive.R;
import com.example.stayactive.adapter.AlertAdapter;
import com.example.stayactive.databinding.FragmentAlertsBinding;
import com.example.stayactive.model.Alert;
import com.example.stayactive.model.Exercise;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AlertsFragment extends Fragment {
    private FragmentAlertsBinding binding;
    private static final String TAG = "AlertsFragment"; // used for debugging

    public AlertsFragment() {
        super(R.layout.fragment_alerts);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAlertsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onFABClicked();
        initRecyclerViewAlert();
    }

    /*
    This method navigates to the create a new alert fragment.
     */
    private void onFABClicked() {
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = AlertsFragmentDirections.actionAlertsfragmentToAddalertfragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private void initRecyclerViewAlert() {
        AlertAdapter alertAdapter = new AlertAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        List<Alert> alerts = new ArrayList<>();

        alerts.add(new Alert(1, "alert1", LocalTime.now(), LocalTime.now(), LocalTime.now(), new boolean[7], new ArrayList<Exercise>(), false, 3, true));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        alertAdapter.submitList(alerts);

        binding.alertsRecyclerView.setAdapter(alertAdapter);
        binding.alertsRecyclerView.setLayoutManager(linearLayoutManager);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}