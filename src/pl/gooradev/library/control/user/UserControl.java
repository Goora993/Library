package pl.gooradev.library.control.user;

import pl.gooradev.library.control.publication.PublicationOption;
import pl.gooradev.library.control.user.add_user.AddUserControl;
import pl.gooradev.library.control.user.borrow_publication.BorrowPublicationControl;
import pl.gooradev.library.control.user.info_user.InfoUserControl;
import pl.gooradev.library.control.user.remove_user.RemoveUserControl;
import pl.gooradev.library.control.user.return_publication.ReturnPublicationControl;
import pl.gooradev.library.exception.NoPublicationWithSuchId;
import pl.gooradev.library.exception.NoUserWithSuchPesel;
import pl.gooradev.library.exception.UserAlreadyExistException;
import pl.gooradev.library.io.ConsolePrinter;
import pl.gooradev.library.io.DataReader;
import pl.gooradev.library.model.Library;

import java.util.InputMismatchException;

public class UserControl {
    Library library;
    ConsolePrinter consolePrinter;
    DataReader dataReader;
    AddUserControl addUserControl;
    RemoveUserControl removeUserControl;
    InfoUserControl infoUserControl;
    BorrowPublicationControl borrowPublicationControl;
    ReturnPublicationControl returnPublicationControl;

    int optionInt;
    UserOption userOption;

    public UserControl(Library library, ConsolePrinter consolePrinter, DataReader dataReader){
        this.library = library;
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
        addUserControls();
    }

    private void addUserControls() {
        addUserControl = new AddUserControl(library, dataReader, consolePrinter);
        removeUserControl = new RemoveUserControl(library, dataReader, consolePrinter);
        infoUserControl = new InfoUserControl(library, dataReader, consolePrinter);
        borrowPublicationControl = new BorrowPublicationControl(library, dataReader, consolePrinter);
        returnPublicationControl = new ReturnPublicationControl(library, dataReader, consolePrinter);
    }

    public void manageUserLoop() {

        do{

            try {
                printOptions();
                userOption = getOption();
                manageUser(userOption);
            } catch (NullPointerException e){
                consolePrinter.printLine("Brak opcji o id " + optionInt + ", wybierz ponownie");
            } catch (InputMismatchException | UserAlreadyExistException  | NoUserWithSuchPesel |
                    NoPublicationWithSuchId e){
                consolePrinter.printLine(e.getMessage() + ", wybierz ponownie");
            }

        } while(userOption!=UserOption.BACK);
    }

    private void manageUser(UserOption userOption) throws NullPointerException, InputMismatchException,
            UserAlreadyExistException, NoUserWithSuchPesel, NoPublicationWithSuchId {

            switch (userOption) {
                case ADD_USER:
                    addUserControl.addUser();
                    break;
                case REMOVE_USER:
                    removeUserControl.removeUser();
                    break;
                case BORROW_PUBLICATION:
                    borrowPublicationControl.borrowPublication();
                    break;
                case RETURN_PUBLICATION:
                    returnPublicationControl.returnPublication();
                    break;
                case PRINT_USER_BY_PESEL:
                    infoUserControl.printUserByPesel();
                    break;
                case PRINT_USER:
                    infoUserControl.printUsers();
                    break;
                case BACK:
                    break;
            }

    }

    private void printOptions() {
        consolePrinter.printLine("Wybierz opcję: ");
        consolePrinter.printLine(UserOption.ADD_USER);
        consolePrinter.printLine(UserOption.REMOVE_USER);
        consolePrinter.printLine(UserOption.BORROW_PUBLICATION);
        consolePrinter.printLine(UserOption.RETURN_PUBLICATION);
        consolePrinter.printLine(UserOption.PRINT_USER_BY_PESEL);
        consolePrinter.printLine(UserOption.PRINT_USER);
        consolePrinter.printLine(PublicationOption.BACK);
    }

    private UserOption getOption() throws InputMismatchException {
        optionInt = dataReader.getInt();
        return UserOption.createFromInt(optionInt);
    }
}
