package pl.gooradev.library.control.sl.load;

import pl.gooradev.library.io.ConsolePrinter;
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
    CsvFileManager cfm = new CsvFileManager();

    int optionInt;
    LoadOption loadOption;


    public LoadControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader) {
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
        this.sfm = new SerializableFileManager(consolePrinter);
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
                this.library = sfm.importData();
                break;
            case CSV_LOAD:
                dataReader.getAndSetFilePath();
                this.library = cfm.importData();
                break;
            case BACK:
                break;
        }
    }

    private void printOptions() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(LoadOption.SERIAL_LOAD);
        consolePrinter.printLine(LoadOption.CSV_LOAD);
        consolePrinter.printLine(LoadOption.BACK);
    }

    private LoadOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return LoadOption.createFromInt(optionInt);
    }
}
