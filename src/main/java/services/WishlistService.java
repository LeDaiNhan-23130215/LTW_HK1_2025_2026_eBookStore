package services;

import DAO.WishlistDAO;
import DAO.WishlistDetailDAO;
import models.Ebook;

import java.util.List;

public class WishlistService {
    private WishlistDAO wishlistDAO = new WishlistDAO();
    private WishlistDetailDAO wishlistDetailDAO = new WishlistDetailDAO();

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
        return wishlistDetailDAO.getBooksByUser(userId);
    }
}
