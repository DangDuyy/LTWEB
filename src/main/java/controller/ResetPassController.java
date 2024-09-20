package controller;

import dao.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/resetpass")
public class ResetPassController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ResetPassController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        // Check cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/login");
                    return;
                }
            }
        }
        req.getRequestDispatcher(Constant.Path.RESETPW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String newPassword = req.getParameter("psw");
        String confirmPassword = req.getParameter("repeat-psw");
        UserServiceImpl service = new UserServiceImpl();
        String alertMsg = "";

        LOGGER.log(Level.INFO, "Received password reset request for email: {0}", email);

        if (!newPassword.equals(confirmPassword)) {
            alertMsg = "Passwords do not match!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.RESETPW).forward(req, resp);
            return;
        }

        if (!service.checkExistEmail(email)) {
            alertMsg = "Email does not exist!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.RESETPW).forward(req, resp);
            return;
        }

        boolean isSuccess = service.resetPass(email, newPassword);
        if (isSuccess) {
            LOGGER.log(Level.INFO, "Password reset successful for email: {0}", email);
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "System error!";
            LOGGER.log(Level.SEVERE, "Password reset failed for email: {0}", email);
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.RESETPW).forward(req, resp);
        }
    }
}