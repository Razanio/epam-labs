package com.epam.courses.domain;

import com.epam.courses.domain.features.PortType;
import com.epam.courses.domain.features.PurposeType;

import javax.sound.sampled.Port;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Feature {
    private String name;
    private String element;

    public Feature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public abstract void setValue(String value);

    public abstract String getValue();

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }
}
