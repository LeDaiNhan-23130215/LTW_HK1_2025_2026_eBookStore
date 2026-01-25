package services;

import DAO.AuthorDAO;
import DAO.ImageDAO;
import DAO.WishlistDAO;
import DAO.WishlistDetailDAO;
import models.*;

import java.util.List;

public class WishlistService {
    private WishlistDAO wishlistDAO = new WishlistDAO();
    private WishlistDetailDAO wishlistDetailDAO = new WishlistDetailDAO();
    private AuthorDAO authorDAO = new AuthorDAO();
    private ImageDAO imageDAO = new ImageDAO();

    public void addToWishlist(int userId, int bookId) {
        int wishlistId = wishlistDAO.getOrCreate(userId);

        if (!wishlistDetailDAO.exists(wishlistId, bookId)) {
            wishlistDetailDAO.addBookToWishlist(wishlistId, bookId);
        }
    }

    public void removeFromWishlist(int userId, int bookId) {
        int wishlistId = wishlistDAO.getOrCreate(userId);
        wishlistDetailDAO.removeBookInWishlist(wishlistId, bookId);
    }

    public List<Ebook> getWishlist(int userId) {
        int wishlistId = wishlistDAO.getOrCreate(userId);
        return wishlistDetailDAO.getBooksByWishlistId(wishlistId);
    }

    public boolean isInWishlist(int userId, int bookId) {
        int wishlistId = wishlistDAO.getOrCreate(userId);
        return wishlistDetailDAO.exists(wishlistId, bookId);
    }

    public List<Ebook> getWishlistWithDetails(int userId) {
        List<Ebook> ebooks = wishlistDetailDAO.getBooksByWishlistId(userId);

        for (Ebook ebook : ebooks) {

            // authors
            List<Author> authors = authorDAO.getByEbookID(ebook.getId());
            ebook.setAuthors(authors);

            // images (thumbnail hoặc all)
            List<Image> images = imageDAO.getByEbookID(ebook.getId());
            ebook.setImages(images);
        }

        return ebooks;
    }
}
