import Models.Book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Models.Category;
import Repositories.Interfaces.IBookRepository;
import Services.CategoryService;
import Services.Interfaces.IBookService;
import Services.Interfaces.ICategoryService;
import UnitOfWork.UnitOfWork;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        Application.launch();

        /*
        var unitOfWork = new UnitOfWork();
        IBookService BookService = new Services.BookService(unitOfWork);
        ICategoryService CategoryService = new Services.CategoryService(unitOfWork);
        //SeedBooks(BookService,CategoryService);
        //SeedCategories(CategoryService);
        while (true) {
            System.out.println("\nThe book collection");
            System.out.println("====================================");
            System.out.println("\tFor adding a new book, type 'add'");
            System.out.println("\tFor listing all books, type 'list'");
            System.out.println("\tFor adding a new category, type 'add category'");
            System.out.println("\tFor listing all categories, type 'list categories'");
            System.out.println("====================================");
            System.out.println("Type 'exit' to exit the program");

            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            String input = reader.readLine();
            switch (input) {
                case "add":
                    System.out.println("\nType the name of the book");
                    String bookName = reader.readLine();
                    System.out.println("Type the author of the book");
                    String bookAuthor = reader.readLine();
                    System.out.println("Type the publication year of the book");
                    String bookPublicationYear = reader.readLine();
                    System.out.println("Type the ISBN of the book");
                    String bookISBN = reader.readLine();
                    System.out.println("Type the publisher of the book");
                    String bookPublisher = reader.readLine();
                    var bookId = BookService.addBook(bookName, bookAuthor, bookPublicationYear, bookISBN, bookPublisher);
                    System.out.println("Type the category of the book from the following list (write the names separated by commas, if there are none write them as text separated by commas):");
                    List<Category> categories = CategoryService.getCategories();
                    for (Category category : categories) {
                        System.out.println(category.getName());
                    }
                    String bookCategory = reader.readLine();
                    for (String bookCategoryPart : bookCategory.split(",")) {
                        if(CategoryService.getCategory(bookCategoryPart) == null)
                            unitOfWork.getCategoryRepository().addCategory(bookCategoryPart);
                        var category = CategoryService.getCategory(bookCategoryPart);
                        CategoryService.addBookToCategory(bookId, category.getId());
                    }
                    break;
                case "list":
                    System.out.println("\nList of books:");
                    List<Book> books = BookService.getAllBooks();
                    for (Book book : books) {
                        System.out.println(book.getTitle()+"; "+book.getAuthor()+"; "+book.getPublisher()+"; "+book.getISBN());
                    }
                    break;
                case "add category":
                    System.out.println("\nType the name of the category");
                    String categoryName = reader.readLine();
                    if(CategoryService.getCategory(categoryName) != null)
                        System.out.println("Category already exists");
                    else
                        CategoryService.addCategory(categoryName);
                    break;
                case "list categories":
                    System.out.println("\nList of categories:");
                    List<Category> bookCategories = CategoryService.getCategories();
                    for (Category category : bookCategories) {
                        System.out.println(category.getName());
                    }
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
         */

    }
    public static void SeedBooks(IBookService BookService, ICategoryService CategoryService)
    {
        var id = BookService.addBook("The Lord of the Rings", "J.R.R. Tolkien", "Allen & Unwin", "978-0-04-823993-3", "1954");
        CategoryService.addBookToCategory(id, 1);
        CategoryService.addBookToCategory(id, 2);
        CategoryService.addBookToCategory(id, 3);
        id = BookService.addBook("The Hobbit", "J.R.R. Tolkien", "Allen & Unwin", "978-0-04-823993-3", "1937");
        CategoryService.addBookToCategory(id, 1);
        CategoryService.addBookToCategory(id, 2);
        id = BookService.addBook("The Silmarillion", "J.R.R. Tolkien", "Allen & Unwin", "978-0-04-823993-3", "1977");
        CategoryService.addBookToCategory(id, 2);
        CategoryService.addBookToCategory(id, 3);
        BookService.addBook("The Fellowship of the Ring", "J.R.R. Tolkien", "Allen & Unwin", "978-0-04-823993-3", "1954");
        BookService.addBook("The Two Towers", "J.R.R. Tolkien", "Allen & Unwin", "978-0-04-823993-3", "1954");
        BookService.addBook("The Return of the King", "J.R.R. Tolkien", "Allen & Unwin", "978-0-04-823993-3", "1955");
        BookService.addBook("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "Pan Books", "978-0-04-823993-3", "1979");
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GUI/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Book collection");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}