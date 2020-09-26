package pl.gooradev.library.control.sl.save;

import pl.gooradev.library.io.file.FileType;

public enum SaveOption {
    SERIAL_SAVE(1, "Zapisywanie akutalnych danych", FileType.SERIAL),
    CSV_SAVE(2, "Zapisywanie akutalnych danych do pliku CSV", FileType.CSV),
    CSV_BY_PATH_SAVE(3, "Zapisywanie akutalnych danych do pliku CSV w wybranym folderze", FileType.CSV),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;
    private FileType fileType;


    SaveOption(int value, String description){
        this.value = value;
        this.description = description;
    }

    SaveOption(int value, String description, FileType fileType){
        this.value = value;
        this.description = description;
        this.fileType = fileType;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public FileType getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static SaveOption createFromInt(int opt) {
        SaveOption[] options = SaveOption.values();
        SaveOption optionToReturn = null;
        for (SaveOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }
}
