package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Author;
import services.AdminServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminAuthorController", value = "/admin-author")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024
)
public class AdminAuthorController extends HttpServlet {

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
            loadAuthorPage(req, resp);
            return;
        }

        switch (action) {
            case "delete":
                deleteAuthor(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            default:
                loadAuthorPage(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            handleAddAuthor(req, resp);
            return;
        }

        if ("update".equals(action)) {
            updateAuthor(req, resp);
            return;
        }

        loadAuthorPage(req, resp);
    }

    private void loadAuthorPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Author> authors = adminServices.getListAuthors();
        req.setAttribute("authors", authors);

        req.getRequestDispatcher("/WEB-INF/views/admin-author.jsp")
                .forward(req, resp);
    }

    private void handleAddAuthor(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mode = req.getParameter("mode");

        if ("import".equals(mode)) {
            Part filePart = req.getPart("file");

            if (filePart != null && filePart.getSize() > 0) {
                adminServices.importAuthorFile(filePart);
            }

        } else {
            Author author = getAuthorFromRequest(req);
            adminServices.addAuthor(author);
        }

        resp.sendRedirect(req.getContextPath() + "/admin-author");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Author author = adminServices.getAuthorById(id);

        if (author == null) {
            resp.sendRedirect(req.getContextPath() + "/admin-author");
            return;
        }

        req.setAttribute("author", author);
        req.getRequestDispatcher("/WEB-INF/views/admin-author-edit.jsp")
                .forward(req, resp);
    }

    private void updateAuthor(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Author author = getAuthorFromRequest(req);
        author.setId(id);

        adminServices.updateAuthor(author);
        resp.sendRedirect(req.getContextPath() + "/admin-author");
    }

    private void deleteAuthor(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        adminServices.deleteAuthor(id);
        resp.sendRedirect(req.getContextPath() + "/admin-author");
    }

    private Author getAuthorFromRequest(HttpServletRequest req) {

        String authorName = req.getParameter("authorName");
        String authorDetail = req.getParameter("authorDetail");
        String nationality = req.getParameter("nationality");
        String awards = req.getParameter("awards");

        int birthYear = parseIntSafe(req.getParameter("birthYear"));
        int numberOfBooks = parseIntSafe(req.getParameter("numberOfBooks"));

        return new Author(
                authorName,
                authorDetail,
                birthYear,
                nationality,
                numberOfBooks,
                awards
        );
    }

    private int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
}