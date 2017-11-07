package com.company;


import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;

//Actually SAX parser
public class SAXBuilder extends AbstractPlanesBuilder{
    private PlaneHandler ph;//Handler for parsing
    private XMLReader reader;
    public SAXBuilder(){
        ph = new PlaneHandler();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(ph);
        } catch (SAXException e) {
            System.err.println("Error of SAX parser " + e);
        }
    }

    //Parsing. But all works is doing with handler.
    public void buildListPlanes(String fileName){
        try {
            reader.parse(fileName);
        } catch(IOException e){
            System.err.println("Error of IO " + e);
        } catch (SAXException e){
            System.err.println("Error of SAX parser " + e);
        }
        planes = ph.getPlanes();
    }
}
