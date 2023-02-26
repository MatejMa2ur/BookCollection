package Services;

import Models.Category;
import Repositories.Interfaces.ICategoryRepository;
import Services.Interfaces.ICategoryService;
import UnitOfWork.UnitOfWork;

import java.util.List;

public class CategoryService implements ICategoryService {
    private ICategoryRepository categoryRepository;
    public CategoryService(UnitOfWork unitOfWork) {
        this.categoryRepository = unitOfWork.getCategoryRepository();
    }
    public void addCategory(String name) {
        categoryRepository.addCategory(name);
    }
    public Category getCategory(int id) {
        return categoryRepository.getCategory(id);
    }
    public Category getCategory(String name) {
        return categoryRepository.getCategory(name);
    }
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }
    public void addBookToCategory(int bookId, int categoryId) {
        categoryRepository.addBookToCategory(bookId, categoryId);
    }
}
