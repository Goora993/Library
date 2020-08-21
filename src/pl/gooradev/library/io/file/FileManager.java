package pl.gooradev.library.io.file;

import pl.gooradev.library.model.Library;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}
