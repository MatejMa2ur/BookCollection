package Repositories.Interfaces;

import Models.Category;

import java.sql.ResultSet;
import java.util.List;

public interface ICategoryRepository {
    List<Category> getCategories();
    Category getCategory(int id);
    Category getCategory(String name);
    void addCategory(String name);
    void deleteCategory(int id);
    void updateCategory(int id, String name);
    void addBookToCategory(int bookId, int categoryId);
}
