package pl.gooradev.library.control.user.info_user;

import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.User;

import java.util.Collection;

public class InfoUserControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    public InfoUserControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void printUsers() {
        consolePrinter.printAllUsers(library);
    }
}
