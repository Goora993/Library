package pl.gooradev.library.control.user;

public enum UserOption {
    ADD_USER(1, "Dodawanie użytkowników"),
    REMOVE_USER(2, "Usuwanie użytkowników"),
    BORROW_PUBLICATION(3, "Wypożyczanie publikacji"),
    RETURN_PUBLICATION(4, "Zwracanie publikacji"),
    PRINT_USER_BY_PESEL(5, "Wyświetalnie informacji o użytkowniku poprzez numer pesel"),
    PRINT_USER(6, "Wyświetlanie informacji o użytkownikach"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    UserOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static UserOption createFromInt(int opt) {
        UserOption[] options = UserOption.values();
        UserOption optionToReturn = null;
        for (UserOption option : options) {
            if (option.getValue() == opt)
                optionToReturn = option;
        }
        return optionToReturn;
    }

}
