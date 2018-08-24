package com.icybiscuit.idol.utils;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
//@Setter
public class MessageUtil<T> {
    private int status;
    private String msg;
    private T content;


    public static MessageUtil isOK(Object messageContent) {
        MessageUtil m = new MessageUtil();
        m.setStatus(200);
        m.setMsg("success");
        m.setContent(messageContent);
        return m;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
