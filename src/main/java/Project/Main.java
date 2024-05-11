package Project;

import java.util.Scanner;

public class Main {
    public static final int MAX_BOOKS = 1000;
    public static final int MAX_CLIENTS = 100;
    public static Library library;
    // This is a static variable to keep track of the current client that is logged in, So we skip the login process till he logs out.
    public static Client currentClient;

    public static void main(String[] args) {
        library = new Library();
        generateSampleData();

        // Create a menu to interact with the library.
        // The menu should have the following options:
        // 1. Manage books (list all books and their availability with the option to search by title)
        // 2. Manage clients (list all clients and the books they borrowed with the option to search by name or id)
        // 3. Login as a client (list all books borrowed, borrow a book, return a book)
        // 4. Exit
        // The menu should keep asking for options until the user selects the exit option.

        System.out.println("Welcome to the library!");
        cli();
    }

    public static void cli() {
        try {
            Scanner scanner = new Scanner(System.in);
            int option;
            if(currentClient != null) option = 3; // To skip the login process
            else {
                System.out.println("Choose an option:");
                System.out.println("1. Manage books (list all books, search by title, register a new book, remove a book)");
                System.out.println("2. Manage clients (list all clients, search by name or id, register a new client, remove a client)");
                System.out.println("3. Login as a client (list all books borrowed, borrow a book, return a book)");
                System.out.println("4. Exit");
                option = scanner.nextInt();
            }
            // I tried a while loop but it gets stuck.
            switch (option) {
                case 1 -> { // Manage books (list all books and their availability with the option to search by title, remove a book)
                    System.out.println("1. List all books and their availability");
                    System.out.println("2. Search by title");
                    System.out.println("3. Register a new book");
                    System.out.println("4. Remove a book");
                    System.out.println("5. Exit");
                    int searchOption = scanner.nextInt();
                    if (searchOption == 1) {
                        System.out.println("List of books:\n" + library.listBooks());
                    } else if (searchOption == 2) {
                        System.out.println("Enter the title to search:");
                        String search = scanner.next();
                        System.out.println(library.searchBook(search));
                    } else if (searchOption == 3) {
                        System.out.println("Enter the title of the book:");
                        String title = scanner.next();
                        System.out.println("Enter the author of the book:");
                        String author = scanner.next();
                        System.out.println("Enter the number of copies:");
                        int copies = scanner.nextInt();
                        Book book = new Book(title, author, copies);
                        library.addBookOrCopy(book);
                        System.out.println("Book " + book.getTitle() + " registered with id " + book.getId());
                    } else if (searchOption == 4) {
                        System.out.println("List of books:\n" + library.listBooks());
                        System.out.println("Enter the id of the book to remove:");
                        int id = scanner.nextInt();
                        Book book = library.getBookById(id);
                        if (book != null) {
                            library.removeBook(book);
                        } else {
                            System.out.println("Book not found");
                        }
                    } else if (searchOption != 5) {
                        System.out.println("Invalid option");
                    }
                }
                case 2 -> { // Manage clients (list all clients and the books they borrowed with the option to search by name or id, register a new client, remove a client)
                    System.out.println("1. List all clients and the books they borrowed");
                    System.out.println("2. Search by name or id");
                    System.out.println("3. Register a new client");
                    System.out.println("4. Remove a client");
                    System.out.println("5. Exit");
                    int searchOption = scanner.nextInt();
                    if (searchOption == 1) {
                        library.listClients();
                    } else if (searchOption == 2) {
                        System.out.println("Enter the name to search:");
                        String search = scanner.next();
                        System.out.println(library.searchClient(search));
                    } else if (searchOption == 3) {
                        System.out.println("Enter the name of the client:");
                        String name = scanner.next();
                        Client client = new Client(name);
                        library.registerClient(client);
                        System.out.println("Client " + client.getName() + " registered with id " + client.getId());
                    } else if (searchOption == 4) {
                        System.out.println("List of clients:\n" + library.listClients());
                        System.out.println("Enter the id of the client to remove:");
                        int id = scanner.nextInt();
                        Client client = library.getClient(id);
                        if (client != null) {
                            library.removeClient(client);
                        } else {
                            System.out.println("Client not found");
                        }
                    } else if(searchOption != 5) System.out.println("Invalid option");
                }
                case 3 -> {
                    Client client;
                    if (currentClient == null) {
                        System.out.println("Enter the id of the client:");
                        int id = scanner.nextInt();
                        client = library.getClient(id);
                    } else {
                        client = currentClient;
                    }
                    if (client != null) {
                        if(currentClient != null) System.out.println("Welcome " + client.getName());
                        else currentClient = client;
                        System.out.println("1. List all books borrowed");
                        System.out.println("2. Borrow a book");
                        System.out.println("3. Return a book");
                        System.out.println("4. Logout (return to the main menu)");
                        System.out.println("5. Exit");
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            System.out.println("Books borrowed by " + client.getName() + "\n");
                            for (Book book : client.getBorrowedBooks()) {
                                if (book != null) {
                                    // We can't use the book.toString() method because it will show the availability of the book
                                    System.out.println("Book id: " + book.getId() + " Title: " + book.getTitle() + " Author: " + book.getAuthor() + "\n");
                                }
                            }
                        } else if (choice == 2) {
                            System.out.println("List of books:\n" + library.listBooks());
                            System.out.println("Enter the id or title of the book to borrow:");
                            String temp = scanner.next();
                            int bookId = -1;
                            String title = null;
                            try {
                                bookId = Integer.parseInt(temp);
                            } catch (NumberFormatException e) {
                                title = temp;
                            }
                            Book book;
                            if (bookId != -1) {
                                book = library.getBookById(bookId);
                            } else {
                                book = library.getBookByTitle(title);
                            }
                            if (book != null) {
                                if (book.getIsAvailable()) {
                                    library.borrowBook(client, book);
                                } else {
                                    System.out.println("Book not available");
                                }
                            } else {
                                System.out.println("Book not found");
                            }
                        } else if (choice == 3) {
                            int bookId;
                            Book book;
                            System.out.println("Books borrowed by " + client.getName() + "\n");
                            for (Book _book : client.getBorrowedBooks()) {
                                if (_book != null) {
                                    System.out.println("Book id: " + _book.getId() + " Title: " + _book.getTitle() + " Author: " + _book.getAuthor() + "\n");
                                }
                            }
                            System.out.println("Enter the id of the book to return:");
                            bookId = scanner.nextInt();
                            book = library.getBookById(bookId);
                            if (book != null) {
                                library.returnBook(client, book);
                            } else {
                                System.out.println("Book not found");
                            }
                        } else if (choice == 4) {
                            currentClient = null;
                            System.out.println("Successfully logged out");
                        } else if (choice == 5) {
                            System.out.println("Invalid option");
                        }
                    } else {
                        System.out.println("Client not found");
                    }
                }
                case 4 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option");
            }

        } catch (Exception e) {
            System.out.println("Invalid option");
            cli(); // Use recursion to keep asking for options
        }
        cli(); // Same as above
    }

    public static void generateSampleData(){
        for (int i = 0; i < 10; i++) {
            library.addBookOrCopy(new Book("Title" + i, "Author" + i, i + 1));
        }
        Book[] zeroCopies = new Book[3];
        for (int i = 0; i < 3; i++) {
            // Zero available copies
            zeroCopies[i] = new Book("Zero" + i, "Author" + i, 0);
            library.addBookOrCopy(zeroCopies[i]);
        }
        for (int i = 0; i < 3; i++) {
            library.registerClient(new Client("Client" + i));
        }
        for (int i = 0; i < 3; i++) {
            zeroCopies[i] = library.getBookById(i + 10);
        }
        for (int i = 0; i < 3; i++) {
            Client client = new Client("Client" + (i + 3), new Book[]{library.getBookById(i), zeroCopies[i]});
            library.getBookById(i).addBorrower(client);
            zeroCopies[i].addBorrower(client);
            library.registerClient(client);
        }
    }
}
