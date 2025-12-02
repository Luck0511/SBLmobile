package com.itlvck.sblmobile.dto;

public class LoginResponse {
    private String message;
    private UserInfo userInfo;

    public String getMessage() {
        return message;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public static class UserInfo {
        private int id;
        private String userName;

        public int getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }
    }
}