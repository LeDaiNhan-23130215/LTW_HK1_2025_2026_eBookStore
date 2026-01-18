package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Image;
import services.BookshelfService;
import services.ImageServices;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "BookShelfController", value = "/book-shelf")
public class BookShelfController extends HttpServlet {

    private BookshelfService bookshelfService;
    private ImageServices imageServices;

    @Override
    public void init() {
        bookshelfService = new BookshelfService();
        imageServices = new ImageServices();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int userId = (int) req.getSession().getAttribute("userID");
        Map<Integer, Image> imageMap = imageServices.getImages();
        req.setAttribute("books",
                bookshelfService.getBooksOfUser(userId));
        req.setAttribute("imageMap", imageMap);
        req.getRequestDispatcher("/WEB-INF/views/book-shelf.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
