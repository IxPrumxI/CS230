package Project;

public class Book {

    private static int currentId = 0;
    private int id;
    private String title;
    private String author;
    private int availableCopies;
//    private Client[] borrowers = new Client[Main.MAX_CLIENTS]; // This was causing an issue because borrowers.length was always 100
    private Client[] borrowers = new Client[0]; // This is a better approach, we will resize the array when needed

    public Book(String title, String author, int availableCopies) {
        this.id = currentId++;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
        // ,
    }

    public Book(String title, String author) {
        this.id = currentId++;
        this.title = title;
        this.author = author;
        this.availableCopies = 1;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Client[] getBorrowers() {
        return borrowers;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    // It's better to check the number of available copies instead of a boolean
    public boolean getIsAvailable() {
        return availableCopies > 0;
    }

//    public void setIsAvailable(boolean isAvailable) {
//        this.isAvailable = isAvailable;
//    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void addCopy() {
        availableCopies++;
    }

    public void addBorrower(Client client) {
        Client[] newBorrowers = new Client[borrowers.length + 1];
        // Copy the old borrowers to the new array
        for (int i = 0; i < borrowers.length; i++) {
            newBorrowers[i] = borrowers[i];
        }
        newBorrowers[borrowers.length] = client;
        borrowers = newBorrowers;
    }

    public void removeBorrower(Client client) {
        Client[] newBorrowers = new Client[borrowers.length - 1];
        int j = 0;
        for (int i = 0; i < borrowers.length; i++) {
            if (borrowers[i] != client) {
                newBorrowers[j++] = borrowers[i];
            }
        }
        borrowers = newBorrowers;
    }

    public String toString() {
        return "Book " + id + ": " + title + " by " + author + " (" + availableCopies + " copies available) - Borrowed by " + borrowers.length + " clients.";
    }
}
