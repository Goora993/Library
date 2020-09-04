package pl.gooradev.library.control.user.borrow_publication;

import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.LibraryUser;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.model.User;

public class BorrowPublicationControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    public BorrowPublicationControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter){
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void borrowPublication() {

        consolePrinter.printLine("Podaj numer pesel użytkownika: ");
        String pesel = dataReader.getString();
        consolePrinter.printLine("Podaj ID publikacji: ");
        int id = dataReader.getInt();


        User user = library.getUsers().get(pesel);
        Publication publication = library.getPublications().get(id);

        if(user instanceof LibraryUser){
            ((LibraryUser) user).borrowPublication(publication);
        }

    }
}
