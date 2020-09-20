package pl.gooradev.library.control.publication.add_publication;


public enum AddPublicationOption {
    ADD_BOOK(1, "Dodawanie książki"),
    ADD_MAGAZINE(2, "Dodawanie magazynu/gazety"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;


    AddPublicationOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    static AddPublicationOption createFromInt(int opt) {
        AddPublicationOption[] options = AddPublicationOption.values();
        AddPublicationOption optionToReturn = null;
        for (AddPublicationOption option : options) {
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

    @Override
    public String toString() {
        return value + " - " + description;
    }

}
