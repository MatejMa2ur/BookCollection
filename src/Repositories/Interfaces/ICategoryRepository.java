package Repositories.Interfaces;

import java.sql.ResultSet;

public interface ICategoryRepository {
    ResultSet getCategories();
    ResultSet getCategory(int id);
    ResultSet getCategory(String name);
    void addCategory(String name);
    void deleteCategory(int id);
    void updateCategory(int id, String name);
}
