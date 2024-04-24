package java13.service.serviceImpl;

import java13.dao.BookDao;
import java13.dao.daoImol.BookDaoImpl;
import java13.model.Book;
import java13.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void createTableBook() {
        bookDao.createTableBook();
    }

    @Override
    public String saveBook(Book book) {
        return bookDao.saveBook(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Book updateBookById(Long olId, Book newBook) {
        return bookDao.updateBookById(olId, newBook);
    }

    @Override
    public String deleteBookById(Long bookId) {
        return bookDao.deleteBookById(bookId);
    }

    @Override
    public List<Book> getAllBookByAuthorId(Long authorId) {
        return bookDao.getAllBookByAuthorId(authorId);
    }

    @Override
    public List<Book> sortedBookByPrice() {
        return bookDao.sortedBookByPrice();
    }

    @Override
    public void deleteBooksFromAuthorId(Long authorId) {
        bookDao.deleteBooksFromAuthorId(authorId);
    }
}
