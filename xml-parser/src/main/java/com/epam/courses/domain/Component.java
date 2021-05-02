package com.epam.courses.domain;

import com.epam.courses.domain.features.PortType;
import com.epam.courses.domain.features.PurposeType;

import java.util.*;

public class Component {
    private String identity;
    private String name;
    private String origin;
    private Double price;
    private Boolean critical;
    private PurposeType purposeType;
    private Map<PortType,Integer> ports = new HashMap<>();
    private Set<Feature> features = new LinkedHashSet<Feature>();

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<PortType, Integer> getPorts() {
        return ports;
    }

    public void setPorts(Map<PortType, Integer> ports) {
        this.ports = ports;
    }

    public PurposeType getPurposeType() {
        return purposeType;
    }

    public void setPurposeType(PurposeType purposeType) {
        this.purposeType = purposeType;
    }


    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Boolean getCritical() {
        return critical;
    }

    public void setCritical(Boolean critical) {
        this.critical = critical;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: ").append(getIdentity()).append('\n');
        builder.append("Name: ").append(getName()).append('\n');
        builder.append("Origin: ").append(getOrigin()).append('\n');
        builder.append("Price: ").append(getPrice()).append('\n');
        builder.append("Features:\n");
        for(Feature feature: getFeatures()){
            builder.append('\t').append(feature.getName()).append(": ").append(feature.getValue()).append('\n');
        }
        builder.append("\tPurpose: ").append(getPurposeType().getName()).append('\n');
        if(ports.size() != 0) {
            builder.append("\tPorts:\n");
            for (Map.Entry<PortType, Integer> item : ports.entrySet()) {
                builder.append("\t\t").append(item.getKey()).append(": ").append(item.getValue()).append('\n');
            }
        }
        builder.append("Critical: ").append(getCritical()).append('\n');
        return builder.toString();
    }
}
