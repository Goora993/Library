package pl.gooradev.library.control.publication.info_publication.info_book;

import pl.gooradev.library.control.publication.info_publication.InfoPublicationOption;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class InfoBookControl {

    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    int optionInt;
    InfoBookOption infoBookOption;


    public InfoBookControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter){
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }


    public void printSortedBooksLoop() {
        do{

            try{
                printSortBooksMenu();
                infoBookOption = getOption();
                printSortedBooks(infoBookOption);
            } catch (NullPointerException e) {
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }

        } while (infoBookOption != InfoBookOption.BACK);

    }


    private void printSortedBooks(InfoBookOption infoBookOption) throws NullPointerException{
        switch (infoBookOption) {
            case SORT_BY_TITLE:
            case SORT_BY_AUTHOR:
                printBooks(infoBookOption);
                break;
            case BACK:
                break;
        }
    }


    private void printBooks(InfoBookOption infoBookOption) {
        consolePrinter.printBooks(library, infoBookOption);
    }


    private void printSortBooksMenu(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(InfoBookOption.SORT_BY_TITLE);
        consolePrinter.printLine(InfoBookOption.SORT_BY_AUTHOR);
        consolePrinter.printLine(InfoBookOption.BACK);
    }


    private InfoBookOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return InfoBookOption.createFromInt(optionInt);
    }
}

