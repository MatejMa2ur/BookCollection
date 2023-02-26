import Models.Book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var unitOfWork = new UnitOfWork();
        List<String> bookLines = new ArrayList<String>();
        try{
            bookLines = Files.readAllLines(Path.of("data/book_titles.txt"));
        }
        catch (IOException e){
            bookLines = new ArrayList<String>();
        }
        List<String> bookCategories = new ArrayList<String>();
        try{
            bookCategories = Files.readAllLines(Path.of("data/book_categories.txt"));
        }
        catch (IOException e){
            bookCategories = new ArrayList<String>();
        }
        while (true) {
            System.out.println("The book collection");
            System.out.println("====================================");
            System.out.println("For adding a new book, type 'add'");
            System.out.println("For removing a book, type 'remove'");
            System.out.println("For listing all books, type 'list'");
            System.out.println("For adding a new category, type 'add category'");
            System.out.println("For removing a category, type 'remove category'");
            System.out.println("For listing all categories, type 'list categories'");
            System.out.println("====================================");
            System.out.println("Type 'exit' to exit the program");

            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            String input = reader.readLine();
            switch (input) {
                case "add":
                    System.out.println("Type the name of the book");
                    String bookName = reader.readLine();
                    System.out.println("Type the author of the book");
                    String bookAuthor = reader.readLine();
                    System.out.println("Type the publication year of the book");
                    String bookPublicationYear = reader.readLine();
                    System.out.println("Type the ISBN of the book");
                    String bookISBN = reader.readLine();
                    System.out.println("Type the publisher of the book");
                    String bookPublisher = reader.readLine();
                    Book book = new Book(bookName, bookAuthor, bookPublicationYear, bookISBN, bookPublisher);
                    System.out.println("Type the category of the book from the following list (write IDs separated by comma, if there are none write them as text separated by commas):");
                    for (String bookCategory : bookCategories) {
                        System.out.println(bookCategory);
                    }
                    String bookCategory = reader.readLine();
                    for (String bookCategoryPart : bookCategory.split(",")) {
                        var a = unitOfWork.getCategoryRepository().getCategory(bookCategoryPart);
                        if(unitOfWork.getCategoryRepository().getCategory(bookCategoryPart) == null)
                            unitOfWork.getCategoryRepository().addCategory(bookCategoryPart);
                        String category = bookCategories.size()+1+";"+bookCategoryPart;
                        bookCategories.add(category);
                        Files.write(Path.of("data/book_categories.txt"), bookCategories);
                    }
                    bookLines.add(bookName + ";" + bookAuthor + ";" + bookCategory+ ";" + bookPublicationYear + ";" + bookISBN + ";" + bookPublisher);
                    Files.write(Path.of("data/book_titles.txt"), bookLines);
                    break;
                case "remove":
                    System.out.println("Type the name of the book");
                    String bookNameToRemove = reader.readLine();
                    for (int i = 0; i < bookLines.size(); i++) {
                        String[] bookParts = bookLines.get(i).split(";");
                        if (bookParts[0].equals(bookNameToRemove)) {
                            bookLines.remove(i);
                            break;
                        }
                    }
                    Files.write(Path.of("data/book_titles.txt"), bookLines);
                    break;
                case "list":
                    for (String bookLine : bookLines) {
                        String[] bookParts = bookLine.split(";");
                        System.out.println(bookParts[0] + "; " + bookParts[1] + "; " + bookParts[2]);
                    }
                    break;
                case "add category":
                    System.out.println("Type the name of the category");
                    String categoryName = reader.readLine();
                    //add Id
                    String lastLine = bookCategories.get(bookCategories.size() - 1);
                    bookCategories.add(lastLine+";"+categoryName);
                    Files.write(Path.of("data/book_categories.txt"), bookCategories);
                    break;
                case "list categories":
                    System.out.println("List of categories:");
                    for (String bookCategoryLine : bookCategories) {
                        String[] bookCategoryParts = bookCategoryLine.split(";");
                        System.out.println(bookCategoryParts[0] + "; " + bookCategoryParts[1]);
                    }
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }

    }
}