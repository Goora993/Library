package pl.gooradev.library.io.print;

import pl.gooradev.library.exception.NoUserWithSuchPeselException;
import pl.gooradev.library.model.Library;
import pl.gooradev.library.model.LibraryUser;
import pl.gooradev.library.model.Publication;
import pl.gooradev.library.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

abstract class UserPrinter {

    public static String printAllUsers(Library library) {
        StringBuffer sb = new StringBuffer();
        Collection<User> userCollection = library.getUsers().values();
        List<User> userList = new ArrayList<>(userCollection);


        for (int i = 0; i < userList.size(); i++) {

            sb.append(userList.get(i).toString() + "\n");
        }

        if (userList.size() == 0) {
            return "Brak zarejestrowanych użytkowników";
        }

        return sb.toString();
    }

    public static String printUserByPesel(Library library, String pesel) throws NoUserWithSuchPeselException {
        User user = library.getUsers().get(pesel);
        StringBuffer sb = new StringBuffer();

        if(user!=null){
            return "Użytkownik: " + user + "\n" +
            printBorrowedList(user) + "\n" +
            printBorrowedHistory(user);
        } else {
            throw new NoUserWithSuchPeselException("Brak użytkownika o numerze pesel " + pesel);
        }
    }

    private static String printBorrowedList(User user){
        StringBuffer sb = new StringBuffer();
        List<Publication> borrowedList = null;

        if(user instanceof LibraryUser) {
            borrowedList = ((LibraryUser) user).getBorrowedPublications();
        }

        sb.append("Lista wypożyczonych publikacji: \n");

        if(borrowedList.size()==0){
            sb.append("Brak wypożyczonych publikacji");
        }

        for (Publication publication : borrowedList) {
            sb.append(publication + "\n");
        }

        return sb.toString();
    }

    private static String printBorrowedHistory(User user){
        StringBuffer sb = new StringBuffer();
        List<Publication> borrowedHistory = null;

        if(user instanceof LibraryUser) {
            borrowedHistory = ((LibraryUser) user).getPublicationHistory();
        }

        sb.append("Historia wypożyczonych publikacji: \n");

        if(borrowedHistory.size()==0){
            sb.append("Brak wypożyczonych publikacji w historii");
        }

        for (Publication publication : borrowedHistory) {
            sb.append(publication + "\n");
        }

        return sb.toString();
    }

}
