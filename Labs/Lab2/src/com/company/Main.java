package com.company;

import javax.xml.XMLConstants;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Variables for validation and some for parser purpose(fileName)
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "D:\\JavaProjects\\OOP\\Labs\\XmlParser\\src\\com\\company\\planes.xml";
        String schemaName = "D:\\JavaProjects\\OOP\\Labs\\XmlParser\\src\\com\\company\\planes.xsd";
        //Check whether xml is valid with xsd
        boolean isValid = true;
        Validation validator = new Validation();
        try{
            validator.validate(language, fileName, schemaName);
        } catch (IOException e){
            isValid = false;
            System.err.println("File " + fileName + " is not valid because " + e);
        } catch (SAXException e){
            isValid = false;
            System.err.println("File " + fileName + "is not valid because " + e);
        }
        if(isValid) {
            PlaneBuilderFactory builderFactory = new PlaneBuilderFactory();
            //Choosing parser
            AbstractPlanesBuilder builder = builderFactory.createPlanesBuilder("dom");
            builder.buildListPlanes(fileName);
            List<Plane> planes = builder.getPlanes();
            //Sorting items in reverse order
            planes.sort(new Comparator<Plane>() {
                @Override
                public int compare(Plane p1, Plane p2) {
                    return (p1.getPrice() > p2.getPrice()) ? -1 : 1;
                }
            });
            for (Plane plane : planes) {
                System.out.println(plane.toString());
            }
        }
        //Transform xml to html
        //With schema transform doesn't see elements
        Transform transform = new Transform();
        transform.transform("D:\\JavaProjects\\OOP\\Labs\\XmlParser\\src\\com\\company\\planes.xsl",
                            "D:\\JavaProjects\\OOP\\Labs\\XmlParser\\src\\com\\company\\planesWithoutSchema.xml",
                       "newPlanes.html");
    }
}
