package mate.academy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import mate.academy.dao.BookDao;
import mate.academy.lib.Injector;
import mate.academy.model.Book;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        BookDao bookDao = (BookDao) injector.getInstance(BookDao.class);

        //add a new book;
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setPrice(new BigDecimal(60.60));
        bookDao.create(book);
        Long id = book.getId();
        System.out.println("Book was added, id: " + id + System.lineSeparator());

        //get book by id. I want to find the same book that was created earlier;
        Optional optional = bookDao.findById(id);
        System.out.println("Book found by id: ");
        System.out.println(optional.orElse("Cannot find book") + System.lineSeparator());

        //get all books;
        List<Book> bookList = bookDao.findAll();
        System.out.println("List of books: ");
        for (Book b: bookList) {
            System.out.println(b);
        }
        System.out.println(System.lineSeparator());

        //update a value for the book that I created before with different price;
        book.setPrice(new BigDecimal(10.00));
        System.out.println("Updated book: ");
        System.out.println(bookDao.update(book) + System.lineSeparator());

        //delete book by ID. I want to delete a book with some ID;
        System.out.println("Book was deleted? " + bookDao.deleteById(id) + System.lineSeparator());

        //After all manipulation
        System.out.println("List of books: ");
        for (Book b: bookDao.findAll()) {
            System.out.println(b);
        }
    }
}
