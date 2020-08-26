package pl.gooradev.library.control.remove;

import pl.gooradev.library.exception.NoSuchIdException;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;

import java.util.InputMismatchException;

public class RemoveControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    public RemoveControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }

    public void removePublication() throws NoSuchIdException {
        RemoveOption option;
        do {
            printRemovePublicationMenu();
            option = RemoveOption.getOption(dataReader.getInt());
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
        } while (option != RemoveOption.BACK);

    }


    private void removeById() throws NoSuchIdException {
        consolePrinter.printLine("Podaj ID publikacji do usunięcia: ");
        int id = dataReader.getInt();
        boolean removed;
        Publication publication = getPublicationById(id);
        try{
            removed = library.removePublicationByPublication(publication);
        } catch (NullPointerException e){
            throw new NoSuchIdException("Brak publikacji o ID " + id);
        }

        if(removed)
            consolePrinter.printLine("Pomyślnie usunięto publikację o ID " + id);

    }

    private Publication getPublicationById(int id) {
        Publication[] publications = library.getPublications();
        Publication resultPublication = null;
        for (Publication publication : publications) {
            if(publication.getId()==id)
                resultPublication = publication;
        }
        return resultPublication;
    }


    private void removeByPublication() {
        RemoveOption option;
        do {
            printRemoveByPublicationMenu();
            option = RemoveOption.getOption(dataReader.getInt());
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
        } while (option != RemoveOption.BACK);
    }

    private void removeBook() {
        try{
            Publication publication = dataReader.readAndCreateBook();
            if(library.removePublicationByPublication(publication))
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
            if(library.removePublicationByPublication(publication))
                consolePrinter.printLine("Usunięto magazyn/gazetę");
            else
                consolePrinter.printLine("Brak wskazanego magazynu/gazety");
        } catch (InputMismatchException e){
            consolePrinter.printLine("Nie udało się usunąć wskazanego magazynu/gazety, niepoprawne dane");
        }
    }


    private void printRemovePublicationMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(RemoveOption.REMOVE_BY_ID);
        consolePrinter.printLine(RemoveOption.REMOVE_BY_PUBLICATION);
        consolePrinter.printLine(RemoveOption.BACK);
    }

    private void printRemoveByPublicationMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(RemoveOption.REMOVE_BOOK);
        consolePrinter.printLine(RemoveOption.REMOVE_MAGAZINE);
        consolePrinter.printLine(RemoveOption.BACK);
    }
}
