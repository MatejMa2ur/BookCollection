package Repositories;

import Models.Book;
import Repositories.Interfaces.IBookRepository;

import java.sql.*;

public class BookRepository implements IBookRepository {
    private String _url;
    public BookRepository(String url) {
        _url = url;
    }
    public void addBook(Book book) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "INSERT INTO book(name,author,publicationYear,ISBN,publisher) VALUES(?,?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublicationYear());
            preparedStatement.setString(4, book.getISBN());
            preparedStatement.setString(5, book.getPublisher());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void deleteBook(int id) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "DELETE FROM book WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void updateBook(Book book) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "UPDATE book SET name = ?, author = ?, publicationYear = ?, ISBN = ?, publisher = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublicationYear());
            preparedStatement.setString(4, book.getISBN());
            preparedStatement.setString(5, book.getPublisher());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public ResultSet getBooks() {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "SELECT * FROM book;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    public ResultSet getBook(int id) {
        try (Connection conn = DriverManager.getConnection(_url)) {
            String sql = "SELECT * FROM book WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
