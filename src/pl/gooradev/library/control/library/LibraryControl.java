package pl.gooradev.library.control.library;

import pl.gooradev.library.control.publication.PublicationControl;
import pl.gooradev.library.control.sl.SaveLoadControl;
import pl.gooradev.library.control.user.UserControl;
import pl.gooradev.library.exception.*;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.io.file.FileManager;
import pl.gooradev.library.io.file.ImportType;
import pl.gooradev.library.io.file.SerializableFileManager;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class LibraryControl {

    Library library;
    ConsolePrinter consolePrinter = new ConsolePrinter();
    DataReader dataReader = new DataReader(consolePrinter);
    FileManager fileManager;
    PublicationControl publicationControl;
    UserControl userControl;
    SaveLoadControl saveLoadControl;

    int optionInt;
    LibraryOption libraryOption;

     public LibraryControl() {
        fileManager = new SerializableFileManager();
        try {
            library = fileManager.importData();
            consolePrinter.printLine("Zaimportowane dane z pliku");
        } catch (InvalidDataException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("Zainicjowano nową bazę.");
            library = new Library();
        } catch (PublicationImportException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("Zainicjowano nową bazę publikacji.");
            library = createLibraryWithEmptyPublications();
        } catch (UserImportException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("Zainicjowano nową bazę użytkowników.");
            library = createLibraryWithEmptyUsers();
        } finally {
            publicationControl = new PublicationControl(library, consolePrinter, dataReader);
            userControl = new UserControl(library, consolePrinter, dataReader);
            saveLoadControl = new SaveLoadControl(library, consolePrinter, dataReader);
        }
    }

    private Library createLibraryWithEmptyPublications(){
         Library resultLibrary;
        try{
            resultLibrary = fileManager.importData(ImportType.IMPORT_USERS);
        } catch (UserImportException ex){
            consolePrinter.printLine(ex.getMessage());
            consolePrinter.printLine("Zainicjowano nową bazę użytkowników.");
            resultLibrary = new Library();
        }
        return resultLibrary;
    }

    private Library createLibraryWithEmptyUsers(){
        Library resultLibrary;
        try{
            resultLibrary = fileManager.importData(ImportType.IMPORT_PUBLICATIONS);
        } catch (PublicationImportException ex){
            consolePrinter.printLine(ex.getMessage());
            consolePrinter.printLine("Zainicjowano nową bazę publikacji.");
            resultLibrary = new Library();
        }

        return resultLibrary;
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
                case SAVE_LOAD_MENU:
                    saveLoadControl.manageSaveLoadLoop();
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
        consolePrinter.printLine(LibraryOption.SAVE_LOAD_MENU);
        consolePrinter.printLine(LibraryOption.EXIT);
    }


    private LibraryOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return LibraryOption.createFromInt(optionInt);
    }
}





