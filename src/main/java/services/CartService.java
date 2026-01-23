package services;

import DAO.CartDAO;
import DAO.CartDetailDAO;
import DAO.EbookDAO;
import DAO.ImageDAO;
import DTO.CartItem;
import models.Cart;
import models.CartDetail;
import models.Ebook;
import models.Image;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private CartDAO cartDAO = new CartDAO();
    private CartDetailDAO cartDetailDAO = new CartDetailDAO();
    private EbookDAO ebookDAO = new EbookDAO();
    private ImageDAO imageDAO = new ImageDAO();

    public Cart getCartByUserID(int userID) {
        return cartDAO.getCartByUserId(userID);
    }

    public int createCart(int userID) {
        return cartDAO.createCart(userID);
    }

    public void addBookToCart(int cartId, int bookId, double price) {

        CartDetailDAO cdDao = new CartDetailDAO();

        if (!cdDao.isBookInCart(cartId, bookId)) {
            cdDao.addBookToCart(cartId, bookId, price);
        }
    }

    public boolean removeItem(int cartId, int bookId){
        return cartDetailDAO.removeItem(cartId, bookId);
    }

    public boolean updatePrice(int cartId, int bookId, double price) {
        return cartDetailDAO.updatePrice(cartId, bookId, price);
    }

    public List<CartDetail> getCartDetailByCartID(int cartID) {
        return cartDetailDAO.getCartDetailsByCartID(cartID);
    }

    public List<CartItem> getCartItemsByCartID(int cartID) {

        List<CartItem> items = new ArrayList<>();
        List<CartDetail> details = cartDetailDAO.getCartDetailsByCartID(cartID);

        for (CartDetail cd : details) {

            Ebook ebook = ebookDAO.getEbookById(cd.getBookID());

            if (ebook != null) {
                Image image = imageDAO.getFirstImageByEbookID(cd.getBookID());

                CartItem item = new CartItem(
                        cd.getId(),
                        ebook,
                        image,
                        cd.getPrice()
                );
                items.add(item);
            }
        }
        return items;
    }

    public int getTotalCartDetails(int cartId) {
        return cartDetailDAO.getCartDetailsByCartID(cartId).size();
    }

    public void clearCart(int cartId) {
        cartDetailDAO.removeAllItems(cartId);
    }
}
