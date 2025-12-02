package com.itlvck.sblmobile.dto;

import android.widget.EditText;

public class LoginRequest {
    private EditText userName;
    private EditText password;

    public LoginRequest(EditText userName, EditText password) {
        this.userName = userName;
        this.password = password;
    }
}
