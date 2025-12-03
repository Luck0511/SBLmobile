package com.itlvck.sblmobile.dto;

public class RegisterResponse {
    //variables
    private String message;
    private UserInfo userInfo;

    //getter
    public String getMessage() {
        return message;
    }
    public UserInfo getUserInfo() {
        return userInfo;
    }

    //setter
    public void setMessage(String message) {
        this.message = message;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    //constructor
    public static class UserInfo {
        //variables
        private int id;
        private String userName;

        //getter
        public int getId() {
            return id;
        }
        public String getUserName() {
            return userName;
        }

        //setter
        public void setId(int id) {
            this.id = id;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }

    }
}
