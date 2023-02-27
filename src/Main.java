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