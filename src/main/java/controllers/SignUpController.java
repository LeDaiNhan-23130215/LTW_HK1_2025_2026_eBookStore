package controllers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignUpController", value = ("/sign-up"))
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("fname");
        String email = req.getParameter("userAndEmail");
        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if(username == null || username.isEmpty() ||
                email == null || email.isEmpty() ||
                phoneNumber == null || phoneNumber.isEmpty() ||
                password == null || password.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty()) {

            req.setAttribute("error_msg", "Vui lòng điền đầy đủ thông tin.");
            req.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req, resp);
            return;
        }

        if(username.length() < 3) {
            req.setAttribute("error_msg", "Tên phải có ít nhất 3 ký tự.");
            req.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req, resp);
            return;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if(!email.matches(emailRegex)) {
            req.setAttribute("error_msg", "Email không hợp lệ.");
            req.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req, resp);
            return;
        }

        if(!phoneNumber.matches("\\d{10,11}")) {
            req.setAttribute("error_msg", "Số điện thoại không hợp lệ (10–11 số).");
            req.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req, resp);
            return;
        }

        if(password.length() < 6) {
            req.setAttribute("error_msg", "Mật khẩu phải có ít nhất 6 ký tự.");
            req.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req, resp);
            return;
        }

        if(!password.equals(confirmPassword)) {
            req.setAttribute("error_msg", "Mật khẩu xác nhận không khớp.");
            req.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("success_msg", "Đăng ký thành công! Đang chuyển trang...");
        req.getRequestDispatcher("/WEB-INF/views/sign-up.jsp").forward(req, resp);
    }
}
