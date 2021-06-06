package com.epam.courses.controller.admin.account;

import com.epam.courses.controller.Action;
import com.epam.courses.controller.Forward;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Addon;
import com.epam.courses.domain.User;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminAccountAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            try (ServiceFactory factory = getFactory()) {
                User user = (User) session.getAttribute("session_user");
                List<Account> accounts = factory.getAdministratorService().getAccounts();
                req.setAttribute("user", user);
                req.setAttribute("accounts", accounts);
                req.setAttribute("freeusers", factory.getUserService().getFreeUsers());
                req.setAttribute("blockaccounts", factory.getAdministratorService().getBlockAccounts());
                req.setAttribute("unblockaccounts", factory.getAdministratorService().getUnblockAccounts());
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
