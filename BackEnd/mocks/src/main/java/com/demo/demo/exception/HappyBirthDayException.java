package com.demo.demo.exception;

public class HappyBirthDayException extends RuntimeException {

    private static final long serialVersionUID = 2827308907740826575L;

    private final String code;

    public HappyBirthDayException(String message, String code) {
        super(message);
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
