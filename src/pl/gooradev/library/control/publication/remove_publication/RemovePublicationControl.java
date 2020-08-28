package pl.gooradev.library.control.publication.remove_publication;

import pl.gooradev.library.exception.NoSuchIdException;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;

import java.util.InputMismatchException;

public class RemovePublicationControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    public RemovePublicationControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void removePublication() throws NoSuchIdException {
        RemovePublicationOption option;
        do {
            printRemovePublicationMenu();
            option = RemovePublicationOption.getOption(dataReader.getInt());
            switch (option) {
                case REMOVE_BY_ID:
                    removeById();
                    break;
                case REMOVE_BY_PUBLICATION:
                    removeByPublication();
                    break;
                case BACK:
                    break;
            }
        } while (option != RemovePublicationOption.BACK);

    }


    private void removeById() throws NoSuchIdException {
        consolePrinter.printLine("Podaj ID publikacji do usunięcia: ");
        int id = dataReader.getInt();
        boolean removed;
        try{
            removed = library.removePublication(id);
        } catch (NullPointerException e){
            throw new NoSuchIdException("Brak publikacji o ID " + id);
        }

        if(removed)
            consolePrinter.printLine("Pomyślnie usunięto publikację o ID " + id);

    }


    private void removeByPublication() {
        RemovePublicationOption option;
        do {
            printRemoveByPublicationMenu();
            option = RemovePublicationOption.getOption(dataReader.getInt());
            switch (option) {
                case REMOVE_BOOK:
                    removeBook();
                    break;
                case REMOVE_MAGAZINE:
                    removeMagazine();
                    break;
                case BACK:
                    break;
            }
        } while (option != RemovePublicationOption.BACK);
    }

    private void removeBook() {
        try{
            Publication publication = dataReader.readAndCreateBook();
            if(library.removePublication(publication))
                consolePrinter.printLine("Usunięto książkę");
            else
                consolePrinter.printLine("Brak wskazanej książki");
        } catch (InputMismatchException e){
            consolePrinter.printLine("Nie udało się usunąć wskazanej książki, niepoprawne dane");
        }
    }

    private void removeMagazine() {
        try{
            Publication publication = dataReader.readAndCreateMagazine();
            if(library.removePublication(publication))
                consolePrinter.printLine("Usunięto magazyn/gazetę");
            else
                consolePrinter.printLine("Brak wskazanego magazynu/gazety");
        } catch (InputMismatchException e){
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
}
