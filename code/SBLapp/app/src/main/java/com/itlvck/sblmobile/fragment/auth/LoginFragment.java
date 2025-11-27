package com.itlvck.sblmobile.fragment.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.itlvck.sblmobile.R;

public class LoginFragment extends Fragment {

    //Variables
    private EditText username;
    private EditText password;
    private Button btnLogin;

    public LoginFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        //Here it goes the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //Initialisation variables
        username = view.findViewById(R.id.logUsername);
        password = view.findViewById(R.id.logPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        //Click Event management (not finished)
        btnLogin.setOnClickListener(v -> loginSubmit());

        return view;
    }

    //Creating the logic for the login submit (not finished)
    private void loginSubmit(){
        String logUsername = username.getText().toString().trim();
        String logPassword = password.getText().toString();

        if (logUsername.isEmpty()) {
            username.setError("Inserisci lo username");
            return;
        }

        if (logPassword.isEmpty()) {
            password.setError("Inserisci la password");
            return;
        }

    }
}

