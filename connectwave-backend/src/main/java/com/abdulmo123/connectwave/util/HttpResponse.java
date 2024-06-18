package com.abdulmo123.connectwave.util;

import java.io.Serializable;

public class HttpResponse implements Serializable {

    private int statusCode;
    private String message;
    private Object data;

    public HttpResponse() {}

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
