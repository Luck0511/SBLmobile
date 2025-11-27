package com.itlvck.sblmobile.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.itlvck.sblmobile.R;
import com.itlvck.sblmobile.fragment.auth.LoginFragment;


public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.auth_content_container, new LoginFragment())
                    .commit();
        }
    }
}

