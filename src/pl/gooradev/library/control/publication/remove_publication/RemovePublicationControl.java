package pl.gooradev.library.control.publication.remove_publication;

import pl.gooradev.library.control.library.LibraryControl;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;

import java.util.InputMismatchException;

public class RemovePublicationControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    int optionInt;
    RemovePublicationOption removePublicationOption;


    public RemovePublicationControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void removePublicationLoop() {
        do {

            try {
                refreshLibrary();
                printRemovePublicationMenu();
                removePublicationOption = getOption();
                removePublication(removePublicationOption);
            } catch (NullPointerException e) {
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }

        } while (removePublicationOption != RemovePublicationOption.BACK);
    }

    private void removePublication(RemovePublicationOption removePublicationOption) {
        switch (removePublicationOption) {
            case REMOVE_BY_ID:
                removeById();
                break;
            case REMOVE_BY_PUBLICATION:
                removeByPublicationLoop();
                break;
            case BACK:
                break;
        }
    }

    private void removeById() {
        consolePrinter.printLine("Podaj ID publikacji do usunięcia: ");
        int id = dataReader.getInt();
        boolean removed = false;

        try {
            removed = library.removePublication(id);
        } catch (NullPointerException e) {
            consolePrinter.printLine("Brak publikacji o ID " + id);
        }

        if (removed)
            consolePrinter.printLine("Pomyślnie usunięto publikację o ID " + id);
    }

    private void removeByPublicationLoop() {
        do {

            try{
                printRemoveByPublicationMenu();
                removePublicationOption = getOption();
                removeByPublication(removePublicationOption);
            } catch (NullPointerException e) {
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }

        } while (removePublicationOption != RemovePublicationOption.BACK);
    }

    private void removeByPublication(RemovePublicationOption removePublicationOption) {

            switch (removePublicationOption) {
                case REMOVE_BOOK:
                    removeBook();
                    break;
                case REMOVE_MAGAZINE:
                    removeMagazine();
                    break;
                case BACK:
                    break;
            }
    }

    private void removeBook() {

        try {

            Publication publication = dataReader.readAndCreateBook();
            if (library.removePublication(publication))
                consolePrinter.printLine("Usunięto książkę");
            else
                consolePrinter.printLine("Brak wskazanej książki");

        } catch (InputMismatchException e) {
            consolePrinter.printLine("Nie udało się usunąć wskazanej książki, niepoprawne dane");
        }
    }

    private void removeMagazine() {

        try {

            Publication publication = dataReader.readAndCreateMagazine();
            if (library.removePublication(publication))
                consolePrinter.printLine("Usunięto magazyn/gazetę");
            else
                consolePrinter.printLine("Brak wskazanego magazynu/gazety");

        } catch (InputMismatchException e) {
            consolePrinter.printLine("Nie udało się usunąć wskazanego magazynu/gazety, niepoprawne dane");
        }
    }


    private void printRemovePublicationMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(RemovePublicationOption.REMOVE_BY_ID);
        consolePrinter.printLine(RemovePublicationOption.REMOVE_BY_PUBLICATION);
        consolePrinter.printLine(RemovePublicationOption.BACK);
    }

    private void printRemoveByPublicationMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(RemovePublicationOption.REMOVE_BOOK);
        consolePrinter.printLine(RemovePublicationOption.REMOVE_MAGAZINE);
        consolePrinter.printLine(RemovePublicationOption.BACK);
    }

    private RemovePublicationOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return RemovePublicationOption.createFromInt(optionInt);
    }

    private void refreshLibrary(){
        library = LibraryControl.getLibrary();
    }
}
