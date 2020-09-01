package pl.gooradev.library.io.file;

import pl.gooradev.library.model.Library;

public interface FileManager {
    Library importData();
    Library importData(ImportType importType);
    void exportData(Library library);
}
