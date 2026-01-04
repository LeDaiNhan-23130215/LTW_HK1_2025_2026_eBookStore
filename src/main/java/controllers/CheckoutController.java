package controllers;

import DTO.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Cart;
import models.User;
import services.CartService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutController", value = "/checkout")
public class CheckoutController extends HttpServlet {
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        int userId = user.getId();

        Cart cart = cartService.getCartByUserID(userId);
        if (cart == null) {
            cartService.createCart(userId);
            cart = cartService.getCartByUserID(userId);
        }

        List<CartItem> cartItems = cartService.getCartItemsByCartID(cart.getId());
        double totalPrice = 0;
        for (CartItem ci : cartItems) {
            totalPrice += ci.getPriceAtADD();
        }

        req.setAttribute("cart", cart);
        req.setAttribute("cartItems", cartItems);
        req.setAttribute("totalPrice", totalPrice);

        req.getRequestDispatcher("/WEB-INF/views/payment.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        // Lấy phương thức thanh toán từ form
        String paymentMethod = req.getParameter("paymentMethod");

        // Lấy thông tin nhận hàng từ session
        String email = (String) session.getAttribute("checkoutEmail");
        String name = (String) session.getAttribute("checkoutName");
        String phone = (String) session.getAttribute("checkoutPhone");
        String note = (String) session.getAttribute("checkoutNote");

        // TODO: Tạo đơn hàng từ giỏ hàng + thông tin nhận hàng + phương thức thanh toán
        // orderService.createOrder(user, cartItems, totalPrice, paymentMethod, ...);

        // Sau khi tạo đơn hàng, chuyển hướng sang trang xác nhận
        resp.sendRedirect(req.getContextPath() + "/checkout");
    }
}
