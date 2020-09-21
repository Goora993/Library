package pl.gooradev.library.control.publication.info_publication.info_magazine;

public enum InfoMagazineOption {
    SORT_BY_NAME(1, "Wyświetl posortowane według nazwy"),
    SORT_BY_PUBLISHER(2, "Wyświetl posortowane według wydawnictwa"),
    BACK(0, "Wróc do poprzedniego menu");

    private int value;
    private String description;


    InfoMagazineOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    static InfoMagazineOption createFromInt(int opt) {
        InfoMagazineOption[] options = InfoMagazineOption.values();
        InfoMagazineOption optionToReturn = null;
        for (InfoMagazineOption option : options) {
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
