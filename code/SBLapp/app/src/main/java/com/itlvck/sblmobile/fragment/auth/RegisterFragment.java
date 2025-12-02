
package com.itlvck.sblmobile.fragment.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.itlvck.sblmobile.R;
import com.itlvck.sblmobile.dto.ErrorResponse;
import com.itlvck.sblmobile.dto.RegisterRequest;
import com.itlvck.sblmobile.dto.RegisterResponse;
import com.itlvck.sblmobile.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    //Variables
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private Button btnRegister;
    private TextView goToLogin;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        //Here it goes the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        //Initialisation variables
        username = view.findViewById(R.id.registerUsername);
        password = view.findViewById(R.id.registerPassword);
        confirmPassword = view.findViewById(R.id.registerConfirmPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        goToLogin = view.findViewById(R.id.goToLogin);

        //Click Event management
        //Submit Registration (not finished)
        btnRegister.setOnClickListener(v -> registrationSubmit());

        // Go to Login (finished)
        goToLogin.setOnClickListener(v -> {
            LoginFragment loginFragment = new LoginFragment();

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.auth_content_container, loginFragment)
                    .addToBackStack(null)
                    .commit();
        });
        return view;
    }

    //Creating the logic for the Submit Registration
    private void registrationSubmit() {
        String usernametxt = username.getText().toString().trim();
        String passwordtxt = password.getText().toString().trim();
        String confirmPasswordtxt = confirmPassword.getText().toString().trim();

        //Check for empty inputs
        if (usernametxt.isEmpty() || passwordtxt.isEmpty() || confirmPasswordtxt.isEmpty()) {
            Toast.makeText(getActivity(), "Compilare tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }
        //Check that the passwords match
        if (!passwordtxt.equals(confirmPasswordtxt)) {
            Toast.makeText(getActivity(), "ATTENZIONE! Le password non coincidono.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Disable btnRegister during the registration
        btnRegister.setEnabled(false);
        Toast.makeText(getActivity(), "Registrazione in corso...", Toast.LENGTH_SHORT).show();


        //Create the request
        RegisterRequest request = new RegisterRequest(username, password);

        RetrofitClient.getInstance()
                .getApiService()
                .register(request)
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        btnRegister.setEnabled(true);

                        if (response.isSuccessful() && response.body() != null) {
                            // Success (200)
                            RegisterResponse registerResponse = response.body();
                            Toast.makeText(getActivity(), "Benvenuto " + registerResponse.getUserInfo().getUserName() + "!", Toast.LENGTH_SHORT).show();

                            // save User ID
                            saveUserId(registerResponse.getUserInfo().getId());

                            //Method to navigate to Login
                            navigateToLogin();

                        } else {
                            // Errore (400, 409, 500, ecc.)
                            handleError(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        btnRegister.setEnabled(true);
                        Toast.makeText(getActivity(), "Errore di connessione: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void handleError(Response<RegisterResponse> response) {
        try {
            if (response.errorBody() != null) {
                String errorJson = response.errorBody().string();
                ErrorResponse error = new Gson().fromJson(errorJson, ErrorResponse.class);

                switch (response.code()) {
                    case 400:
                        Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show(); // "User name and password are required"
                        break;
                    case 409:
                        Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show(); // "An User with this name already exists"
                        break;
                    case 500:
                        Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show(); // "There has been an internal server error"
                        break;
                    default:
                        Toast.makeText(getActivity(), "Errore: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }
            } else {
                showToast("Errore sconosciuto: " + response.code());
            }
        } catch (Exception e) {
            showToast("Errore nella risposta del server");
        }
    }

    private void saveUserId(int userId) {
        requireActivity().getSharedPreferences("UserPrefs", 0)
                .edit()
                .putInt("userId", userId)
                .apply();
    }

    private void navigateToLogin() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.auth_content_container, loginFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

}

