package com.demo.demo.message.response;

public class BirthDayException extends RuntimeException{

    private static final long serialVersionUID = 2827308907740826575L;

    private final String code;

    public BirthDayException(String message){
        super(message);
        this.code = "0";
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     * @param code    the code
     */
    public BirthDayException(String message, String code) {
        super(message);
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }
}
