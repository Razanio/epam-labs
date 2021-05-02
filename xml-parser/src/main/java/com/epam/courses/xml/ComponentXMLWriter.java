package com.epam.courses.xml;

import com.epam.courses.domain.Component;
import com.epam.courses.domain.Feature;
import com.epam.courses.domain.features.PortType;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class ComponentXMLWriter {
    public void write(List<Component> components, String fileName) throws FileNotFoundException, XMLStreamException {
        XMLStreamWriter writer = null;
        try {
            XMLOutputFactory factory = XMLOutputFactory.newFactory();
            writer = factory.createXMLStreamWriter(new FileOutputStream(fileName), "UTF-8");
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("components");
            writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            writer.writeAttribute("xmlns", "http://www.example.org/components");
            writer.writeAttribute("xsi:schemaLocation", "http://www.example.org/components components.xsd");
            for (Component component : components) {
                writer.writeStartElement("component");
                writer.writeAttribute("id", component.getIdentity());
                writer.writeStartElement("name");
                writer.writeCData(component.getName());
                writer.writeEndElement();
                writer.writeStartElement("origin");
                writer.writeCharacters(component.getOrigin());
                writer.writeEndElement();
                writer.writeStartElement("price");
                writer.writeCharacters(component.getPrice().toString());
                writer.writeEndElement();
                writer.writeStartElement("feature");
                for (Feature feature : component.getFeatures()) {
                    writer.writeStartElement(feature.getElement());
                    writer.writeCharacters(feature.getValue());
                    writer.writeEndElement();
                }
                writer.writeStartElement("purpose");
                writer.writeCharacters(component.getPurposeType().toString());
                writer.writeEndElement();
                writer.writeStartElement("port");
                for (Map.Entry<PortType, Integer> item : component.getPorts().entrySet()) {
                    writer.writeStartElement(item.getKey().toString());
                    writer.writeCharacters(item.getValue().toString());
                    writer.writeEndElement();
                }
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeStartElement("critical");
                writer.writeCharacters(component.getCritical().toString());
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
