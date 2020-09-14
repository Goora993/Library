package pl.gooradev.library.control.publication.info_publication;

import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class InfoPublicationControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    int optionInt;
    InfoPublicationOption infoPublicationOption;

    public InfoPublicationControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void printPublicationsLoop(){
        do{
            try {
                printPublicationsInfoMenu();
                infoPublicationOption = getOption();
                printPublications(infoPublicationOption);
            } catch (NullPointerException e) {
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }
        } while (infoPublicationOption != InfoPublicationOption.BACK);
    }

    private void printPublications(InfoPublicationOption infoPublicationOption)
            throws InputMismatchException, NullPointerException {
            switch (infoPublicationOption) {
                case PRINT_BOOKS:
                    printSortedBooksLoop();
                    break;
                case PRINT_MAGAZINES:
                    printSortedMagazinesLoop();
                    break;
                case PRINT_ALL:
                    printAll();
                    break;
                case BACK:
                    break;
            }
    }

    private void printSortedBooksLoop() throws InputMismatchException, NullPointerException{
        do{
            try{
                printSortBooksMenu();
                infoPublicationOption = getOption();
                printSortedBooks(infoPublicationOption);
            } catch (NullPointerException e) {
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }
        } while (infoPublicationOption != InfoPublicationOption.BACK);

        printPublicationsInfoMenu();
        infoPublicationOption = getOption();
    }

    private void printSortedBooks(InfoPublicationOption infoPublicationOption){
            switch (infoPublicationOption) {
                case SORT_BY_TITLE:
                case SORT_BY_AUTHOR:
                    printBooks(infoPublicationOption);
                    break;
                case BACK:
                    break;
            }
    }

    private void printBooks(InfoPublicationOption option) {
        consolePrinter.printBooks(library, option);
    }


    private void printSortedMagazinesLoop() throws InputMismatchException, NullPointerException{
        do{
            try{
                printSortMagazinesMenu();
                infoPublicationOption = getOption();
                printSortedMagazines(infoPublicationOption);
            } catch (NullPointerException e) {
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }
        } while (infoPublicationOption != InfoPublicationOption.BACK);

        printPublicationsInfoMenu();
        infoPublicationOption = getOption();
    }


    private void printSortedMagazines(InfoPublicationOption infoPublicationOption){
            switch (infoPublicationOption) {
                case SORT_BY_NAME:
                case SORT_BY_PUBLISHER:
                    printMagazines(infoPublicationOption);
                    break;
                case BACK:
                    break;
            }
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

    private InfoPublicationOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return InfoPublicationOption.createFromInt(optionInt);
    }
}

