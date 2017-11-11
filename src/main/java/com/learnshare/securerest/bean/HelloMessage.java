package com.learnshare.securerest.bean;

import io.swagger.annotations.ApiModelProperty;

public class HelloMessage {

    @ApiModelProperty(notes = "Provided user name", required =true)
    private String userName;

    @ApiModelProperty(notes = "The system generated greeting message" , readOnly =true)
    private String message;

    public HelloMessage(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
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
