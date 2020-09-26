package pl.gooradev.library.io.print;

import pl.gooradev.library.control.publication.info_publication.info_book.InfoBookOption;
import pl.gooradev.library.control.publication.info_publication.info_magazine.InfoMagazineOption;
import pl.gooradev.library.exception.NoUserWithSuchPeselException;
import pl.gooradev.library.io.file.CsvFileManager;
import pl.gooradev.library.model.*;


public class ConsolePrinter {

    public void printAllPublications(Library library) {
        printLine(PublicationPrinter.printAllPublications(library));
    }

    public void printBooks(Library library, InfoBookOption infoBookOption) {
        printLine(PublicationPrinter.printBooks(library, infoBookOption));
    }

    public void printMagazines(Library library, InfoMagazineOption infoMagazineOption) {
        printLine(PublicationPrinter.printMagazines(library, infoMagazineOption));
    }

    public void printAllUsers(Library library) {
        printLine(UserPrinter.printAllUsers(library));
    }

    public void printUserByPesel(Library library, String pesel) throws NoUserWithSuchPeselException {
        printLine(UserPrinter.printUserByPesel(library, pesel));
    }

    public void printLine(String text) {
        System.out.println(text);
    }

    public <T> void printLine(T t) {
        System.out.println(t.toString());
    }


}

