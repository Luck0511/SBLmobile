package com.itlvck.sblmobile.dto;

import android.widget.EditText;

public class RegisterRequest {

    private String userName;
        private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisterRequest(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
