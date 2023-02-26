package Repositories.Interfaces;

import Models.Book;

import java.sql.ResultSet;
import java.util.List;

public interface IBookRepository {
    int addBook(Book book);
    void deleteBook(int id);
    void updateBook(Book book);
    List<Book> getBooks();
    Book getBook(int id);
}
