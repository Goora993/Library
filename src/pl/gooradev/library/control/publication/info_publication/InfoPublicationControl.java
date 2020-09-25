package pl.gooradev.library.control.publication.info_publication;

import pl.gooradev.library.control.library.LibraryControl;
import pl.gooradev.library.control.publication.info_publication.info_book.InfoBookControl;
import pl.gooradev.library.control.publication.info_publication.info_magazine.InfoMagazineControl;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class InfoPublicationControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    InfoBookControl infoBookControl;
    InfoMagazineControl infoMagazineControl;

    int optionInt;
    InfoPublicationOption infoPublicationOption;


    public InfoPublicationControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        createControllers(library, dataReader, consolePrinter);
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    private void createControllers(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        infoBookControl = new InfoBookControl(library, dataReader, consolePrinter);
        infoMagazineControl = new InfoMagazineControl(library, dataReader, consolePrinter);
    }


    public void printPublicationsLoop(){
        do{

            try {
                refreshLibrary();
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
                    infoBookControl.printSortedBooksLoop();
                    break;
                case PRINT_MAGAZINES:
                    infoMagazineControl.printSortedMagazinesLoop();
                    break;
                case PRINT_ALL:
                    printAll();
                    break;
                case BACK:
                    break;
            }
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


    private InfoPublicationOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return InfoPublicationOption.createFromInt(optionInt);
    }

    private void refreshLibrary(){
        library = LibraryControl.getLibrary();
    }
}

