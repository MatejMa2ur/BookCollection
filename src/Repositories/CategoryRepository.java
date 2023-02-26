package Repositories;

import Repositories.Interfaces.ICategoryRepository;

import java.sql.*;

public class CategoryRepository implements ICategoryRepository {
    private String _url;
    public CategoryRepository(String url) {
        _url = url;
    }
    public ResultSet getCategories() {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "SELECT * FROM category;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    public ResultSet getCategory(int id) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "SELECT * FROM category WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    public ResultSet getCategory(String name) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "SELECT * FROM category WHERE name = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    public void addCategory(String name) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "INSERT INTO category(name) VALUES(?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void deleteCategory(int id) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "DELETE FROM category WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void updateCategory(int id, String name) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "UPDATE category SET name = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void addBookToCategory(int bookId, int categoryId) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "INSERT INTO categoryBookConnect(bookId,categoryId) VALUES(?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void deleteBookFromCategory(int bookId, int categoryId) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "DELETE FROM categoryBookConnect WHERE bookId = ? AND categoryId = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
