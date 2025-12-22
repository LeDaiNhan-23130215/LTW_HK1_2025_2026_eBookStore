package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Cart;
import models.CartDetail;
import models.User;
import services.CartService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = user.getId();

        Cart cart = cartService.getCartByUserID(userId);
        if (cart == null) {
            cartService.createCart(userId);
            cart = cartService.getCartByUserID(userId);
        }

        List<CartDetail> cartDetails = cartService.getCartDetailByCartID(cart.getId());

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice();
        }

        request.setAttribute("cart", cart);
        request.setAttribute("cartDetails", cartDetails);
        request.setAttribute("totalPrice", totalPrice);

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        int userId = user.getId();
        Cart cart = cartService.getCartByUserID(userId);
        if (cart == null) {
            cartService.createCart(userId);
            cart = cartService.getCartByUserID(userId);
        }

        if ("add".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            double price = Double.parseDouble(request.getParameter("price"));

            cartService.addBookToCart(cart.getId(), bookId, price);
        }

        if ("remove".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            cartService.removeItem(cart.getId(), bookId);
        }

        if ("update".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            double price = Double.parseDouble(request.getParameter("price"));

            cartService.updatePrice(cart.getId(), bookId, price);
        }

        response.sendRedirect("cart");
    }
}