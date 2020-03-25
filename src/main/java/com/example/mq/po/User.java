package com.example.mq.po;

import java.io.Serializable;
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 用户
     * */
    private String userName;

    private String password;
    /**
     * 消息
     * */
    private String message;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
