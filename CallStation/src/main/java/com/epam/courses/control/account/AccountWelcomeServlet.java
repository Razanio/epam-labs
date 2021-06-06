package com.epam.courses.control.account;

import com.epam.courses.control.BaseServlet;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.User;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountWelcomeServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            try (ServiceFactory factory = getFactory()) {
                User user = (User) session.getAttribute("session_user");
                Account account = factory.getAccountDao().read(user.getId());
                req.setAttribute("user", user);
                req.setAttribute("account", account);
                req.getRequestDispatcher("/WEB-INF/jsp/account/accountview.jsp").forward(req, resp);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}

