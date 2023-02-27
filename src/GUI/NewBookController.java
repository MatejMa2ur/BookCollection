package GUI;

import Models.Book;
import Services.BookService;
import UnitOfWork.UnitOfWork;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewBookController implements Initializable {
    private BookService bookService;
    private boolean isEditing;
    private Book editingBook;
    @FXML private TextField txtTitle, txtAuthor, txtPublisher, txtISBN, txtPublicationYear;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isEditing = false;
        txtPublicationYear.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                txtPublicationYear.setText(oldValue);
            }
        });
    }

    public void handleSave(ActionEvent actionEvent) {
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String publisher = txtPublisher.getText().trim();
        String ISBN = txtISBN.getText().trim();
        int publicationYear = Integer.parseInt(txtPublicationYear.getText().trim());

        if (txtTitle.getText().isEmpty() || txtAuthor.getText().isEmpty()
                || txtPublisher.getText().isEmpty()){
            txtTitle.setPromptText("Field must not be empty!");
            txtAuthor.setPromptText("Field must not be empty!");
            txtPublisher.setPromptText("Field must not be empty!");
        }
        else {
            if (isEditing){
                bookService.editBook(editingBook.getId(), title, author, publisher, ISBN, String.valueOf(publicationYear));
            }
            else {
                bookService.addBook(title, author, publisher, ISBN, String.valueOf(publicationYear));
            }
            Node node = (Node) actionEvent.getSource();
            node.getScene().getWindow().hide();
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        node.getScene().getWindow().hide();
    }

    public void fillData(Book book){
        isEditing = true;
        this.editingBook = book;
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(String.valueOf(book.getAuthor()));
        txtPublisher.setText(book.getPublisher());
        txtISBN.setText(book.getISBN());
        txtPublicationYear.setText(String.valueOf(Integer.parseInt(book.getPublicationYear())));
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
