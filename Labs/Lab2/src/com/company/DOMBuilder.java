package com.company;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

//Actually DOM parser
public class DOMBuilder extends AbstractPlanesBuilder{
    private DocumentBuilder documentBuilder;
    public DOMBuilder(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e){
            System.err.println("Error of DOM parser" + e);
        }
    }

    public void buildListPlanes(String fileName){
        Document doc = null;
        try{
            doc = documentBuilder.parse(fileName);
            //Getting root element
            Element root = doc.getDocumentElement();
            //Getting list of tags <plane>
            NodeList planesList = root.getElementsByTagName("plane");
            for(int i = 0; i < planesList.getLength(); i++){
                Element planeElement = (Element) planesList.item(i);
                Plane plane = buildPlane(planeElement);
                planes.add(plane);
            }
        } catch (IOException e){
            System.err.println("Error of IO " + e);
        }
          catch (SAXException e){
            System.err.println("Error of DOM parser " + e);
          }
    }

    //Parsing <plane>...</plane>
    private Plane buildPlane(Element planeEl){
        Plane plane = new Plane();
        //Get attributes and simpleTypes
        plane.setModel(planeEl.getAttribute("model"));
        plane.setOrigin(getElementTextContent(planeEl, "origin"));
        plane.setPrice(Double.parseDouble(getElementTextContent(planeEl, "price")));
        //Get complexTypes
        Element charsEl = (Element) planeEl.getElementsByTagName("chars").item(0);
        buildChars(charsEl, plane.getChars());
        Element paramsEl = (Element) planeEl.getElementsByTagName("parameters").item(0);
        buildParams(paramsEl, plane.getParameters());
        return plane;
    }
    //Parsing Chars
    private void buildChars(Element charsEl, Plane.Chars chars){
        chars.setType(getElementTextContent(charsEl, "type"));
        chars.setSeats(Integer.parseInt(getElementTextContent(charsEl, "seats")));
        chars.setRadar(Boolean.parseBoolean(getElementTextContent(charsEl, "radar")));
        Node ammunitionNode = charsEl.getElementsByTagName("ammunition").item(0);
        chars.setAmmunition(Boolean.parseBoolean(ammunitionNode.getTextContent()));
        //Check whether attribute rackets is existed and set value
        Element ammunitionElement = (Element) ammunitionNode;
        String racketsS = ammunitionElement.getAttribute("rackets");
        if(!racketsS.equals("")){
            chars.setRackets(Integer.parseInt(racketsS));
        }
    }
    //Parsing parameters
    private  void buildParams(Element paramsEl, Plane.Parameters parameters){
        parameters.setHeight(Double.parseDouble(getElementTextContent(paramsEl, "height")));
        parameters.setLength(Double.parseDouble(getElementTextContent(paramsEl, "length")));
        parameters.setWingspan(Double.parseDouble(getElementTextContent(paramsEl, "wingspan")));
    }
    //Auxiliary method which get textContent from tag elementName which is child of element
    private static String getElementTextContent(Element element, String elementName){
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
