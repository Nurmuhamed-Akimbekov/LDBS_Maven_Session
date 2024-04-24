package java13.dao;

import java13.model.Author;

import java.util.List;

public interface AuthorDao {
    //CRUD

    void createTableAuthor();

    String saveAuthor(Author newAuthor);
    Author findById(Long authorId);
    List<Author> findAllAuthors();
    String updateAuthor(Long oldAuthorId,Author newAuthor);
    void deleteAuthor (Long id);
    void dropAuthorTable();
    void cleanAuthorTAble();

    // dop method
    List<Author> sortByDateOfBirth();

}
