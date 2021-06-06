package com.epam.courses.control.account;

import com.epam.courses.control.BaseServlet;
import com.epam.courses.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountChangePasswordServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null) {
            try {
                User user = (User) session.getAttribute("session_user");
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/jsp/account/changepass.jsp").forward(req, resp);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/account/accountview.html");
    }
}
