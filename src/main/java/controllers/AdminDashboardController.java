package controllers;

import DAO.AdminDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AdminDashboardController", value = "/admin-dashboard")
public class AdminDashboardController extends HttpServlet {
    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int totalEbooks = adminDAO.countTotalEBook();
        int totalUsers = adminDAO.countTotalUser();
        int totalOrders = adminDAO.countSuccessOrder();
        double totalMonthlyRevenue = adminDAO.getMonthlyRevenue();

        req.setAttribute("totalEbooks", totalEbooks);
        req.setAttribute("totalUsers", totalUsers);
        req.setAttribute("totaOrders", totalOrders);
        req.setAttribute("totalRevenue", totalMonthlyRevenue);

        req.getRequestDispatcher("/WEB-INF/views/admin-dashboard.jsp").forward(req, resp);
    }
}
