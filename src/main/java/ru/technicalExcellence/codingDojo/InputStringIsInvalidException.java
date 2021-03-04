package ru.technicalExcellence.codingDojo;

public class InputStringIsInvalidException extends RuntimeException {

    public InputStringIsInvalidException() {
        super();
    }

    public InputStringIsInvalidException(String message) {
        super(message);
    }
}
