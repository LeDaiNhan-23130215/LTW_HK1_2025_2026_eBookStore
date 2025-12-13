package controllers;

import models.Category;
import services.AdminServices;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryController", value = "/admin-category")
public class AdminCategoryController extends HttpServlet{
    private AdminServices adminServices;

    @Override
    public void init() throws ServletException {
        adminServices = new AdminServices();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            List<Category> categories = adminServices.getListCategory();
            req.setAttribute("categories", categories);

            req.getRequestDispatcher("/WEB-INF/views/admin-category.jsp")
                    .forward(req, resp);
            return;
        }

        if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            adminServices.deleteCategory(id);
            resp.sendRedirect(req.getContextPath() + "/admin-category");
            return;
        }

        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = adminServices.getCategoryById(id);
            req.setAttribute("category", category);

            req.getRequestDispatcher("/WEB-INF/views/admin-category-edit.jsp")
                    .forward(req, resp);
            return;
        }

        List<Category> categories = adminServices.getListCategory();
        req.setAttribute("categories", categories);

        req.getRequestDispatcher("/WEB-INF/views/admin-category.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if(action==null){
            req.getRequestDispatcher("/WEB-INF/views/admin-category.jsp").forward(req,resp);
            return;
        }
        if("update".equals(action)){
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("categoryName");
            String desc = req.getParameter("description");
            Category category = new Category(id, name, desc);
            adminServices.updateCategory(category);
            resp.sendRedirect(req.getContextPath()+"/admin-category");
            return;
        }
        else{
            String name = req.getParameter("categoryName");
            String desc = req.getParameter("description");

            Category category = new Category(name, desc);
            adminServices.addCategory(category);

            resp.sendRedirect(req.getContextPath() + "/admin-category");
        }
    }
}
