package com.curdexample.crudexample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse {
    private String message;
    private Object obj;
    HttpStatus status;

    public BaseResponse() {
    }

    public BaseResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public BaseResponse(String message, HttpStatus status, Object obj) {
        this.message = message;
        this.status = status;
        this.obj = obj;
    }

}
