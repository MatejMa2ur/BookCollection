package GUI;

import Models.Book;
import Services.BookService;
import UnitOfWork.UnitOfWork;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private BookService bookService = new BookService(new UnitOfWork());
    @FXML private MFXButton btnDeleteBook, btnAddBook;
    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> colTitle, colAuthor, colPublisher, colISBN, colRelease;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colPublisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colRelease.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));

        refreshItems();
    }

    public void editBook(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = openWindow("/GUI/AddBook.fxml");
        NewBookController  newBookController = fxmlLoader.getController();
        newBookController.setBookService(bookService);
        newBookController.fillData(tableView.getSelectionModel().getSelectedItem());
    }

    public void addBook(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = openWindow("/GUI/AddBook.fxml");
        NewBookController  newBookController = fxmlLoader.getController();
        newBookController.setBookService(bookService);
    }

    public FXMLLoader openWindow(String url) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Book collection");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initModality(Modality.WINDOW_MODAL);

        Window window = scene.getWindow();
        window.setOnHiding(event -> refreshItems());
        stage.show();

        return fxmlLoader;
    }

    private void refreshItems() {
        tableView.refresh();
        tableView.setItems(FXCollections.observableArrayList(bookService.getAllBooks()));
    }

    public void deleteBook(ActionEvent actionEvent) {
        bookService.deleteBook(tableView.getSelectionModel().getSelectedItem().getId());
        refreshItems();
    }
}
