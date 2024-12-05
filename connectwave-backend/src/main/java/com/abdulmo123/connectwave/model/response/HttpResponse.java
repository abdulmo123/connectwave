package com.abdulmo123.connectwave.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HttpResponse implements Serializable {
    private int status;
    private String message;
    private Object data;
}
