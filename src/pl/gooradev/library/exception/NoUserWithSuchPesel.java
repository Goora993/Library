package pl.gooradev.library.exception;

public class NoUserWithSuchPesel extends RuntimeException {
    public NoUserWithSuchPesel(String message) {
        super(message);
    }
}
