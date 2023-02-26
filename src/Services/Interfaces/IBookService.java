package Services.Interfaces;

import Models.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    int addBook(String title, String author, String publisher, String ISBN, String releaseYear);
}
