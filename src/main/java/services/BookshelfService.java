package services;

import DAO.BookshelfDAO;
import DAO.BookshelfDetailDAO;
import models.Bookshelf;
import models.Ebook;

import java.util.List;

public class BookshelfService {

    private final BookshelfDAO bookshelfDAO;
    private final BookshelfDetailDAO bookshelfDetailDAO;

    public BookshelfService() {
        this.bookshelfDAO = new BookshelfDAO();
        this.bookshelfDetailDAO = new BookshelfDetailDAO();
    }

    public Bookshelf getOrCreateBookshelf(int userId) {
        Bookshelf bookshelf = bookshelfDAO.getByUserId(userId);

        if (bookshelf == null) {
            bookshelfDAO.create(userId);
            bookshelf = bookshelfDAO.getByUserId(userId);
        }

        return bookshelf;
    }

    public void addBookToBookshelf(int userId, int ebookId) {
        Bookshelf bookshelf = getOrCreateBookshelf(userId);

        boolean exists = bookshelfDetailDAO.exists(
                bookshelf.getId(), ebookId
        );

        if (!exists) {
            bookshelfDetailDAO.addBook(
                    bookshelf.getId(), ebookId
            );
        }
    }

    public List<Ebook> getBooksOfUser(int userId) {
        return bookshelfDetailDAO.getBooksByUser(userId);
    }

    public boolean userOwnsBook(int userId, int ebookId) {
        Bookshelf bookshelf = bookshelfDAO.getByUserId(userId);
        if (bookshelf == null) return false;

        return bookshelfDetailDAO.exists(
                bookshelf.getId(), ebookId
        );
    }
}
