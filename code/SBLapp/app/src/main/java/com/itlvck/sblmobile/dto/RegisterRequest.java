package com.itlvck.sblmobile.dto;

import android.widget.EditText;

public class RegisterRequest {
        private EditText userName;
        private EditText password;

        public RegisterRequest(EditText userName, EditText password) {
            this.userName = userName;
            this.password = password;
        }
}
