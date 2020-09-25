package pl.gooradev.library.control.sl.load;

import pl.gooradev.library.control.library.LibraryControl;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.io.file.CsvFileManager;
import pl.gooradev.library.io.file.SerializableFileManager;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class LoadControl {
    Library library;
    ConsolePrinter consolePrinter;
    DataReader dataReader;

    SerializableFileManager sfm;
    CsvFileManager cfm;

    int optionInt;
    LoadOption loadOption;


    public LoadControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader) {
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
        this.sfm = new SerializableFileManager(consolePrinter);
        this.cfm = new CsvFileManager(consolePrinter);
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
                LibraryControl.setLibrary(sfm.importData());
                break;
            case CSV_LOAD:
                LibraryControl.setLibrary(cfm.importData());
                break;
            case CSV_BY_PATH_LOAD:
                dataReader.getAndSetFilePath();
                LibraryControl.setLibrary(cfm.importData());
                break;
            case BACK:
                break;
        }
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
