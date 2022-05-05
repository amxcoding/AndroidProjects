package com.example.stayactive;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stayactive.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    /*
    View binding is used to easily access buttons or text views.
     */
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    /*
    This methode is called when another activity starts.
    The method finish() I am calling when the user logs successfully in.
    This will result in that the user will not be able to go back to the login screen.
     */
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    /*
    This method is called when the app closes or gets destroyed manually by calling finish()
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}