package com.epam.courses.domain.features;

public enum PurposeType {
    OUTPUT("output"), INPUT("input"), OUTPUTINPUT("output-input"),
    MULTIMEDIA("multimedia"), MEMORY("memory"), COMPUTINGLOGIC("computing-logic"), Lev("mem");

    private String name;

    PurposeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
