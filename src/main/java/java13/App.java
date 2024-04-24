package java13;

import java13.model.Author;
import java13.model.Book;
import java13.service.AuthorService;
import java13.service.BookService;
import java13.service.serviceImpl.AuthorServiceImpl;
import java13.service.serviceImpl.BookServiceImpl;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AuthorService authorService = new AuthorServiceImpl();
        BookService bookService = new BookServiceImpl();
        while (true) {
            System.out.println("""
                    1. create table authors       10. create table books
                    2. save author                11. save book
                    3. get author by id           12. get book by id
                    4. get all authors            13. get all books
                    5. update author              14. update book
                    6. delete author by id        15. delete book
                    7. clean table author         16. get all books by author id
                    8. drop table author          17. sort books by price
                    9. sort authors by d.birth    18. delete books from authors id""");
            try {
                switch (new Scanner(System.in).nextInt()) {
                    case 1 -> authorService.createTableAuthor();
                    case 2 -> System.out.println(authorService.saveAuthor(new Author("Muktar", "Temitbekov", "muk@gmail.com", "KG", LocalDate.of(1991, 2, 28))));
                    case 3 -> System.out.println(authorService.findById(2L));
                    case 4 -> System.out.println(authorService.findAllAuthors());
                    case 5 -> System.out.println(authorService.updateAuthor(1L, new Author("Nurik", "Akimbekov", "nurik@gmail.com", "KG", LocalDate.of(1999, 2, 28))));
                    case 6 -> authorService.deleteAuthor(6L);
                    case 7 -> authorService.cleanAuthorTAble();
                    case 8 -> authorService.dropAuthorTable();
                    case 9 -> System.out.println(authorService.sortByDateOfBirth());
                    case 10-> bookService.createTableBook();
                    case 11-> System.out.println(bookService.saveBook(new Book("Samurai", "KG", 2020, 500, 1L)));
                    case 12-> System.out.println(bookService.getBookById(1L));
                    case 13-> System.out.println(bookService.getBooks());
                    case 14-> System.out.println(bookService.updateBookById(2L, new Book("Alippe Kyrgyzstan", "KG", 1936, 500, 1L)));
                    case 15-> System.out.println(bookService.deleteBookById(6L));
                    case 16-> System.out.println(bookService.getAllBookByAuthorId(1L));
                    case 17-> System.out.println(bookService.sortedBookByPrice());
                    case 18-> bookService.deleteBooksFromAuthorId(1L);
                    default -> System.out.println("write correctly");
                }
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
