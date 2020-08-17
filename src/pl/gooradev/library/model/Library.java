package pl.gooradev.library.model;


public class Library {
    private int maxBooks = 1;
    private Book[] books = new Book[maxBooks];
    private int booksNumber;

    public Library() {
    }

    public Library(int maxBooks, Book[] books, int booksNumber) {
        this.maxBooks = maxBooks;
        this.books = books;
        this.booksNumber = booksNumber;
    }

    public void addBook(Book book){
        if(maxBooks<=booksNumber){
            books = extendTab(books);
        }
        books[booksNumber] = book;
        booksNumber++;
    }
    
    private Book[] extendTab(Book[] books){
        maxBooks*=maxBooks*2;
        Book[] newBooks = new Book[maxBooks];
        for (int i = 0; i < books.length; i++) {
            newBooks[i] = books[i];
        }
        return newBooks;
    }

    public void printBooks() {
        if(booksNumber == 0) {
            System.out.println("Brak książek w bibliotece");
        }
        for(int i=0; i<booksNumber; i++) {
            System.out.println(books[i].toString());
        }
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public void setMaxBooks(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public int getBooksNumber() {
        return booksNumber;
    }

    public void setBooksNumber(int booksNumber) {
        this.booksNumber = booksNumber;
    }
}
