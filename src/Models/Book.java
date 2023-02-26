package Models;

public class Book {
    private int Id;
    private String Title;
    private String Author;
    private String Publisher;
    private String ISBN;
    private String publicationYear;
    public Book(String title, String author, String publisher, String ISBN, String releaseYear) {
        this.Title = title;
        this.Author = author;
        this.Publisher = publisher;
        this.ISBN = ISBN;
        this.publicationYear = releaseYear;
    }
    public Book(int id, String title, String author, String publisher, String ISBN, String releaseYear) {
        this.Id = id;
        this.Title = title;
        this.Author = author;
        this.Publisher = publisher;
        this.ISBN = ISBN;
        this.publicationYear = releaseYear;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
}
