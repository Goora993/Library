package pl.gooradev.library.control.user.borrow_publication;

import pl.gooradev.library.exception.NoPublicationWithSuchIdException;
import pl.gooradev.library.exception.NoUserWithSuchPeselException;
import pl.gooradev.library.exception.PublicationIsAlreadyBorrowedException;
import pl.gooradev.library.io.print.ConsolePrinter;
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


    public void borrowPublication() throws NoUserWithSuchPeselException, NoPublicationWithSuchIdException {

        User user = getUser();
        Publication publication = getPublication();

        if(publication.isBorrowed())
            throw new PublicationIsAlreadyBorrowedException("Publikacja o id: " + publication.getId() +
                    " jest już wypożyczona");

        if(user instanceof LibraryUser){
            ((LibraryUser) user).borrowPublication(publication);
            publication.setBorrowed(true);
        }

        consolePrinter.printLine("Publikacja id: " + publication.getId() + ", " + publication.getTitle() +
                " została wypożyczona");
    }


    private User getUser() throws NoUserWithSuchPeselException {
        consolePrinter.printLine("Podaj numer pesel użytkownika: ");
        String pesel = dataReader.getString();
        User user = library.getUsers().get(pesel);

        if(user!=null)
            return user;
        else
            throw new NoUserWithSuchPeselException("Brak użytkownika o numerze pesel " + pesel);
    }


    private Publication getPublication() throws NoPublicationWithSuchIdException {
        consolePrinter.printLine("Podaj ID publikacji: ");
        int id = dataReader.getInt();
        Publication publication = library.getPublications().get(id);

        if(publication!=null)
            return publication;
        else
            throw new NoPublicationWithSuchIdException("Brak publikacji o ID " + id);
    }

}
