package com.epam.courses.service.exception.addon;

import com.epam.courses.service.exception.ServiceException;

public class AddonAlreadyExistsException extends ServiceException {
    private String name;

    public AddonAlreadyExistsException(String serviceName) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
