package pl.gooradev.library.util;

import pl.gooradev.library.model.Publication;

import java.util.Random;

public class IdGenerator {
    Random random = new Random();


    public int createId(Publication[] publications){
        int id;
        boolean exist = true;
        do{
            id = random.nextInt(999999999);

            for (Publication publication : publications) {
                if(publication.getId() == id)
                    exist = false;
            }

        } while(!exist);
        return id;
    }
}
