package pl.gooradev.library.control.library;

import pl.gooradev.library.control.add.AddControl;
import pl.gooradev.library.control.info.InfoControl;
import pl.gooradev.library.control.remove.RemoveControl;
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
    AddControl addControl;
    RemoveControl removeControl;
    InfoControl infoControl;
    int optionInt;

     public LibraryControl() {
        fileManager = new FileManagerBuilder(consolePrinter, dataReader).build();
        try {
            library = fileManager.importData();
            consolePrinter.printLine("Zaimportowane dane z pliku");
            addControl = new AddControl(library,dataReader,consolePrinter);
            removeControl = new RemoveControl(library, dataReader, consolePrinter);
            infoControl = new InfoControl(library, dataReader, consolePrinter);
        } catch (DataImportException | InvalidDataException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("Zainicjowano nową bazę.");
            library = new Library();
        }
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
            } catch (NoSuchIdException e) {
                consolePrinter.printLine(e.getMessage());
            }
        } while (libraryOption != LibraryOption.EXIT);
    }


    private void mainLoop(LibraryOption libraryOption) throws NoSuchOptionException, InputMismatchException, NoSuchIdException {
        try{
            switch (libraryOption) {
                case ADD_PUBLICATION:
                    addControl.addPublication();
                    break;
                case REMOVE_PUBLICATION:
                    removeControl.removePublication();
                    break;
                case PRINT_PUBLICATIONS:
                    infoControl.printPublications();
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
        consolePrinter.printLine(LibraryOption.EXIT);
    }


    private LibraryOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return LibraryOption.createFromInt(optionInt);
    }
}





