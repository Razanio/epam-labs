package com.epam.courses.control;

import com.epam.courses.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainPageServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null){
            User user = (User)session.getAttribute("session_user");
            System.out.println("start");
            if(user != null){
                switch (user.getRole()){
                    case ADMIN:
                        resp.sendRedirect(req.getContextPath() + "/user/list.html");
                        return;
                    case SUBSCRIBER:
                       // resp.sendRedirect(req.getContextPath() + "/login.html?message=" + URLEncoder.encode(String.format("Пользователь %s (%s) вошел в систему", user.getLogin(), user.getRole().getName()), "UTF-8"));
                        resp.sendRedirect(req.getContextPath() + "/account/accountview.html");
                        return;

                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
