package pl.gooradev.library.control;

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
        Option option;
        do {
            printPublicationsInfoMenu();
            option = Option.getOption(dataReader.getInt());
            switch (option) {
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case PRINT_ALL:
                    printAll();
                    break;
                case BACK:
                    break;
            }
        } while (option != Option.BACK);
    }

    private void printBooks() {
        consolePrinter.printBooks(library);
    }

    private void printMagazines() {
        consolePrinter.printMagazines(library);
    }

    private void printAll() {
        consolePrinter.printAll(library);
    }

    private void printPublicationsInfoMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(Option.PRINT_BOOKS);
        consolePrinter.printLine(Option.PRINT_MAGAZINES);
        consolePrinter.printLine(Option.PRINT_ALL);
        consolePrinter.printLine(Option.BACK);
    }
}

