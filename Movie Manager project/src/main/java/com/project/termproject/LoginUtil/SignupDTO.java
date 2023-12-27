package com.project.termproject.LoginUtil;

import java.io.Serializable;

public class SignupDTO implements Serializable {
    public SignupDTO(String username, String password){
        this.userName = username;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String password;
}
