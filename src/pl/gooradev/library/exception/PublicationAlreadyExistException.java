package pl.gooradev.library.exception;

public class PublicationAlreadyExistException extends RuntimeException {
    public PublicationAlreadyExistException(String message) {
        super(message);
    }
}
