package pl.gooradev.library.control.sl.load;


public enum LoadOption {
    SERIAL_LOAD(1, "Wczytywanie ostatniego stanu danych"),
    CSV_LOAD(2, "Wczytywanie danych z pliku CSV"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    LoadOption(int value, String description){
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
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
}
