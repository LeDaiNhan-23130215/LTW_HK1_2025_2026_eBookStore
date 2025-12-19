package controllers;

import DAO.EbookDAO;
import DTO.EbookProductCardView;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Ebook;
import services.EbookService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", value = "/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EbookService ebookService = new EbookService();
        List<EbookProductCardView> newEBooks =
                ebookService.getNewEbookProductCards();

        request.setAttribute("newEBooks", newEBooks);

        request.getRequestDispatcher("/WEB-INF/views/home.jsp")
                .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}