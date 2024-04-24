package java13.service;

import java13.model.Book;

import java.util.List;

public interface BookService {
    void createTableBook();
    String saveBook(Book book);
    Book getBookById(Long id);
    List<Book> getBooks();
    Book updateBookById(Long olId,Book newBook);
    String deleteBookById(Long bookId);

    // dop methods
    List<Book> getAllBookByAuthorId(Long authorId);
    List<Book> sortedBookByPrice();
    void deleteBooksFromAuthorId(Long authorId);
}
