package controllers;
import DAO.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userAndEmail");
        String password = req.getParameter("password");

        if(userName == null || userName.isEmpty()
                || password == null || password.isEmpty()){
            req.setAttribute("error_msg", "Please enter username and password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            return;
        }

        boolean isValid = userDAO.checkLogin(userName, password);
        if(!isValid){
            req.setAttribute("error_msg", "Invalid username or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("userName", userDAO.getUserNameByEmail(userName));
        resp.sendRedirect(req.getContextPath()+"/home");
    }
}
