package pl.gooradev.library.app;

import pl.gooradev.library.exception.DataExportException;
import pl.gooradev.library.exception.DataImportException;
import pl.gooradev.library.exception.InvalidDataException;
import pl.gooradev.library.exception.NoSuchOptionException;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.io.file.FileManager;
import pl.gooradev.library.io.file.FileManagerBuilder;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.util.IdGenerator;

import java.util.InputMismatchException;

public class LibraryControl {

    Library library;
    ConsolePrinter consolePrinter = new ConsolePrinter();
    DataReader dataReader = new DataReader(consolePrinter);
    private IdGenerator idGenerator = new IdGenerator();
    FileManager fileManager;
    int optionInt;

    LibraryControl() {
        fileManager = new FileManagerBuilder(consolePrinter, dataReader).build();
        try {
            library = fileManager.importData();
            consolePrinter.printLine("Zaimportowane dane z pliku");
        } catch (DataImportException | InvalidDataException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("Zainicjowano nową bazę.");
            library = new Library();
        }
    }


    public void mainLoop() {
        Option option = null;
        do {
            printOptions();
            try{
                option = getOption();
                mainLoopSwitch(option);
            } catch (NoSuchOptionException | InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }
        } while (option != Option.EXIT);
    }


    private void mainLoopSwitch(Option option) throws NoSuchOptionException, InputMismatchException{
        try{
            switch (option) {
                case ADD_PUBLICATION:
                    addPublication();
                    break;
                case REMOVE_PUBLICATION:
                    removePublication();
                    break;
                case PRINT_PUBLICATIONS:
                    printPublications();
                    break;
                case EXIT:
                    exit();
                    break;
            }
        } catch (NullPointerException e){
            throw new NoSuchOptionException("Brak opcji o id " + optionInt);
        }
    }


    private void addPublication() {
        Option option;
        do {
            printMagazineOrBookMenu();
            option = getOption();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case BACK:
                    break;
            }
        } while (option != Option.BACK);
    }

    private void addMagazine() {
        Publication publication = dataReader.readAndCreateMagazine();
        publication.setId(idGenerator.createId(library.getPublications()));
        library.addPublication(publication);
    }

    private void addBook() {
        Publication publication = dataReader.readAndCreateBook();
        publication.setId(idGenerator.createId(library.getPublications()));
        library.addPublication(publication);
    }

    private void removePublication() {
        Option option;
        do {
            printRemovePublicationMenu();
            option = getOption();
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


    private void printPublications() {
        Option option;
        do {
            printAddPublicationsMenu();
            option = getOption();
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

    private void exit() {
        try {
            fileManager.exportData(library);
            consolePrinter.printLine("Export danych do pliku zakończony powodzeniem");
        } catch (DataExportException e) {
            consolePrinter.printLine(e.getMessage());
        }
        consolePrinter.printLine("Do widzenia!");
        dataReader.close();
    }


    private void printOptions() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(Option.ADD_PUBLICATION);
        consolePrinter.printLine(Option.REMOVE_PUBLICATION);
        consolePrinter.printLine(Option.PRINT_PUBLICATIONS);
        consolePrinter.printLine(Option.EXIT);

    }

    private void printMagazineOrBookMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(Option.ADD_BOOK);
        consolePrinter.printLine(Option.ADD_MAGAZINE);
        consolePrinter.printLine(Option.BACK);
    }

    private void printAddPublicationsMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(Option.PRINT_BOOKS);
        consolePrinter.printLine(Option.PRINT_MAGAZINES);
        consolePrinter.printLine(Option.PRINT_ALL);
        consolePrinter.printLine(Option.BACK);
    }

    private void printRemovePublicationMenu() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(Option.REMOVE_BOOK);
        consolePrinter.printLine(Option.REMOVE_MAGAZINE);
        consolePrinter.printLine(Option.BACK);
    }

    private Option getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return Option.createFromInt(optionInt);
    }
}





