package Repositories.Interfaces;

import Models.Book;

import java.sql.ResultSet;

public interface IBookRepository {
    void addBook(Book book);
    void deleteBook(int id);
    void updateBook(Book book);
    ResultSet getBooks();
    ResultSet getBook(int id);
}
