package pl.gooradev.library.control.sl;

public enum SaveLoadOption {

    SAVE_MENU(1, "Menu zapisywania danych"),
    LOAD_MENU(2, "Menu odczytu danych"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;

    SaveLoadOption(int value, String desc){
        this.value = value;
        this.description = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static SaveLoadOption createFromInt(int opt) {
        SaveLoadOption[] options = SaveLoadOption.values();
        SaveLoadOption optionToReturn = null;
        for (SaveLoadOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }

}
