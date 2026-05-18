package lk.ijse.serenitymentalhealththerapycenter.bo.exception;

public class LoginException extends Exception {

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}