package com.epam.courses.control.account;

import com.epam.courses.control.BaseServlet;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Addon;
import com.epam.courses.domain.User;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountPayServiceServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            try (ServiceFactory factory = getFactory()) {
                User user = (User) session.getAttribute("session_user");
                String name = req.getParameter("payservice");
                Addon service = factory.getAccountService().getAddonByName(name);
                Account account = factory.getAccountDao().read(user.getId());
                factory.getAccountService().payForTheAddon(service.getId(), user.getId());
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/account/accountview.html");
    }
}
