package Project;

public class Client {
    private static int currentId = 0;

    private int id;
    private String name;
    private Book[] borrowedBooks;

    public Client(String name, Book[] borrowedBooks) {
        this.id = currentId++;
        this.name = name;
        this.borrowedBooks = borrowedBooks;
    }

    public Client(String name) {
        this.id = currentId++;
        this.name = name;
        this.borrowedBooks = new Book[Main.MAX_BOOKS];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }

    // Borrow a book
    public void borrowBook(Book book) {
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] == null) {
                borrowedBooks[i] = book;
                break;
            }
        }
    }

    // Return a book
    public void returnBook(Book book) {
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] == book) {
                borrowedBooks[i] = null;
                break;
            }
        }
    }

    public String toString(){
        return "Client " + id + " borrowed " + borrowedBooks.length + " books";
    }
}
