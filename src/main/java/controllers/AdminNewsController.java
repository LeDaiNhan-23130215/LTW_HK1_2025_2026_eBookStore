package controllers;

import DAO.NewsDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.News;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminNewsController", value = "/admin-news")
public class AdminNewsController extends HttpServlet {

    private NewsDAO newsDAO;

    @Override
    public void init() throws ServletException {
        newsDAO = new NewsDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            List<News> newsList = newsDAO.getAllNews();
            req.setAttribute("newsList", newsList);

            req.getRequestDispatcher("/WEB-INF/views/admin-news.jsp")
                    .forward(req, resp);
            return;
        }

        // DELETE
        if (action.equals("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            newsDAO.deleteNews(id);
            resp.sendRedirect(req.getContextPath() + "/admin-news");
            return;
        }

        // EDIT
        if (action.equals("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            News news = newsDAO.getNewsById(id);

            req.setAttribute("news", news);
            req.getRequestDispatcher("/WEB-INF/views/admin-news-edit.jsp")
                    .forward(req, resp);
            return;
        }

        // Default: list
        List<News> newsList = newsDAO.getAllNews();
        req.setAttribute("newsList", newsList);
        req.getRequestDispatcher("/WEB-INF/views/admin-news.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null) {
            resp.sendRedirect(req.getContextPath() + "/admin-news");
            return;
        }

        // UPDATE
        if (action.equals("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String imgURL = req.getParameter("imgURL");
            String author = req.getParameter("author");
            int status = Integer.parseInt(req.getParameter("status"));

            News news = new News();
            news.setId(id);
            news.setTitle(title);
            news.setContent(content);
            news.setImgURL(imgURL);
            news.setAuthor(author);
            news.setStatus(status);

            newsDAO.updateNews(news);

            resp.sendRedirect(req.getContextPath() + "/admin-news");
            return;
        }

        // ADD
        if (action.equals("add")) {

            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String imgURL = req.getParameter("imgURL");
            String author = req.getParameter("author");
            int status = Integer.parseInt(req.getParameter("status"));

            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            news.setImgURL(imgURL);
            news.setAuthor(author);
            news.setStatus(status);

            newsDAO.addNews(news);

            resp.sendRedirect(req.getContextPath() + "/admin-news");
        }
    }
}
