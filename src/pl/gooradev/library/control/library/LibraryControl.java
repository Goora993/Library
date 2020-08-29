package pl.gooradev.library.control.library;

import pl.gooradev.library.control.publication.add_publication.AddPublicationControl;
import pl.gooradev.library.control.publication.info_publication.InfoPublicationControl;
import pl.gooradev.library.control.publication.remove_publication.RemovePublicationControl;
import pl.gooradev.library.control.user.add_user.AddUserControl;
import pl.gooradev.library.control.user.info_user.InfoUserControl;
import pl.gooradev.library.control.user.remove_user.RemoveUserControl;
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
    AddPublicationControl addPublicationControl;
    RemovePublicationControl removePublicationControl;
    InfoPublicationControl infoPublicationControl;
    AddUserControl addUserControl;
    RemoveUserControl removeUserControl;
    InfoUserControl infoUserControl;

    int optionInt;

     public LibraryControl() {
        fileManager = new FileManagerBuilder(consolePrinter, dataReader).build();
        try {
            library = fileManager.importData();
            consolePrinter.printLine("Zaimportowane dane z pliku");
            addPublicationControls();
            addUserControls();
        } catch (DataImportException | InvalidDataException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("Zainicjowano nową bazę.");
            library = new Library();
        }
    }


    private void addPublicationControls() {
        addPublicationControl = new AddPublicationControl(library,dataReader,consolePrinter);
        removePublicationControl = new RemovePublicationControl(library, dataReader, consolePrinter);
        infoPublicationControl = new InfoPublicationControl(library, dataReader, consolePrinter);
    }

    private void addUserControls() {
         addUserControl = new AddUserControl(library, dataReader, consolePrinter);
         removeUserControl = new RemoveUserControl(library, dataReader, consolePrinter);
         infoUserControl = new InfoUserControl(library, dataReader, consolePrinter);
    }


    public void run() {
        LibraryOption libraryOption = null;
        do {
            printOptions();
            try{
                libraryOption = getOption();
                mainLoop(libraryOption);
            } catch (NoSuchOptionException | InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            } catch (NoSuchIdException | UserAlreadyExistException | NoUserWithSuchPesel e) {
                consolePrinter.printLine(e.getMessage());
            }
        } while (libraryOption != LibraryOption.EXIT);
    }


    private void mainLoop(LibraryOption libraryOption) throws NoSuchOptionException, InputMismatchException,
            NoSuchIdException, UserAlreadyExistException, NoUserWithSuchPesel {
        try{
            switch (libraryOption) {
                case ADD_PUBLICATION:
                    addPublicationControl.addPublication();
                    break;
                case REMOVE_PUBLICATION:
                    removePublicationControl.removePublication();
                    break;
                case PRINT_PUBLICATIONS:
                    infoPublicationControl.printPublications();
                    break;
                case ADD_USER:
                    addUserControl.addUser();
                    break;
                case REMOVE_USER:
                    removeUserControl.removeUser();
                    break;
                case PRINT_USER:
                    infoUserControl.printUsers();
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
        consolePrinter.printLine(LibraryOption.ADD_PUBLICATION);
        consolePrinter.printLine(LibraryOption.REMOVE_PUBLICATION);
        consolePrinter.printLine(LibraryOption.PRINT_PUBLICATIONS);
        consolePrinter.printLine(LibraryOption.ADD_USER);
        consolePrinter.printLine(LibraryOption.REMOVE_USER);
        consolePrinter.printLine(LibraryOption.PRINT_USER);
        consolePrinter.printLine(LibraryOption.EXIT);
    }


    private LibraryOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return LibraryOption.createFromInt(optionInt);
    }
}





