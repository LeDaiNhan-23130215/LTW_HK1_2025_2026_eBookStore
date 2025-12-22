package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Author;
import models.Ebook;
import services.AuthorService;
import services.WishlistService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "WishlistController", value = "/wishlist")
public class WishlistController extends HttpServlet {
    private WishlistService wishlistService;
    private AuthorService authorService;

    @Override
    public void init() throws ServletException {
        wishlistService = new WishlistService();
        authorService = new AuthorService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(session == null || session.getAttribute("userID") == null){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }

        int userID = (Integer) session.getAttribute("userID");

        List<Ebook> wishlist = wishlistService.getWishlist(userID);

        Map<Integer, Author> authorMap = authorService.getAuthors();
        req.setAttribute("wishlist", wishlist);
        req.setAttribute("authorMap", authorMap);
        req.getRequestDispatcher("/WEB-INF/views/wishlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("userID") == null){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not logged in");
            return;
        }

        int userID = (Integer) session.getAttribute("userID");

        String action = req.getParameter("action");
        int bookID = Integer.parseInt(req.getParameter("ebookId"));

        if("add".equalsIgnoreCase(action)){
            wishlistService.addToWishlist(userID, bookID);
        }
        else if("remove".equalsIgnoreCase(action)){
            wishlistService.removeFromWishlist(userID, bookID);
        }
        resp.sendRedirect(req.getContextPath()+"/wishlist");
    }
}
