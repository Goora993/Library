package pl.gooradev.library.control.user.remove_user;

import pl.gooradev.library.exception.NoUserWithSuchPesel;
import pl.gooradev.library.io.print.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

public class RemoveUserControl {
    Library library;
    DataReader dataReader;
    ConsolePrinter consolePrinter;

    public RemoveUserControl(Library library, DataReader dataReader, ConsolePrinter consolePrinter) {
        this.library = library;
        this.dataReader = dataReader;
        this.consolePrinter = consolePrinter;
    }


    public void removeUser() throws NoUserWithSuchPesel {
        consolePrinter.printLine("Podaj pesel użytkownika, którego chcesz usunąć: ");
        String pesel = dataReader.getString();
        boolean removed;

        removed = library.removeUserByPesel(pesel);

        if(removed){
            consolePrinter.printLine("Pomyślnie usunięto użytkownika o numerze pesel " + pesel);
        }
    }
}
