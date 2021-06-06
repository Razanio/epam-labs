package com.epam.courses.service.exception.user;

import com.epam.courses.service.exception.ServiceException;

public class UserPasswordIncorrectException extends ServiceException {
    private Long id;

    public UserPasswordIncorrectException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
