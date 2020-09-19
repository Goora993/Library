package pl.gooradev.library.control.sl.save;

import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.io.file.CsvFileManager;
import pl.gooradev.library.io.file.SerializableFileManager;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class SaveControl {
    Library library;
    ConsolePrinter consolePrinter;
    DataReader dataReader;

    SerializableFileManager sfm;
    CsvFileManager cfm = new CsvFileManager();

    int optionInt;
    SaveOption saveOption;


    public SaveControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader){
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
        this.sfm = new SerializableFileManager(consolePrinter);
    }

    public void manageSaveLoop() {
        do {

            try{
                printOptions();
                saveOption = getOption();
                manageSave(saveOption);
            } catch (NullPointerException e){
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException e) {
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }

        } while (saveOption!=SaveOption.BACK);
    }

    private void manageSave(SaveOption saveOption){
        switch (saveOption){
            case SERIAL_SAVE:
                sfm.exportData(library);
                break;
            case CSV_SAVE:
                dataReader.getAndSetFilePath();
                cfm.exportData(library);
                break;
            case BACK:
                break;
        }
    }

    private void printOptions(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(SaveOption.SERIAL_SAVE);
        consolePrinter.printLine(SaveOption.CSV_SAVE);
        consolePrinter.printLine(SaveOption.BACK);
    }

    private SaveOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return SaveOption.createFromInt(optionInt);
    }
}
