package pl.gooradev.library.exception;

public class PublicationIsAlreadyBorrowedException extends RuntimeException {
    public PublicationIsAlreadyBorrowedException(String message){
        super(message);
    }
}
