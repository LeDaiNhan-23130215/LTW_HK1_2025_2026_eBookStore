package controllers;

import models.Category;
import DAO.AdminDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryController", value = "/admin-category")
public class AdminCategoryController extends HttpServlet{
    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Category> categories = adminDAO.getAllCategory();
        req.setAttribute("categories", categories);

        req.getRequestDispatcher("/WEB-INF/views/admin-category.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String desc = req.getParameter("description");

        Category category = new Category(name, desc);
        adminDAO.addCategory(category);

        resp.sendRedirect(req.getContextPath() + "/admin-category");
    }
}
