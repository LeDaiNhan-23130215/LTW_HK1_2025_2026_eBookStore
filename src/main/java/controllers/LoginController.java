package controllers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
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

        if(!userName.equals("admin") || !password.equals("12345")){
            req.setAttribute("error_msg", "Invalid username or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            return;
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute("username", userName);
            resp.sendRedirect(req.getContextPath()+"/home");
        }
    }
}
