package com.icybiscuit.idol.utils;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
//@Setter
public class MessageUtil<T> {
    private int status;
    private String desc;
    private T data;


    public static MessageUtil ok(Object messageData) {
        MessageUtil m = new MessageUtil();
        m.setStatus(200);
        m.setDesc("success");
        m.setData(messageData);
        return m;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setData(T data) {
        this.data = data;
    }
}
