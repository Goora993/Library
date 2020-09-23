package pl.gooradev.library.exception;

public class NoUserWithSuchPeselException extends RuntimeException {
    public NoUserWithSuchPeselException(String message) {
        super(message);
    }
}
