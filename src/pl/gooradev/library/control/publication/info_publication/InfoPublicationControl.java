package pl.gooradev.library.control.publication.info_publication;

import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

public class InfoPublicationControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    public InfoPublicationControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void printPublications() {
        InfoPublicationOption option;
        do {
            printPublicationsInfoMenu();
            option = InfoPublicationOption.getOption(dataReader.getInt());
            switch (option) {
                case PRINT_BOOKS:
                    printSortedBooks();
                    break;
                case PRINT_MAGAZINES:
                    printSortedMagazines();
                    break;
                case PRINT_ALL:
                    printAll();
                    break;
                case BACK:
                    break;
            }
        } while (option != InfoPublicationOption.BACK);
    }

    private void printSortedBooks(){
        InfoPublicationOption option;
        do {
            printSortBooksMenu();
            option = InfoPublicationOption.getOption(dataReader.getInt());
            switch (option) {
                case SORT_BY_TITLE:
                case SORT_BY_AUTHOR:
                    printBooks(option);
                    break;
                case BACK:
                    break;
            }
        } while (option != InfoPublicationOption.BACK);
    }

    private void printBooks(InfoPublicationOption option) {
        consolePrinter.printBooks(library, option);
    }

    private void printSortedMagazines(){
        InfoPublicationOption option;
        do {
            printSortMagazinesMenu();
            option = InfoPublicationOption.getOption(dataReader.getInt());
            switch (option) {
                case SORT_BY_NAME:
                case SORT_BY_PUBLISHER:
                    printMagazines(option);
                    break;
                case BACK:
                    break;
            }
        } while (option != InfoPublicationOption.BACK);
    }

    private void printMagazines(InfoPublicationOption option) {
        consolePrinter.printMagazines(library, option);
    }

    private void printAll() {
        consolePrinter.printAllPublications(library);
    }


    private void printPublicationsInfoMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(InfoPublicationOption.PRINT_BOOKS);
        consolePrinter.printLine(InfoPublicationOption.PRINT_MAGAZINES);
        consolePrinter.printLine(InfoPublicationOption.PRINT_ALL);
        consolePrinter.printLine(InfoPublicationOption.BACK);
    }

    private void printSortBooksMenu(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(InfoPublicationOption.SORT_BY_TITLE);
        consolePrinter.printLine(InfoPublicationOption.SORT_BY_AUTHOR);
        consolePrinter.printLine(InfoPublicationOption.BACK);
    }

    private void printSortMagazinesMenu(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(InfoPublicationOption.SORT_BY_NAME);
        consolePrinter.printLine(InfoPublicationOption.SORT_BY_PUBLISHER);
        consolePrinter.printLine(InfoPublicationOption.BACK);
    }
}

