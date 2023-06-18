package com.calsoft.model;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    private int code;
    private Boolean success;
    private String message;
    private Object responseObject;

    public ApiResponse() {

    }
    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(int code, Boolean success, String message, Object response) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.responseObject = response;

    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }


}
