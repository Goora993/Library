package pl.gooradev.library.control.user.add_user;

import pl.gooradev.library.exception.UserAlreadyExistException;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.User;

public class AddUserControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    public AddUserControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void addUser() throws UserAlreadyExistException {
        User userToAdd = dataReader.readAndCreateUser();
        library.addUser(userToAdd);
    }
}
