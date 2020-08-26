package pl.gooradev.library.control.info;

import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

public class InfoControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    public InfoControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void printPublications() {
        InfoOption  option;
        do {
            printPublicationsInfoMenu();
            option = InfoOption .getOption(dataReader.getInt());
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
        } while (option != InfoOption .BACK);
    }

    private void printSortedBooks(){
        InfoOption option;
        do {
            printSortBooksMenu();
            option = InfoOption.getOption(dataReader.getInt());
            switch (option) {
                case SORT_BY_TITLE:
                case SORT_BY_AUTHOR:
                    printBooks(option);
                    break;
                case BACK:
                    break;
            }
        } while (option != InfoOption.BACK);
    }

    private void printBooks(InfoOption option) {
        consolePrinter.printBooks(library, option);
    }

    private void printSortedMagazines(){
        InfoOption option;
        do {
            printSortMagazinesMenu();
            option = InfoOption.getOption(dataReader.getInt());
            switch (option) {
                case SORT_BY_NAME:
                case SORT_BY_PUBLISHER:
                    printMagazines(option);
                    break;
                case BACK:
                    break;
            }
        } while (option != InfoOption.BACK);
    }

    private void printMagazines(InfoOption option) {
        consolePrinter.printMagazines(library, option);
    }

    private void printAll() {
        consolePrinter.printAll(library);
    }


    private void printPublicationsInfoMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(InfoOption.PRINT_BOOKS);
        consolePrinter.printLine(InfoOption.PRINT_MAGAZINES);
        consolePrinter.printLine(InfoOption.PRINT_ALL);
        consolePrinter.printLine(InfoOption.BACK);
    }

    private void printSortBooksMenu(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(InfoOption.SORT_BY_TITLE);
        consolePrinter.printLine(InfoOption.SORT_BY_AUTHOR);
        consolePrinter.printLine(InfoOption.BACK);
    }

    private void printSortMagazinesMenu(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(InfoOption.SORT_BY_NAME);
        consolePrinter.printLine(InfoOption.SORT_BY_PUBLISHER);
        consolePrinter.printLine(InfoOption.BACK);
    }
}

