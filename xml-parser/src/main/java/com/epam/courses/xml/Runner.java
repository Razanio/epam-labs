package com.epam.courses.xml;

import com.epam.courses.domain.Component;
import com.epam.courses.domain.comparators.CompareByPrice;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Runner
{
    public static void main ( String[] args  ) throws IOException, XMLStreamException {
        ComponentXMLValidator validator = new ComponentXMLValidator("src/main/java/com/epam/courses/resourses/components.xml");
        if (validator.validate()) {
            ComponentXMLReader reader = new ComponentXMLReader();
            List<Component> components = reader.read("src/main/java/com/epam/courses/resourses/components.xml");
            System.out.println("Before sort");
            for (Component component : components) {
                System.out.println(component);
            }
            Collections.sort(components, new CompareByPrice());
            System.out.println("After sort");
            for (Component component : components) {
                System.out.println(component);
            }
            ComponentXMLWriter writer = new ComponentXMLWriter();
            writer.write(components, "src/main/java/com/epam/courses/resourses/components-new.xml");

        } else {
            System.out.println(validator.getErrorMessage());
        }
    }
}
