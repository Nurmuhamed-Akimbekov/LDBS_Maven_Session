package java13.dao.daoImol;

import java13.config.JdbcConfig;
import java13.dao.AuthorDao;
import java13.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public void createTableAuthor() {
        String sql = """
                create table if not exists authors(
                id serial primary key,
                first_name varchar(50),
                last_name varchar(50),
                email varchar unique,
                country varchar,
                date_of_birth date);
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String saveAuthor(Author newAuthor) {
        String sql = """
                insert into authors(first_name,last_name,email,country,date_of_birth)
                values(?,?,?,?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newAuthor.getFirstName());
            preparedStatement.setString(2, newAuthor.getLastName());
            preparedStatement.setString(3, newAuthor.getEmail());
            preparedStatement.setString(4, newAuthor.getCountry());
            preparedStatement.setDate(5, Date.valueOf(newAuthor.getDateOfBirth()));
            preparedStatement.executeUpdate();
            return "success";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "failed";
    }

    @Override
    public Author findById(Long authorId) {
        Author author = new Author();
        String sql = """
                select * from authors where id =?""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setEmail(resultSet.getString("email"));
                author.setCountry(resultSet.getString("country"));
                author.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return author;
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = " select * from authors ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setEmail(resultSet.getString("email"));
                author.setCountry(resultSet.getString("country"));
                author.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                authors.add(author);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return authors;
    }

    @Override
    public String updateAuthor(Long oldAuthorId, Author newAuthor) {
        String sql = "update authors set" +
                " first_name=? ," +
                "last_name =?," +
                " email=?," +
                " country=? ," +
                "date_of_birth=? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newAuthor.getFirstName());
            preparedStatement.setString(2, newAuthor.getLastName());
            preparedStatement.setString(3, newAuthor.getEmail());
            preparedStatement.setString(4, newAuthor.getCountry());
            preparedStatement.setDate(5, Date.valueOf(newAuthor.getDateOfBirth()));
            preparedStatement.setLong(6, oldAuthorId);
            int find = preparedStatement.executeUpdate();
            if (find > 0) {
                return "successfully updated";
            } else return (" author by id " + oldAuthorId + " not found");
        } catch (SQLException e) {
            return (e.getMessage());
        }

    }

    @Override
    public void deleteAuthor(Long id) {
        String sql = "delete from authors where id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int deleteAuthor = preparedStatement.executeUpdate();
            if (deleteAuthor > 0) {
                System.out.println("successfully deleted");
            } else {
                System.out.println(" author by id " + id + " not found!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void dropAuthorTable() {
        String sql = "drop table if exists ";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("table successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void cleanAuthorTAble() {
        String sql = "truncate table  authors";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("table successfully cleaned");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Author> sortByDateOfBirth() {
        List<Author> authors = new ArrayList<>();
        String sql = " select * from authors order by  date_of_birth ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setEmail(resultSet.getString("email"));
                author.setCountry(resultSet.getString("country"));
                author.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                authors.add(author);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return authors;
    }
}
