package pl.gooradev.library.control.user.borrow_publication;

import pl.gooradev.library.exception.NoPublicationWithSuchId;
import pl.gooradev.library.exception.NoUserWithSuchPesel;
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

    public void borrowPublication() throws NoUserWithSuchPesel, NoPublicationWithSuchId {

        User user = getUser();
        Publication publication = getPublication();

        if(user instanceof LibraryUser){
            ((LibraryUser) user).borrowPublication(publication);
        }

        consolePrinter.printLine("Publikacja id: " + publication.getId() + " " + publication.getTitle() +
                " została wypożyczona");

    }

    private User getUser() throws NoUserWithSuchPesel {
        consolePrinter.printLine("Podaj numer pesel użytkownika: ");
        String pesel = dataReader.getString();
        User user = library.getUsers().get(pesel);

        if(user!=null)
            return user;
        else
            throw new NoUserWithSuchPesel("Brak użytkownika o numerze pesel " + pesel);


    }

    private Publication getPublication() throws NoPublicationWithSuchId {
        consolePrinter.printLine("Podaj ID publikacji: ");
        int id = dataReader.getInt();
        Publication publication = library.getPublications().get(id);

        if(publication!=null)
            return publication;
        else
            throw new NoPublicationWithSuchId("Brak publikacji o ID " + id);
    }
}
