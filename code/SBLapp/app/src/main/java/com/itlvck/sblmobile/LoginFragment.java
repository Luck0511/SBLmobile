package com.itlvck.sblmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

class LoginFragment extends Fragment {

    //Variables
    private EditText username;
    private EditText password;
    private Button btnLogin;

    public LoginFragment(){

    }

    @Nullable
    @Override
     public View onCreate(@NonNull LayoutInflater inflater,
                          @Nullable ViewGroup container,
                          @Nullable Bundle savedInstanceState) {

        //Here it goes the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //Initialisation variables
        username = view.findViewById(R.id.logUsername);
        password = view.findViewById(R.id.logPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        //Click Event management
        btnLogin.setOnClickListener(v -> loginSubmit());

        return view;
    }

    //Creating the logic for the login submit
    private void loginSubmit(){
        String logUsername = username.getText().toString().trim();
        String logPassword = password.getText().toString();

        if (logUsername.isEmpty()) {
            username.setError("Inserisci lo username");
            return;
        }

        if (logUsername.isEmpty()) {
            password.setError("Inserisci la password");
            return;
        }

    }
}

