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

import com.example.stayactive.R;
import com.example.stayactive.databinding.FragmentAddAlertBinding;

public class AddAlertFragment extends Fragment {
    private static final String TAG = "AddAlertFragment";
    /*
    View binding is used to easily access buttons or text views.
     */
    private FragmentAddAlertBinding binding;

    /*
    In the super I am inflating the fragment screen/view
     */
    public AddAlertFragment() {
        super(R.layout.fragment_add_alert);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddAlertBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /*
    After the view is created I can setup on click listeners etc.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBtnChooseClicked();
//        setTextColorOnClick(view);
    }

    /*
    The method below is used to navigate to the exercises fragment
     */
    private void onBtnChooseClicked() {
        binding.btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = AddAlertFragmentDirections.actionAddalertfragmentToExercisesfragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

//    public void setTextColorOnClick(View view) {
//        CharSequence text = "" + view.getId();
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(getActivity(), text, duration);
//        toast.show();
//        if (view.getId() == R.id.tvMonday) {
//            TextView textView = (TextView) view;
////            textView.setTextColor(Color.parseColor("@color/design_default_color_primary"));
//            textView.setTextColor(Color.parseColor("#FF6200EE"));
//        }
//    }

    /*
    The method below is called when the fragment closes.
    Binding is set to null to avoid memory leaks because a view (e.g. a button) is still accessed.
    Note: If mainactivity gets destroyed because the app gets closed. Everything will be deleted from
    the memory. So no need to set binding to null in mainactivity.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}