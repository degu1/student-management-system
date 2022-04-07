package se.iths.exception;

public record ExceptionDTO(String message) {

    public static ExceptionDTO of(Exception e) {
        return new ExceptionDTO(e.getMessage());
    }

    public String getMessage() {
        return message;
    }
}
