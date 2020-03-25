package com.example.mq.po;


import java.io.Serializable;

public class MessageTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接受对象
     */
    private String user;

    /**
     * 消息
     */
    private String message;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
