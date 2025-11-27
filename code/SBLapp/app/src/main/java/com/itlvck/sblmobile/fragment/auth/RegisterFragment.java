
package com.itlvck.sblmobile.fragment.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.itlvck.sblmobile.R;

public class RegisterFragment extends Fragment {
    //Variables
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private Button btnRegister;

    public RegisterFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Here it goes the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        //Initialisation variables
        username=view.findViewById(R.id.registerUsername);
        password=view.findViewById(R.id.registerPassword);
        confirmPassword=view.findViewById(R.id.registerConfirmPassword);
        btnRegister=view.findViewById(R.id.btnRegister);

        //Click Event management (not finished)
        btnRegister.setOnClickListener(v -> registrationSubmit());

        return view;
    }

    //Creating the logic for the register submit (not finished)
    private void registrationSubmit(){
        String usernametxt=username.getText().toString().trim();
        String passwordtxt=password.getText().toString().trim();
        String confirmPasswordtxt=confirmPassword.getText().toString().trim();

        //Check for empty inputs
        if (usernametxt.isEmpty()|| passwordtxt.isEmpty() || confirmPasswordtxt.isEmpty()){
            Toast.makeText(getActivity(), "Compilare tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }
        //Check that the passwords match
        if (!passwordtxt.equals(confirmPasswordtxt)){
            Toast.makeText(getActivity(), "ATTENZIONE! Le password non coincidono.", Toast.LENGTH_SHORT).show();
            return;
        }

        //If all succeded
        Toast.makeText(getActivity(), "Registrazione riuscita per: " + usernametxt, Toast.LENGTH_LONG).show();
    }

}
