package services;

import DAO.AuthorDAO;
import models.Author;

import java.util.HashMap;
import java.util.Map;

public class AuthorService {
    private AuthorDAO authorDAO = new AuthorDAO();

    public Map<Integer, Author> getAuthors() {
        Map<Integer, Author> authors = authorDAO.getAuthorMap();
        return authors != null ? authors : new HashMap<>();
    }
}
