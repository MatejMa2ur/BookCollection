package Repositories;

import Models.Category;
import Repositories.Interfaces.ICategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    private String _url;
    public CategoryRepository(String url) {
        _url = url;
    }
    public List<Category> getCategories() {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "SELECT * FROM category;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            var rs = preparedStatement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
            return categories;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    public Category getCategory(int id) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "SELECT * FROM category WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            var rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return new Category(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    public Category getCategory(String name) {
        try (Connection conn = DriverManager.getConnection(_url)){
            String sql = "SELECT * FROM category WHERE name = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            var rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return new Category(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
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
