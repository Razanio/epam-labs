package com.epam.courses.controller;

import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>();

    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMIN);
        Set<Role> subscriber = new HashSet<>();
        subscriber.add(Role.SUBSCRIBER);


        permissions.put("/user/changepass", all);
        permissions.put("/user/savepass", all);

        permissions.put("/account/accountview", subscriber);
        permissions.put("/account/addservice", subscriber);
        permissions.put("/account/addons", subscriber);
        permissions.put("/account/delservice", subscriber);
        permissions.put("/account/payservice", subscriber);
        permissions.put("/account/addbalance", subscriber);
        permissions.put("/account/savebalance", subscriber);

        permissions.put("/admin/adminview", admin);
        permissions.put("/admin/addon/addons", admin);
        permissions.put("/admin/addon/addaddon", admin);
        permissions.put("/admin/addon/saveaddons", admin);
        permissions.put("/admin/addon/changeaddon", admin);
        permissions.put("/admin/addon/savechangeaddon", admin);
        permissions.put("/admin/addon/deladdon", admin);
        permissions.put("/admin/account/accounts", admin);
        permissions.put("/admin/account/addaccount", admin);
        permissions.put("/admin/account/saveaccount", admin);
        permissions.put("/admin/account/showaccount", admin);
        permissions.put("/admin/account/blockaccount", admin);
        permissions.put("/admin/account/unblockaccount", admin);
        permissions.put("/admin/account/delaccount", admin);
        permissions.put("/admin/account/resetpass", admin);

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        HttpServletResponse httpResp = (HttpServletResponse)resp;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Set<Role> roles = permissions.get(url);
        if(roles != null) {
            HttpSession session = httpReq.getSession(false);
            if(session != null) {
                User user = (User)session.getAttribute("session_user");
                if(user != null && roles.contains(user.getRole())) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        } else {
            chain.doFilter(req, resp);
            return;
        }
        httpResp.sendRedirect(context + "/login.html?message=login.message.access.denied");
    }
}
