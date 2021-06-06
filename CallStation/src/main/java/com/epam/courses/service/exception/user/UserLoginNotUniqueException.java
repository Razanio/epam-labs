package com.epam.courses.service.exception.user;

import com.epam.courses.service.exception.ServiceException;

public class UserLoginNotUniqueException extends ServiceException {
    private String login;

    public UserLoginNotUniqueException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
