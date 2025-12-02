package com.itlvck.sblmobile.dto;

import android.widget.EditText;

public class RegisterRequest {
    //variables
    private String userName;
    private String password;

    //constructor
    public RegisterRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    //getter
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    //setter
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
