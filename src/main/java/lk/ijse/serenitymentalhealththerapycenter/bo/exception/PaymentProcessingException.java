package lk.ijse.serenitymentalhealththerapycenter.bo.exception;

public class PaymentProcessingException extends Exception {

    public PaymentProcessingException(String message) {
        super(message);
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}