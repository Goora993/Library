package pl.gooradev.library.exception;

public class NoUserWithSuchPesel extends Exception {
    public NoUserWithSuchPesel(String message) {
        super(message);
    }
}
