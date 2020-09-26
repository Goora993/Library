package pl.gooradev.library.control.user.return_publication;

import pl.gooradev.library.control.library.LibraryControl;
import pl.gooradev.library.exception.NoPublicationWithSuchIdException;
import pl.gooradev.library.exception.NoSuchPublicationBorrowedException;
import pl.gooradev.library.exception.NoUserWithSuchPeselException;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.LibraryUser;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.model.User;

import java.util.List;

public class ReturnPublicationControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    public ReturnPublicationControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter){
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }


    public void returnPublication() throws NoUserWithSuchPeselException, NoPublicationWithSuchIdException, NoSuchPublicationBorrowedException {
        refreshLibrary();

        LibraryUser user = getUser();
        Publication publication = getPublication();
        List<Publication> borrowedList = user.getBorrowedPublications();

        if(!borrowedList.contains(publication))
            throw new NoSuchPublicationBorrowedException("Użytkownik o numerze pesel " + user.getPesel() + " nie posiada wypożyczonej" +
                    " książki o id " + publication.getId());

        if(user instanceof LibraryUser){
            user.returnPublication(publication);
            publication.setBorrowed(false);
        }

        consolePrinter.printLine("Publikacja id: " + publication.getId() + ", \"" + publication.getTitle() +
                "\" została zwrócona");

    }

    private LibraryUser getUser() throws NoUserWithSuchPeselException {
        consolePrinter.printLine("Podaj numer pesel użytkownika: ");
        String pesel = dataReader.getString();
        LibraryUser user = (LibraryUser) library.getUsers().get(pesel);

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

    private void refreshLibrary(){
        library = LibraryControl.getLibrary();
    }
}
