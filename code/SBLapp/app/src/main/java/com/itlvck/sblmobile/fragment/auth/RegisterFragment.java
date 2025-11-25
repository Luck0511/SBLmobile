/*
package com.itlvck.sblmobile.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.itlvck.sblmobile.R;

public class RegisterFragment extends Fragment {
    //Variables
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_register);

        //Initialisation variables
        username=findViewById(R.id.registerUsername);
        password=findViewById(R.id.registerPassword);
        confirmPassword=findViewById(R.id.registerConfirmPassword);
        btnRegister=findViewById(R.id.btnRegister);

        //Click Event management
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationSubmit();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Creating the logic for the register submit
    private void registrationSubmit(){
        String usernametxt=username.getText().toString().trim();
        String passwordtxt=password.getText().toString().trim();
        String confirmPasswordtxt=confirmPassword.getText().toString().trim();

        //Check for empty inputs
        if (usernametxt.isEmpty()|| passwordtxt.isEmpty() || confirmPasswordtxt.isEmpty()){
            Toast.makeText(this, "Compilare tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }
        //Check that the passwords match
        if (!passwordtxt.equals(confirmPasswordtxt)){
            Toast.makeText(this, "ATTENZIONE! Le password non coincidono.", Toast.LENGTH_SHORT).show();
            return;
        }

        //If all succeded
        Toast.makeText(this, "Registrazione riuscita per: " + usernametxt, Toast.LENGTH_LONG).show();
    }

}
*/