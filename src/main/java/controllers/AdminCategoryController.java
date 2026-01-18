package controllers;

import models.Category;
import services.AdminServices;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryController", value = "/admin-category")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024
)
public class AdminCategoryController extends HttpServlet {

    private AdminServices adminServices;

    @Override
    public void init() {
        adminServices = new AdminServices();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            loadCategoryPage(req, resp);
            return;
        }

        switch (action) {
            case "delete":
                int idDel = Integer.parseInt(req.getParameter("id"));
                adminServices.deleteCategory(idDel);
                resp.sendRedirect(req.getContextPath() + "/admin-category");
                break;

            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                Category category = adminServices.getCategoryById(idEdit);
                req.setAttribute("category", category);
                req.getRequestDispatcher("/WEB-INF/views/admin-category-edit.jsp")
                        .forward(req, resp);
                break;

            default:
                loadCategoryPage(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String mode = req.getParameter("mode"); // manual | import

        if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("categoryName");
            String desc = req.getParameter("description");

            adminServices.updateCategory(new Category(id, name, desc));
            resp.sendRedirect(req.getContextPath() + "/admin-category");
            return;
        }

        if ("add".equals(action)) {

            if ("manual".equals(mode)) {
                String name = req.getParameter("categoryName");
                String desc = req.getParameter("description");

                if (name != null && !name.trim().isEmpty()) {
                    adminServices.addCategory(new Category(name.trim(), desc));
                }

                resp.sendRedirect(req.getContextPath() + "/admin-category");
                return;
            }

            if ("import".equals(mode)) {
                Part filePart = req.getPart("file");

                if (filePart != null && filePart.getSize() > 0) {
                    adminServices.importCategoryFile(filePart);
                }

                resp.sendRedirect(req.getContextPath() + "/admin-category");
                return;
            }
        }

        loadCategoryPage(req, resp);
    }

    private void loadCategoryPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Category> categories = adminServices.getListCategory();
        req.setAttribute("categories", categories);

        req.getRequestDispatcher("/WEB-INF/views/admin-category.jsp")
                .forward(req, resp);
    }
}