package Project;

public class Library {

    private Book[] books;
    private Client[] clients;

    public Library(Book[] books) {
        this.books = books;
        this.clients = new Client[Main.MAX_CLIENTS];
    }

    public Library() {
        this.books = new Book[Main.MAX_BOOKS];
        this.clients = new Client[Main.MAX_CLIENTS];
    }

    public Book[] getBooks() {
        return books;
    }

    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public String searchBook(String search) {
        String result = "";
        for (Book book : books) {
            if (book != null && (book.getTitle().toLowerCase().startsWith(search.toLowerCase()) || book.getTitle().toLowerCase().contains(search.toLowerCase()))) {
                result += book + "\n";
            }
        }
        try {
            int id = Integer.parseInt(search);
            Book book = this.getBookById(id);
            if (book == null) {
                // Since we reached this point, the user provided a number that is not a book id. So we shouldn't reutrn the result
                return "Book with id " + id + " not found";
            }
            return book.toString();
        } catch (NumberFormatException e) {
            return result;
        }
    }

    public String listBooks() {
        String result = "";
        for (Book book : books) {
            if (book != null) {
                result += book.toString() + "\n";
            }
        }
        return result;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Client[] getClients() {
        return clients;
    }

    public Client getClient(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    public String searchClient(String search) {
        String result = "";
        for (Client client : clients) {
            if (client != null && (client.getName().toLowerCase().startsWith(search.toLowerCase()) || client.getName().toLowerCase().contains(search.toLowerCase()))) {
                result += client + "\n";
            }
        }
        try {
            int id = Integer.parseInt(search);
            Client client = this.getClient(id);
            if (client == null) {
                // Since we reached this point, the user provided a number that is not a client id. So we shouldn't reutrn the result
                return "Client with id " + id + " not found";
            }
            return client.toString();
        } catch (NumberFormatException e) {
            return result;
        }
    }

    public String listClients() {
        String result = "";
        for (Client client : clients) {
            if (client != null) {
                result += client.toString() + "\n";
            }
        }
        return result;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    // Add a book to the library or add a copy if it already exists
    public void addBookOrCopy(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            } else if(books[i] == book) {
                books[i].addCopy();
                break;
            }
        }
    }

    // Remove a book from the library
    public void removeBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == book) {
                for (Client client : book.getBorrowers()) {
                    client.returnBook(book);
                }
                books[i] = null;
                break;
            }
        }
    }

    // Register a new client
    public void registerClient(Client client) {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] == null) {
                clients[i] = client;
                break;
            }
        }
    }

    // Register a new client with borrowed books
    public void registerClient(String name, Book[] borrowedBooks) {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] == null) {
                clients[i] = new Client(name, borrowedBooks);
                break;
            }
        }
    }

    // Remove a client from the library
    public void removeClient(Client client) {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] == client) {
                for (Book book : client.getBorrowedBooks()) {
                    book.setAvailableCopies(book.getAvailableCopies() + 1);
                    book.removeBorrower(client);
                }
                clients[i] = null;
                break;
            }
        }
    }

    // Borrow a book
    public void borrowBook(Client client, Book book) {
        for (int i = 0; i < client.getBorrowedBooks().length; i++) {
            if (client.getBorrowedBooks()[i] == null) {
                client.getBorrowedBooks()[i] = book;
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                book.addBorrower(client);
                break;
            }
        }
    }

    // Return a book
    public void returnBook(Client client, Book book) {
        for (int i = 0; i < client.getBorrowedBooks().length; i++) {
            if (client.getBorrowedBooks()[i] == book) {
                client.getBorrowedBooks()[i] = null;
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                book.removeBorrower(client);
                break;
            }
        }
    }
}
