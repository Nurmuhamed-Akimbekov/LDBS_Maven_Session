package java13.dao.daoImol;

import java13.config.JdbcConfig;
import java13.dao.BookDao;
import java13.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public void createTableBook() {
        String sql = """
                create table if not exists books(
                id serial primary key,
                name varchar,
                country varchar,
                  published_year int,
                price int,
                author_id int references authors(id));""";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("success created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String saveBook(Book book) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into books (name,country,published_year,price,author_id)" +
                        "values(?,?,?,?,?);")) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getCountry());
            preparedStatement.setInt(3, book.getPublishedYear());
            preparedStatement.setInt(4, book.getPrice());
            preparedStatement.setLong(5, book.getAuthorId());
            int bookSave = preparedStatement.executeUpdate();
            return "successfully added";
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    @Override
    public Book getBookById(Long id) {
        Book book = new Book();
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "select * from books where id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Books by id " + id + " not found");
            }
            book.setId(resultSet.getLong("id"));
            book.setName(resultSet.getString("name"));
            book.setCountry(resultSet.getString("country"));
            book.setPublishedYear(resultSet.getInt("published_year"));
            book.setPrice(resultSet.getInt("price"));
            book.setAuthorId(resultSet.getLong("author_id"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from books ")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setCountry(resultSet.getString("country"));
                book.setPublishedYear(resultSet.getInt("published_year"));
                book.setPrice(resultSet.getInt("price"));
                book.setAuthorId(resultSet.getLong("author_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public Book updateBookById(Long olId, Book newBook) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update  books set name=?,country=?,published_year=?,price=?,author_id=? where id=? ")) {
            preparedStatement.setString(1, newBook.getName());
            preparedStatement.setString(2, newBook.getCountry());
            preparedStatement.setInt(3, newBook.getPublishedYear());
            preparedStatement.setInt(4, newBook.getPrice());
            preparedStatement.setLong(5, newBook.getAuthorId());
            preparedStatement.setLong(6, olId);
            int updateBook = preparedStatement.executeUpdate();
            if (updateBook > 0) {
                return newBook;
            } else {
                System.out.println("Book by id " + olId + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteBookById(Long bookId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from books where id=?")) {
            preparedStatement.setLong(1, bookId);
            int deleteBook = preparedStatement.executeUpdate();
            if (deleteBook > 0) {
                return "book successfully deleted";
            } else return "book by id " + bookId + " not found";
        } catch (SQLException E) {
            return (E.getMessage());
        }
    }

    @Override
    public List<Book> getAllBookByAuthorId(Long authorId) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("" +
                "select * from books b inner join authors a on a.id=b.author_id where a.id=?")) {
            preparedStatement.setLong(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setName(resultSet.getString("name"));
            book.setCountry(resultSet.getString("country"));
            book.setPublishedYear(resultSet.getInt("published_year"));
            book.setPrice(resultSet.getInt("price"));
            book.setAuthorId(resultSet.getLong("author_id"));
            books.add(book);}
          if (!resultSet.next()) throw new RuntimeException("Books by author id " + authorId + " not found");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> sortedBookByPrice() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from books order by price")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setCountry(resultSet.getString("country"));
                book.setPublishedYear(resultSet.getInt("published_year"));
                book.setPrice(resultSet.getInt("price"));
                book.setAuthorId(resultSet.getLong("author_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public void deleteBooksFromAuthorId(Long authorId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "delete  from books where author_id in (select id from authors where id=?)")) {
            preparedStatement.setLong(1, authorId);
            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate > 0) {
                System.out.println("books successfully deleted");
            } throw new RuntimeException("books by author id " + authorId + " not found");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
