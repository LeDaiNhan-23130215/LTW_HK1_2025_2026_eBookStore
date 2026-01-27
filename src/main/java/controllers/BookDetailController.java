package controllers;

import DAO.EbookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Ebook;
import services.WishlistService;

import java.io.IOException;
import java.util.List;

@WebServlet("/bookdetail")
public class BookDetailController extends HttpServlet {
    private WishlistService wishlistService;

    @Override
    public void init() throws ServletException {
        wishlistService = new WishlistService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        int ebookId;

        try {
            ebookId = Integer.parseInt(idParam);
        } catch (NumberFormatException | NullPointerException e) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        EbookDAO ebookDAO = new EbookDAO();
        Ebook ebook = ebookDAO.getEbookById(ebookId);

        if (ebook == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userID") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int userID = (Integer) session.getAttribute("userID");

        // Ebook đã được hydrate authors + images
        List<Ebook> wishlist = wishlistService.getWishlistWithDetails(userID);

        List<Integer> wishlistIds = wishlist.stream()
                .map(Ebook::getId)
                .toList();

        request.setAttribute("wishlist", wishlist);
        request.setAttribute("wishlistIds", wishlistIds);

        request.setAttribute("ebook", ebook);
        request.getRequestDispatcher("/WEB-INF/views/bookdetail.jsp")
                .forward(request, response);
    }
}
