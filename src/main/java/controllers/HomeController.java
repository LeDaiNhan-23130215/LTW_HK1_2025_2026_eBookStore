package controllers;

import DAO.EbookDAO;
import DTO.EbookProductCardView;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Ebook;
import services.EbookService;
import services.WishlistService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", value = "/home")
public class HomeController extends HttpServlet {
    private EbookService ebookService;
    private WishlistService wishlistService;

    @Override
    public void init() throws ServletException {
        ebookService = new EbookService();
        wishlistService = new WishlistService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        List<EbookProductCardView> newEBooks = ebookService.getNewEbookProductCards();
        request.setAttribute("newEBooks", newEBooks);

        // Chỉ lấy wishlist nếu đã login
        if (session != null && session.getAttribute("userID") != null) {
            int userID = (Integer) session.getAttribute("userID");
            List<Integer> wishlistIds = wishlistService.getWishlist(userID)
                    .stream()
                    .map(Ebook::getId)
                    .toList();
            request.setAttribute("wishlistIds", wishlistIds);
        }

        request.getRequestDispatcher("/WEB-INF/views/home.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}