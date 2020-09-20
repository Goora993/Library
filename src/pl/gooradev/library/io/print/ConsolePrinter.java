package pl.gooradev.library.io.print;

import pl.gooradev.library.control.publication.info_publication.InfoPublicationOption;
import pl.gooradev.library.exception.NoUserWithSuchPesel;
import pl.gooradev.library.model.*;


public class ConsolePrinter {


    public void printAllPublications(Library library) {
        PublicationPrinter.printAllPublications(library);
    }

    public void printBooks(Library library, InfoPublicationOption option) {
        PublicationPrinter.printBooks(library, option);
    }

    public void printMagazines(Library library, InfoPublicationOption option) {
        PublicationPrinter.printMagazines(library, option);
    }

    public void printAllUsers(Library library) {
        UserPrinter.printAllUsers(library);
    }

    public void printUserByPesel(Library library, String pesel) throws NoUserWithSuchPesel {
        UserPrinter.printUserByPesel(library, pesel);
    }

    public void printLine(String text) {
        System.out.println(text);
    }

    public <T> void printLine(T t) {
        System.out.println(t.toString());
    }


}

