package java13.service.serviceImpl;

import java13.dao.AuthorDao;
import java13.dao.daoImol.AuthorDaoImpl;
import java13.model.Author;
import java13.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    AuthorDao authorDao = new AuthorDaoImpl();

    @Override
    public void createTableAuthor() {
        authorDao.createTableAuthor();
    }

    @Override
    public String saveAuthor(Author newAuthor) {
        return authorDao.saveAuthor(newAuthor);

    }

    @Override
    public Author findById(Long authorId) {
        return authorDao.findById(authorId);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorDao.findAllAuthors();
    }

    @Override
    public String updateAuthor(Long oldAuthorId, Author newAuthor) {
        return authorDao.updateAuthor(oldAuthorId, newAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorDao.deleteAuthor(id);
    }

    @Override
    public void dropAuthorTable() {
        authorDao.dropAuthorTable();
    }

    @Override
    public void cleanAuthorTAble() {
authorDao.cleanAuthorTAble();
    }

    @Override
    public List<Author> sortByDateOfBirth() {
        return authorDao.sortByDateOfBirth();
    }
}
