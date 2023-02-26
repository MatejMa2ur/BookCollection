package UnitOfWork;

import Repositories.*;
import Repositories.Interfaces.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UnitOfWork {
    private IBookRepository BookRepository;
    private ICategoryRepository CategoryRepository;
    private String _connection;
    public UnitOfWork() {
        _connection = "jdbc:sqlite:Database\\Library.db";
        openConnection();
        BookRepository = new BookRepository(_connection);
        CategoryRepository =  new CategoryRepository(_connection);
    }
    public void openConnection() {
        try (Connection conn = DriverManager.getConnection(_connection)){
            String sql = "CREATE TABLE IF NOT EXISTS book (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	title text NOT NULL,\n"
                    + "	author text NOT NULL,\n"
                    + " publicationYear text NOT NULL,\n"
                    + " ISBN text NOT NULL,\n"
                    + " publisher text NOT NULL\n"
                    + ");";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            sql = "CREATE TABLE IF NOT EXISTS category (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	name text NOT NULL\n"
                    + ");";
            stmt = conn.createStatement();
            stmt.execute(sql);
            sql = "CREATE TABLE IF NOT EXISTS categoryBookConnect (\n"
                    + "	categoryId integer NOT NULL,\n"
                    + "	bookId text NOT NULL,\n"
                    + " PRIMARY KEY(categoryId,bookId) \n"
                    + " );";
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public IBookRepository getBookRepository() {
        return BookRepository;
    }
    public ICategoryRepository getCategoryRepository() {
        return CategoryRepository;
    }
}
