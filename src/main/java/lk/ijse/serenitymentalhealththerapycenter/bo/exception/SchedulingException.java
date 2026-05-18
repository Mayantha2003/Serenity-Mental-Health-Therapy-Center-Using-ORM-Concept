package lk.ijse.serenitymentalhealththerapycenter.bo.exception;

public class SchedulingException extends Exception {

    public SchedulingException(String message) {
        super(message);
    }

    public SchedulingException(String message, Throwable cause) {
        super(message, cause);
    }
}