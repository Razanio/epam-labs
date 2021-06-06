package com.epam.courses.controller;

import com.epam.courses.controller.account.AccountMainAction;
import com.epam.courses.controller.account.addon.AccountAddAddonAction;
import com.epam.courses.controller.account.addon.AccountAddonAction;
import com.epam.courses.controller.account.addon.AccountDeleteAddonAction;
import com.epam.courses.controller.account.addon.AccountPayAddonAction;
import com.epam.courses.controller.account.balance.AccountAddBalanceAction;
import com.epam.courses.controller.account.balance.AccountSaveBalance;
import com.epam.courses.controller.admin.AdminMainAction;
import com.epam.courses.controller.admin.account.*;
import com.epam.courses.controller.admin.addon.*;
import com.epam.courses.controller.user.ChangePasswordAction;
import com.epam.courses.controller.admin.account.AdminResetPasswordAction;
import com.epam.courses.controller.user.SaveNewPasswordAction;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();
    static {
        actions.put("/", WelcomAction.class);
        actions.put("/index", WelcomAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/main", MainAction.class);
        actions.put("/account/accountview", AccountMainAction.class);
        actions.put("/account/addservice", AccountAddAddonAction.class);
        actions.put("/account/addons", AccountAddonAction.class);
        actions.put("/account/delservice", AccountDeleteAddonAction.class);
        actions.put("/account/payservice", AccountPayAddonAction.class);
        actions.put("/account/addbalance", AccountAddBalanceAction.class);
        actions.put("/account/savebalance", AccountSaveBalance.class);
        actions.put("/user/changepass", ChangePasswordAction.class);
        actions.put("/user/savepass", SaveNewPasswordAction.class);
        actions.put("/admin/adminview", AdminMainAction.class);
        actions.put("/admin/addon/addons", AdminAddonAction.class);
        actions.put("/admin/addon/addaddon", AdminAddAddonAction.class);
        actions.put("/admin/addon/saveaddons", AdminSaveAddAddonAction.class);
        actions.put("/admin/addon/changeaddon", AdminChangeAddonAction.class);
        actions.put("/admin/addon/savechangeaddon", AdminSaveChangeAddonAction.class);
        actions.put("/admin/addon/deladdon", AdminDeleteAddonAction.class);
        actions.put("/admin/account/accounts", AdminAccountAction.class);
        actions.put("/admin/account/addaccount", AdminAddAccountAction.class);
        actions.put("/admin/account/saveaccount", AdminSaveAddAccountAction.class);
        actions.put("/admin/account/showaccount", AdminShowAccountAction.class);
        actions.put("/admin/account/blockaccount", AdminBlockAccountAction.class);
        actions.put("/admin/account/unblockaccount", AdminUnblockAccountAction.class);
        actions.put("/admin/account/delaccount", AdminDeleteAccountAction.class);
        actions.put("/admin/account/resetpass", AdminResetPasswordAction.class);
    }

    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        if(action != null) {
            try {
                return (Action)action.getDeclaredConstructor().newInstance();
            } catch(Exception e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
