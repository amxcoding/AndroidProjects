package com.example.stayactive;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.stayactive.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
        The code below is used to setup the bottom navigation bar.
         */
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_container_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationBar, navController);

    }

    /*
    Method below set the text color to purple and back.
    Used when selecting the repeat days.
     */
    public void setTextColorOnClick(View view) {
        TextView textView = (TextView) view;

        if (textView.getTag() == null) {
            textView.setTag("false");
        }

        String tag = textView.getTag().toString();

        if (tag.equals("true")) {
            textView.setTag("false");
            TextView otherTV = findViewById(R.id.tvTitleSetTime);
            textView.setTextColor(otherTV.getTextColors()); // set back to white/ black depending on theme
        } else {
            textView.setTag("true");
            TextView otherTV = findViewById(R.id.tvUsedForColoring);
            textView.setTextColor(otherTV.getTextColors());
        }
    }

}