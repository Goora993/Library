package pl.gooradev.library.model;

import pl.gooradev.library.exception.PublicationAlreadyExistException;
import pl.gooradev.library.exception.UserAlreadyExistException;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Library implements Serializable {
    private Map<String, User> users = new HashMap<>();
    private Map<Integer, Publication> publications = new HashMap<>();

    private int maxPublications = 10;
    private int publicationNumber;

    public Library() {
    }

    public void addUser(User user) throws UserAlreadyExistException {
        if(users.containsKey(user.getPesel())){
            throw new UserAlreadyExistException(
                    "Użytkownik o numerze pesel " + user.getPesel() + " już istnieje"
            );
        }
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

    public Collection<Publication> getPublicationsCollection(){
        return publications.values();
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

    public int getMaxPublications() {
        return maxPublications;
    }

    public void setMaxPublications(int maxPublications) {
        this.maxPublications = maxPublications;
    }

    public int getPublicationNumber() {
        return publicationNumber;
    }

    public void setPublicationNumber(int publicationNumber) {
        this.publicationNumber = publicationNumber;
    }
}
