package com.epam.courses.xml;

import com.epam.courses.domain.Component;
import com.epam.courses.domain.features.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentXMLReader {
    public List<Component> read(String fileName) throws FileNotFoundException {
        XMLStreamReader reader = null;
        try {
            List<Component> components = new ArrayList<Component>();
            Component component = null;
            Map<PortType,Integer> ports = new HashMap<>();
            XMLInputFactory factory = XMLInputFactory.newFactory();
            reader = factory.createXMLStreamReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("component".equals(tagName)) {
                            component = new Component();
                            component.setIdentity(reader.getAttributeValue(null, "id"));
                        } else if ("name".equals(tagName)) {
                            component.setName(reader.getElementText());
                        } else if ("origin".equals(tagName)) {
                            component.setOrigin(reader.getElementText());
                        } else if("price".equals(tagName)) {
                            component.setPrice(Double.parseDouble(reader.getElementText()));
                        } else if("peripheral".equals(tagName)) {
                            Peripheral peripheral = new Peripheral();
                            peripheral.setElement(tagName);
                            peripheral.setValue(reader.getElementText());
                            component.getFeatures().add(peripheral);
                        } else if("energy-consumption".equals(tagName)) {
                            EnergyConsumption energyConsumption = new EnergyConsumption();
                            energyConsumption.setElement(tagName);
                            energyConsumption.setValue(reader.getElementText());
                            component.getFeatures().add(energyConsumption);
                        } else if("cooler".equals(tagName)) {
                            Cooler cooler = new Cooler();
                            cooler.setElement(tagName);
                            cooler.setValue(reader.getElementText());
                            component.getFeatures().add(cooler);
                        } else if("purpose".equals(tagName)) {
                            component.setPurposeType(PurposeType.valueOf(reader.getElementText()));
                        } else if("hdmi".equals(tagName)){
                            ports.put(PortType.HDMI, Integer.parseInt(reader.getElementText()));
                        } else if("com".equals(tagName)){
                            ports.put(PortType.COM, Integer.parseInt(reader.getElementText()));
                        } else if("usb".equals(tagName)){
                            ports.put(PortType.USB, Integer.parseInt(reader.getElementText()));
                        } else if("lpt".equals(tagName)) {
                            ports.put(PortType.LPT, Integer.parseInt(reader.getElementText()));
                        } else if("critical".equals(tagName)){
                            component.setCritical(Boolean.parseBoolean(reader.getElementText()));
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("component".equals(tagName)) {
                            component.setPorts(ports);
                            components.add(component);
                            ports = new HashMap<>();
                        }
                        break;
                    }
                }
            }
            return components;
        } catch (XMLStreamException e) {
            System.err.println("File not found");
            return null;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
        }
    }
}
