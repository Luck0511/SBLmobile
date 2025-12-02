package com.itlvck.sblmobile.fragment.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.itlvck.sblmobile.R;
import com.itlvck.sblmobile.activity.MainActivity;
import com.itlvck.sblmobile.dto.ErrorResponse;
import com.itlvck.sblmobile.dto.LoginRequest;
import com.itlvck.sblmobile.dto.LoginResponse;
import com.itlvck.sblmobile.fragment.HomeFragment;
import com.itlvck.sblmobile.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    //Variables
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private TextView goToRegister;

    public LoginFragment() {
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
        goToRegister = view.findViewById(R.id.goToRegister);

        //Click Event management
        //Submit Login (not finished)
        btnLogin.setOnClickListener(v -> loginSubmit());

        // Go to Register
        goToRegister.setOnClickListener(v -> {
            RegisterFragment registerFragment = new RegisterFragment();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.auth_content_container, registerFragment)
                    .addToBackStack(null)
                    .commit();
        });
        return view;
    }

    //Creating the logic for the login submit (not finished)
    private void loginSubmit() {
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

        // Disable btnLogin during the login process
        btnLogin.setEnabled(false);
        Toast.makeText(getActivity(), "Accesso in corso...", Toast.LENGTH_SHORT).show();

        //Create the request
        LoginRequest request = new LoginRequest(logUsername, logPassword);

        RetrofitClient.getInstance()
                .getApiService()
                .login(request)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        btnLogin.setEnabled(true);

                        if (response.isSuccessful() && response.body() != null) {
                            // Success (200)
                            LoginResponse loginResponse = response.body();
                            Toast.makeText(getActivity(), "Bentornato " + logUsername + "!", Toast.LENGTH_SHORT).show();

                            // Method to navigate to the Main app content
                            navigateToMainApp();

                        } else {
                            // Error (401, 500, ecc.)
                            handleError(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        btnLogin.setEnabled(true);
                        Toast.makeText(getActivity(), "Errore di connessione: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void handleError(Response<LoginResponse> response) {
        try {
            if (response.errorBody() != null) {
                String errorJson = response.errorBody().string();
                // Assuming you have an ErrorResponse DTO similar to the RegisterFragment's structure
                ErrorResponse error = new Gson().fromJson(errorJson, ErrorResponse.class);

                switch (response.code()) {
                    case 401:
                        showToast("Credenziali non valide. Riprova."); // Common for Authentication Failed
                        break;
                    case 400:
                        showToast("" + error.getMessage()); // "Username and password are required"
                        break;
                    case 500:
                        showToast("" + error.getMessage()); // "There has been an internal server error"
                        break;
                    default:
                        showToast("Errore: " + error.getMessage());
                        break;
                }
            } else {
                showToast("Errore sconosciuto: " + response.code());
            }
        } catch (Exception e) {
            showToast("Errore nella risposta del server");
        }
    }

    private void navigateToMainApp() {
        Intent homeFragment = new Intent(getContext(), MainActivity.class);
        startActivity(homeFragment);
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

}


