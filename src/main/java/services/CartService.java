package services;

import DAO.CartDAO;
import DAO.CartDetailDAO;
import models.Cart;
import models.CartDetail;

import java.util.List;

public class CartService {
    private CartDAO cartDAO = new CartDAO();
    private CartDetailDAO cartDetailDAO = new CartDetailDAO();

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
}
