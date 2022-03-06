package com.tedi.tictactoe.demo.exception;

public class InvalidGameException extends Exception{
    private String message;

    public InvalidGameException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
