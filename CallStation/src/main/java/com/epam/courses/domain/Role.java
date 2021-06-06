package com.epam.courses.domain;

public enum Role {
    ADMIN("admin"),
    SUBSCRIBER("subscriber");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

    public void setName(String name) {
        this.name = name;
    }
}
