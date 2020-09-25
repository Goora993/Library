package pl.gooradev.library.model;

import pl.gooradev.library.exception.NoUserWithSuchPeselException;
import pl.gooradev.library.exception.PublicationAlreadyExistException;
import pl.gooradev.library.exception.UserAlreadyExistException;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Library implements Serializable {
    private Map<String, User> users = new HashMap<>();
    private Map<Integer, Publication> publications = new HashMap<>();


    public Library() {
    }


    public void addPublication(Publication publication) throws PublicationAlreadyExistException {
        int publicationId = publication.getId();
        if(publications.containsKey(publicationId)){
            throw new PublicationAlreadyExistException(
                    "Publikacja o takim ID już istnieje " + publicationId
            );
        }
        publications.put(publicationId, publication);
    }

    public boolean removePublication(Publication publication){
        if(publications.equals(publication)){
            publications.remove(publication);
            return true;
        }
        return false;
    }

    public boolean removePublication(int id){
        if(publications.containsKey(id)){
            publications.remove(id);
            return true;
        } else {
            throw new NullPointerException();
        }
    }


    public void addUser(User user) throws UserAlreadyExistException {
        if(users.containsKey(user.getPesel())){
            throw new UserAlreadyExistException(
                    "Użytkownik o numerze pesel " + user.getPesel() + " już istnieje"
            );
        } else{
            users.put(user.getPesel(), user);
        }
    }

    public boolean removeUserByPesel(String pesel) throws NoUserWithSuchPeselException {
        User userToRemove = getUserByPesel(pesel);
        return users.remove(pesel, userToRemove);

    }

    private User getUserByPesel(String pesel) throws NoUserWithSuchPeselException {
        if(users.containsKey(pesel)){
            return users.get(pesel);
        }
        throw new NoUserWithSuchPeselException(
                "Użytkownik o numerze pesel " + pesel + " nie istnieje"
        );
    }

    public Collection<Publication> getPublicationsCollection(){
        return publications.values();
    }

    public Collection<User> getUsersCollection(){
        return users.values();
    }


    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public Map<Integer, Publication> getPublications() {
        return publications;
    }

    public void setPublications(Map<Integer, Publication> publications) {
        this.publications = publications;
    }

}
