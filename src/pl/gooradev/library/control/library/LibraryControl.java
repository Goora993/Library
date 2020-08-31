package pl.gooradev.library.control.library;

import pl.gooradev.library.control.publication.PublicationControl;
import pl.gooradev.library.control.user.UserControl;
import pl.gooradev.library.exception.*;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.io.file.FileManager;
import pl.gooradev.library.io.file.FileManagerBuilder;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class LibraryControl {

    Library library;
    ConsolePrinter consolePrinter = new ConsolePrinter();
    DataReader dataReader = new DataReader(consolePrinter);
    FileManager fileManager;
    PublicationControl publicationControl;
    UserControl userControl;

    int optionInt;
    LibraryOption libraryOption;

     public LibraryControl() {
        fileManager = new FileManagerBuilder(consolePrinter, dataReader).build();
        try {
            library = fileManager.importData();
            consolePrinter.printLine("Zaimportowane dane z pliku");
            publicationControl = new PublicationControl(library, consolePrinter, dataReader);
            userControl = new UserControl(library, consolePrinter, dataReader);
        } catch (DataImportException | InvalidDataException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("Zainicjowano nową bazę.");
            library = new Library();
        }
    }


    public void run() {
        do {
            printOptions();
            try{
                libraryOption = getOption();
                libraryMainSwitch(libraryOption);
            } catch (NoSuchOptionException | InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            } catch (NoSuchIdException | UserAlreadyExistException | NoUserWithSuchPesel e) {
                consolePrinter.printLine(e.getMessage());
            }
        } while (libraryOption != LibraryOption.EXIT);
    }


    private void libraryMainSwitch(LibraryOption libraryOption) throws NoSuchOptionException, InputMismatchException,
            NoSuchIdException, UserAlreadyExistException, NoUserWithSuchPesel {
        try{
            switch (libraryOption) {
                case PUBLICATION_MENU:
                    publicationControl.managePublicationLoop();
                    break;
                case USER_MENU:
                    userControl.manageUserLoop();
                    break;
                case EXIT:
                    exit();
                    break;
            }
        } catch (NullPointerException e){
            throw new NoSuchOptionException("Brak opcji o id " + optionInt);
        }
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
        consolePrinter.printLine(LibraryOption.PUBLICATION_MENU);
        consolePrinter.printLine(LibraryOption.USER_MENU);
        consolePrinter.printLine(LibraryOption.EXIT);
    }


    private LibraryOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return LibraryOption.createFromInt(optionInt);
    }
}





