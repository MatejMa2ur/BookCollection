package Repositories;

import Models.Book;
import Repositories.Interfaces.IBookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private String _url;
    public BookRepository(String url) {
        _url = url;
    }
    public int addBook(Book book) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "INSERT INTO book(title,author,publicationYear,ISBN,publisher) VALUES(?,?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublicationYear());
            preparedStatement.setString(4, book.getISBN());
            preparedStatement.setString(5, book.getPublisher());
            preparedStatement.executeUpdate();
            return preparedStatement.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            System.out.println("Add book error: " + e.getMessage());
        }
        return -1;
    }
    public void deleteBook(int id) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "DELETE FROM book WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete book error: " + e.getMessage());
        }
    }
    public void updateBook(Book book) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "UPDATE book SET title = ?, author = ?, publicationYear = ?, ISBN = ?, publisher = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublicationYear());
            preparedStatement.setString(4, book.getISBN());
            preparedStatement.setString(5, book.getPublisher());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Update book error: " + e.getMessage());
        }
    }
    public List<Book> getBooks() {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "SELECT * FROM book;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("publisher"), rs.getString("ISBN"), rs.getString("publicationYear")));
            }
            return books;
        } catch (SQLException e) {
            System.out.println("Get books error: " + e.getMessage());
        }
        return null;
    }
    public Book getBook(int id) {
        try (Connection conn = DriverManager.getConnection(_url)) {
            String sql = "SELECT * FROM book WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return new Book(rs.getString("title"), rs.getString("author"), rs.getString("publisher"), rs.getString("ISBN"), rs.getString("publicationYear"));
        } catch (SQLException e) {
            System.out.println("Get book error: " + e.getMessage());
        }
        return null;
    }
}
