package services;

import DAO.*;
import enums.AddBookResult;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class WishlistService {
    private WishlistDAO wishlistDAO = new WishlistDAO();
    private WishlistDetailDAO wishlistDetailDAO = new WishlistDetailDAO();
    private AuthorDAO authorDAO = new AuthorDAO();
    private ImageDAO imageDAO = new ImageDAO();
    private BookshelfDAO bookshelfDAO = new BookshelfDAO();

    public AddBookResult addToWishlist(int userId, int bookId) {

        if (bookshelfDAO.userOwnsEbook(userId, bookId)) {
            return AddBookResult.ALREADY_OWNED;
        }

        int wishlistId = wishlistDAO.getOrCreate(userId);

        if (wishlistDetailDAO.exists(wishlistId, bookId)) {
            return AddBookResult.ALREADY_EXISTS;
        }

        wishlistDetailDAO.addBookToWishlist(wishlistId, bookId);
        return AddBookResult.SUCCESS;
    }

    public void removeFromWishlist(int userId, int bookId) {
        int wishlistId = wishlistDAO.getOrCreate(userId);
        wishlistDetailDAO.removeBookInWishlist(wishlistId, bookId);
    }

    public List<Ebook> getWishlist(int userId) {
        int wishlistId = wishlistDAO.getOrCreate(userId);
        List<Ebook> allEbooks = wishlistDetailDAO.getBooksByWishlistId(wishlistId);
        List<Ebook> filteredList = new ArrayList<>();

        for (Ebook e : allEbooks) {
            if (!bookshelfDAO.userOwnsEbook(userId, e.getId())) {
                filteredList.add(e);
            }
        }

        // 5. Trả về danh sách đã lọc
        return filteredList;
    }

    public List<Ebook> getWishlistWithDetails(int userId) {
        List<Ebook> ebooks = wishlistDetailDAO.getBooksByWishlistId(userId);

        for (Ebook ebook : ebooks) {
            if (bookshelfDAO.userOwnsEbook(userId, ebook.getId())) {
                continue;
            }
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
