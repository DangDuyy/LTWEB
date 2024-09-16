package controller;

import dao.IUserServiceDao;
import dao.impl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.UserModel;

import java.io.IOException;

@WebServlet(urlPatterns = { "/", "/login", "/dang_nhap" })
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        boolean isRememberMe = false;
        String remember = req.getParameter("remember");
        if ("on".equals(remember)) {
            isRememberMe = true;
        }
        String alertMsg = "";

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
            return;
        }
        IUserServiceDao service = new UserServiceImpl();
        UserModel user = service.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            if (isRememberMe) {
                saveRemeberMe(resp, username);
            }
            req.setAttribute("msg", true);
            req.setAttribute("uname", user.getUserName());
            req.setAttribute("pwd", user.getPassword());
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.setAttribute("msg", false);
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }
    }

    private void saveRemeberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);
    }
}