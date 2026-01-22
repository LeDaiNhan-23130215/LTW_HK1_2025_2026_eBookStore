package controllers;
import DAO.DemoFileDAO;
import DAO.FullFileDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.DemoFile;
import models.Ebook;
import models.FullFile;
import models.Image;
import services.AdminServices;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "AdminEbookController", value = "/admin-ebook")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024
)
public class AdminEbookController extends HttpServlet {
    private AdminServices adminServices;

    @Override
    public void init() {
        adminServices = new AdminServices();
    }

    // ===================== GET =====================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) action = "list";

        switch (action) {
            case "edit":
                showEditForm(req, resp);
                break;

            case "delete":
                deleteEbook(req, resp);
                break;

            default:
                showList(req, resp);
        }
    }

    // ===================== POST =====================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if (action == null) {
            resp.sendRedirect(req.getContextPath() + "/admin-ebook");
            return;
        }

        switch (action) {
            case "add":
                insertEbook(req, resp);
                break;

            case "update":
                updateEbook(req, resp);
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/admin-ebook");
        }
    }

    // ===================== METHODS =====================

    private void showList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Ebook> ebooks = adminServices.findAll();

        // ===== MAP AUTHOR =====
        Map<Integer, String> authorMap = adminServices.getListAuthors()
                .stream()
                .collect(Collectors.toMap(
                        a -> a.getId(),
                        a -> a.getAuthorName()
                ));

        // ===== MAP CATEGORY =====
        Map<Integer, String> categoryMap = adminServices.getListCategory()
                .stream()
                .collect(Collectors.toMap(
                        c -> c.getId(),
                        c -> c.getName()
                ));

        req.setAttribute("authors", adminServices.getListAuthors());
        req.setAttribute("categories", adminServices.getListCategory());

        req.setAttribute("ebooks", ebooks);
        req.setAttribute("authorMap", authorMap);
        req.setAttribute("categoryMap", categoryMap);

        req.getRequestDispatcher("/WEB-INF/views/admin-ebook.jsp")
                .forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String idRaw = req.getParameter("id");
            if (idRaw == null) {
                throw new RuntimeException("ID is null");
            }

            int id = Integer.parseInt(idRaw);

            Ebook ebook = adminServices.getEbookByID(id);
            if (ebook == null) {
                throw new RuntimeException("Ebook not found with id=" + id);
            }

            req.setAttribute("ebook", ebook);
            req.setAttribute("authors", adminServices.getListAuthors());
            req.setAttribute("categories", adminServices.getListCategory());

            req.getRequestDispatcher("/WEB-INF/views/admin-ebook-edit.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500, e.getMessage());
        }
    }

    private void insertEbook(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Ebook ebook = buildEbookFromRequest(req);

        adminServices.addEbook(ebook);

        resp.sendRedirect(req.getContextPath() + "/admin-ebook");
    }

    private void updateEbook(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        Ebook old = adminServices.getEbookByID(id);

        Ebook ebook = new Ebook(
                id,
                req.getParameter("title"),
                Integer.parseInt(req.getParameter("authorId")),
                Double.parseDouble(req.getParameter("price")),
                old.getImageID(),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("categoryId")),
                old.getFullFileID(),
                old.getDemoFileID(),
                old.getStatus(),
                old.geteBookCode()
        );

        adminServices.updateEbook(ebook);

        resp.sendRedirect(req.getContextPath() + "/admin-ebook");
    }

    private void deleteEbook(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        adminServices.deleteEbook(id);

        resp.sendRedirect(req.getContextPath() + "/admin-ebook");
    }

    // ===================== HELPER =====================

    private Ebook buildEbookFromRequest(HttpServletRequest req) {

        String title = req.getParameter("title");
        int authorID = Integer.parseInt(req.getParameter("authorId"));
        int categoryID = Integer.parseInt(req.getParameter("categoryId"));
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");

        String coverURL = req.getParameter("coverUrl");
        Image image = new Image(title, coverURL, "ACTIVE");

        int imageID = adminServices.getIDAfterInserted(image);

        String fullFileUrl = req.getParameter("filePath");

        FullFile fullFile = new FullFile(title, getFileFormat(fullFileUrl), 0, fullFileUrl, "ACTIVE");
        FullFileDAO fullFileDAO = new FullFileDAO();
        int fullFileID = fullFileDAO.insertAndReturnId(fullFile);

        String demoFileUrl = req.getParameter("filePath");

        DemoFile demoFile = new DemoFile(title, getFileFormat(demoFileUrl), 0, demoFileUrl, 10, "ACTIVE");
        DemoFileDAO demoFileDAO = new DemoFileDAO();
        int demoFileID = demoFileDAO.insertAndReturnId(demoFile);

        String code = adminServices.generateEbookCode(categoryID);

        return new Ebook(
                0,
                title,
                authorID,
                price,
                imageID,
                description,
                categoryID,
                fullFileID,
                demoFileID,
                "ACTIVE",
                code
        );
    }

    private String getFileFormat(String url) {
        int dot = url.lastIndexOf(".");
        if (dot != -1) {
            return url.substring(dot + 1).toUpperCase();
        }
        return "UNKNOWN";
    }
}
