package com.example.stayactive.fragments.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.stayactive.MainActivity;
import com.example.stayactive.R;
import com.example.stayactive.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onLoginClicked();
        onCreateAccountClicked();
        onForgotPasswordClicked();
    }

    private void onLoginClicked() {
        binding.btnLogin.setOnClickListener(view -> {
            // TODO: putExtra() -> logged in user as parcelable or logged in user id
            Intent mainActivityIntent = new Intent(getActivity(),MainActivity.class);
//            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainActivityIntent);
        });
    }


    private void onCreateAccountClicked() {
        binding.tvNewAccount.setOnClickListener(view -> {
            NavDirections action = LoginFragmentDirections.actionLoginfragmentToRegisterfragment();
            Navigation.findNavController(view).navigate(action);
        });
    }

    private void onForgotPasswordClicked() {
        binding.tvForgotPassword.setOnClickListener(view -> {
            NavDirections action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment();
            Navigation.findNavController(view).navigate(action);
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}