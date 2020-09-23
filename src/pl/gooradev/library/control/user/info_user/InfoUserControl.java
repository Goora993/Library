package pl.gooradev.library.control.user.info_user;

import pl.gooradev.library.exception.NoUserWithSuchPeselException;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;


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

    public void printUserByPesel() throws NoUserWithSuchPeselException {
        consolePrinter.printLine("Podaj numer pesel użytkownika: ");
        consolePrinter.printUserByPesel(library, dataReader.getString());
    }
}
