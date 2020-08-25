package pl.gooradev.library.control;

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

    public void removePublication() {
        Option option;
        do {
            printRemovePublicationMenu();
            option = Option.getOption(dataReader.getInt());
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
        } while (option != Option.BACK);

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
        consolePrinter.printLine(Option.REMOVE_BOOK);
        consolePrinter.printLine(Option.REMOVE_MAGAZINE);
        consolePrinter.printLine(Option.BACK);
    }
}
