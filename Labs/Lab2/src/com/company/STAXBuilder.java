package com.company;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//Actually STAX parser
public class STAXBuilder extends AbstractPlanesBuilder{
    private XMLInputFactory inputFactory;
    public STAXBuilder(){
        inputFactory = XMLInputFactory.newInstance();
    }

    //Parsing
    public void buildListPlanes(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(fileName);
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.PLANE) {
                        Plane plane = buildPlane(reader);
                        planes.add(plane);
                    }
                }
            }
        } catch (XMLStreamException e) {
            System.err.println("Error of STAX parser " + e);
        } catch (FileNotFoundException e) {
            System.err.println("File not found " + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Error of IO " + e);
            }
        }
    }
    //Parsing plane
    private Plane buildPlane(XMLStreamReader reader) throws XMLStreamException{
        Plane plane = new Plane();
        //Get Attribute value
        plane.setModel(reader.getAttributeValue(null, PlaneEnum.MODEL.getValue()));
        String name;
        //Get values of next elements (children of plane or end of plane)
        while(reader.hasNext()){
            int type = reader.next();
            switch (type){
                //In case of start, we get info about children
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch(PlaneEnum.valueOf(name.toUpperCase())){
                        case ORIGIN:
                            plane.setOrigin(getXMLText(reader));
                            break;
                        case PRICE:
                            plane.setPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case CHARS:
                            buildChars(reader, plane.getChars());
                            break;
                        case PARAMETERS:
                            buildParameters(reader, plane.getParameters());
                            break;
                    }
                    break;
                //In case of end, we know everything about current plane
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.PLANE){
                        return plane;
                    }
                    break;
            }
        }
        return plane;
    }

    //Parsing chars the same as parsing plane
    private void buildChars(XMLStreamReader reader, Plane.Chars chars){
        int type;
        String name;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (PlaneEnum.valueOf(name.toUpperCase())) {
                            case TYPE:
                                chars.setType(getXMLText(reader));
                                break;
                            case SEATS:
                                chars.setSeats(Integer.parseInt(getXMLText(reader)));
                                break;
                            case RADAR:
                                chars.setRadar(Boolean.parseBoolean(getXMLText(reader)));
                                break;
                            case AMMUNITION:
                                if (reader.getAttributeCount() == 1) {
                                    chars.setRackets(Integer.parseInt(reader.getAttributeValue(0)));
                                }
                                chars.setAmmunition(Boolean.parseBoolean(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.CHARS) {
                            return;
                        }
                        break;

                }
            }
        } catch (XMLStreamException e){
            System.err.println("Unknown element in tag chars");
        }
    }

    //Parsing parameters the same as parsing plane
    private void buildParameters(XMLStreamReader reader, Plane.Parameters params){
        int type;
        String name;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (PlaneEnum.valueOf(name.toUpperCase())) {
                            case HEIGHT:
                                params.setHeight(Double.parseDouble(getXMLText(reader)));
                                break;
                            case LENGTH:
                                params.setLength(Double.parseDouble(getXMLText(reader)));
                                break;
                            case WINGSPAN:
                                params.setWingspan(Double.parseDouble(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (PlaneEnum.valueOf(name.toUpperCase()) == PlaneEnum.PARAMETERS) {
                            return;
                        }
                }
            }
        }catch (XMLStreamException e){
            System.err.println("Unknown element in tag parameters");
        }
    }

    //Auxiliary method for getting textContent
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException{
        String text = null;
        if(reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
