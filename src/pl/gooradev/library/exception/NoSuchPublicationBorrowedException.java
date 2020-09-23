package pl.gooradev.library.exception;

public class NoSuchPublicationBorrowedException extends RuntimeException {
    public NoSuchPublicationBorrowedException(String message){
        super(message);
    }
}
