package pl.gooradev.library.control.sl.load;


import pl.gooradev.library.io.file.FileType;

public enum LoadOption {
    SERIAL_LOAD(1, "Wczytywanie ostatniego stanu danych", FileType.SERIAL),
    CSV_LOAD(2, "Wczytywanie danych z pliku CSV", FileType.CSV),
    CSV_BY_PATH_LOAD(3, "Wczytywanie danych z pliku CSV z wybranego folderu", FileType.CSV),
    BACK(0, "Wróc do poprzedniego menu");


    private int value;
    private String description;
    private FileType fileType;


    LoadOption(int value, String description){
        this.value = value;
        this.description = description;
    }

    LoadOption(int value, String description, FileType fileType){
        this.value = value;
        this.description = description;
        this.fileType = fileType;
    }


    static LoadOption createFromInt(int opt) {
        LoadOption[] options = LoadOption.values();
        LoadOption optionToReturn = null;
        for (LoadOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }


    public int getValue() {
        return value;
    }


    public String getDescription() {
        return description;
    }

    public FileType fileType(){
        return fileType;
    }


    @Override
    public String toString() {
        return value + " - " + description;
    }

}
