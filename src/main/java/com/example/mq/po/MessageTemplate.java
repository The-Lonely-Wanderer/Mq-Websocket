package com.example.mq.po;


import java.io.Serializable;

public class MessageTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发送用户
     */
    private String sendUser;


    /**
     * 接受对象
     */
    private String receiverUser;

    /**
     * 消息
     */
    private String message;

    /**
     * 消息标志
     * */
    private String deliveryTag;


    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(String receiverUser) {
        this.receiverUser = receiverUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeliveryTag() {
        return deliveryTag;
    }

    public void setDeliveryTag(String deliveryTag) {
        this.deliveryTag = deliveryTag;
    }
}
