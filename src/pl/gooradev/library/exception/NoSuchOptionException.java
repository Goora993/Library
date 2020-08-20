package pl.gooradev.library.exception;

import java.io.IOException;

public class NoSuchOptionException extends Exception{
    public NoSuchOptionException(String message) {
        super(message);
    }
}
