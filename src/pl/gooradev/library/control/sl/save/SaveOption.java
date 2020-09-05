package pl.gooradev.library.control.sl.save;

public enum SaveOption {
    SERIAL_SAVE(1, "Zapisywanie akutalnych danych"),
    CSV_SAVE(2, "Zapisywanie akutalnych danych do pliku CSV"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    SaveOption(int value, String description){
        this.value = value;
        this.description = description;
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
