package com.zack.labelcreation.exceptions;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String msg) {
        super(msg);
    }
}
