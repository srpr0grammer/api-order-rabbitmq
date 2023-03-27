package com.alanjsantos.orderapi.controller.exception;

public class ErrorGenericException extends RuntimeException {
    public ErrorGenericException(String msg){
        super(msg);
    }
}
