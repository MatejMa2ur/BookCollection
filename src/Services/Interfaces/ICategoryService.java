package Services.Interfaces;

import Models.Category;

import java.util.List;

public interface ICategoryService {
    void addCategory(String name);
    Category getCategory(int id);
    Category getCategory(String name);
    List<Category> getCategories();
    void addBookToCategory(int bookId, int categoryId);
}
