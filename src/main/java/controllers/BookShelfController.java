package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.BookshelfService;

import java.io.IOException;

@WebServlet(name = "BookShelfController", value = "/book-shelf")
public class BookShelfController extends HttpServlet {

    private BookshelfService bookshelfService;

    @Override
    public void init() {
        bookshelfService = new BookshelfService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int userId = (int) req.getSession().getAttribute("userID");

        req.setAttribute("books",
                bookshelfService.getBooksOfUser(userId));

        req.getRequestDispatcher("/WEB-INF/views/book-shelf.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
