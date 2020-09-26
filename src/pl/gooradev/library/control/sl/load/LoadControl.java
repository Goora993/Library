package pl.gooradev.library.control.sl.load;

import pl.gooradev.library.control.library.LibraryControl;
import pl.gooradev.library.io.file.*;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class LoadControl {
    Library library;
    ConsolePrinter consolePrinter;
    DataReader dataReader;

    FileManager fileManager;

    int optionInt;
    LoadOption loadOption;


    public LoadControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader) {
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
    }

    public void manageLoadLoop() {
        do {

            try{
                printOptions();
                loadOption = getOption();
                manageLoad(loadOption);
            } catch (NullPointerException e){
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }

        } while (loadOption != LoadOption.BACK);
    }

    private void manageLoad(LoadOption loadOption) {
        switch (loadOption) {
            case SERIAL_LOAD:
            case CSV_LOAD:
                loadData(loadOption.fileType());
                break;
            case CSV_BY_PATH_LOAD:
                dataReader.getAndSetFilePath();
                loadData(loadOption.fileType());
                break;
            case BACK:
                break;
        }
    }

    private void loadData(FileType fileType){
        fileManager = FileManagerBuilder.getFileManager(fileType, consolePrinter);
        LibraryControl.setLibrary(fileManager.importData());
    }


    private void printOptions() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(LoadOption.SERIAL_LOAD);
        consolePrinter.printLine(LoadOption.CSV_LOAD);
        consolePrinter.printLine(LoadOption.CSV_BY_PATH_LOAD);
        consolePrinter.printLine(LoadOption.BACK);
    }

    private LoadOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return LoadOption.createFromInt(optionInt);
    }
}
