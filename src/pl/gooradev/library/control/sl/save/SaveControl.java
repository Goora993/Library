package pl.gooradev.library.control.sl.save;

import pl.gooradev.library.control.library.LibraryControl;
import pl.gooradev.library.io.file.CsvFileManager;
import pl.gooradev.library.io.file.FileManager;
import pl.gooradev.library.io.file.FileManagerBuilder;
import pl.gooradev.library.io.file.FileType;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class SaveControl {
    Library library;
    ConsolePrinter consolePrinter;
    DataReader dataReader;

    FileManager fileManager;

    int optionInt;
    SaveOption saveOption;


    public SaveControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader){
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
    }

    public void manageSaveLoop() {
        do {

            try{
                refreshLibrary();
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
            case CSV_SAVE:
                saveData(saveOption.getFileType());
                break;
            case CSV_BY_PATH_SAVE:
                saveData(saveOption.getFileType(), dataReader, consolePrinter);
                break;
            case BACK:
                break;
        }
    }

    private void saveData(FileType fileType){
        fileManager = FileManagerBuilder.getFileManager(fileType, consolePrinter);
        fileManager.exportData(library);
    }

    private void saveData(FileType fileType, DataReader dataReader, ConsolePrinter consolePrinter){
        fileManager = FileManagerBuilder.getFileManager(fileType, consolePrinter);
        fileManager.setDataPath(dataReader, consolePrinter);
        fileManager.exportData(library);
    }


    private void printOptions(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(SaveOption.SERIAL_SAVE);
        consolePrinter.printLine(SaveOption.CSV_SAVE);
        consolePrinter.printLine(SaveOption.CSV_BY_PATH_SAVE);
        consolePrinter.printLine(SaveOption.BACK);
    }

    private SaveOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return SaveOption.createFromInt(optionInt);
    }

    private void refreshLibrary(){
        library = LibraryControl.getLibrary();
    }
}
