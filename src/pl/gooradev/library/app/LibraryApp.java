package pl.gooradev.library.app;

public class LibraryApp {
    public static final String APP_NAME = "Biblioteka v1.0";

    public static void main(String[] args) {
        System.out.println(APP_NAME);
        LibraryControl lc = new LibraryControl();
        lc.mainLoop();
    }
}
