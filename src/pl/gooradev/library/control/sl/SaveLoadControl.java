package pl.gooradev.library.control.sl;

import pl.gooradev.library.control.sl.load.LoadControl;
import pl.gooradev.library.control.sl.save.SaveControl;
import pl.gooradev.library.exception.NoSuchOptionException;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class SaveLoadControl {
    Library library;
    ConsolePrinter consolePrinter;
    DataReader dataReader;

    int optionInt;
    SaveLoadOption saveLoadOption;

    SaveControl saveControl;
    LoadControl loadControl;

    public SaveLoadControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader){
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
        saveControl = new SaveControl(library, consolePrinter, dataReader);
        loadControl = new LoadControl(library, consolePrinter, dataReader);
    }

    public void manageSaveLoadLoop() throws NoSuchOptionException {
        do {
            printOptions();
            saveLoadOption = getOption();
            manageSaveLoad(saveLoadOption);
        } while (saveLoadOption!=SaveLoadOption.BACK);
    }


    private void manageSaveLoad(SaveLoadOption saveLoadOption) throws NoSuchOptionException {
        try{
            switch (saveLoadOption){
                case SAVE_MENU:
                    saveControl.manageSaveLoop();
                    break;
                case LOAD_MENU:
                    loadControl.manageLoadLoop();
                    break;
                case BACK:
                    break;
            }
        } catch (NullPointerException e){
            throw new NoSuchOptionException("Brak opcji o id " + optionInt);
        }
    }

    private void printOptions(){
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(SaveLoadOption.SAVE_MENU);
        consolePrinter.printLine(SaveLoadOption.LOAD_MENU);
        consolePrinter.printLine(SaveLoadOption.BACK);
    }

    private SaveLoadOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return SaveLoadOption.createFromInt(optionInt);
    }
}
