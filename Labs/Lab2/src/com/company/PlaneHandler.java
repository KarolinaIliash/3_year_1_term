package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

class PlaneHandler extends DefaultHandler {
    private List<Plane> planes;
    private Plane current = null;
    private PlaneEnum currentEnum = null;
    private EnumSet<PlaneEnum> withText;

    public  PlaneHandler(){
        planes = new ArrayList<>();
        withText = EnumSet.range(PlaneEnum.ORIGIN, PlaneEnum.SEATS);
    }

    //Check whether new plane was started or tag with textContent
    //Other check whether ammunition was started. We need it to
    //Find ammunition attribute
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs){
        if("plane".equals(qName)){
            current = new Plane();
            current.setModel(attrs.getValue(0));
        }
        else{
            PlaneEnum temp = null;
            try{
                temp = PlaneEnum.valueOf(qName.toUpperCase());}
            catch(IllegalArgumentException e){
                System.err.println(localName.toUpperCase() + " " + qName);
            }
            if(temp != null) {
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
            }
            if("ammunition".equals(qName) && attrs.getLength() == 1){
                current.getChars().setRackets(Integer.parseInt(attrs.getValue(0)));
            }
        }
    }

    //Check whether we know everything about current plane
    @Override
    public void endElement(String uri, String localName, String qName){
        if("plane".equals(qName)){
            planes.add(current);
        }
    }

    //Check whether textContent is available
    @Override
    public void characters(char[] ch, int start, int length){
        String s = new String(ch, start, length).trim();
        if(currentEnum != null){
            switch(currentEnum){
                case TYPE:
                    current.getChars().setType(s);
                    break;
                case AMMUNITION:
                    current.getChars().setAmmunition(Boolean.parseBoolean(s));

                    break;
                case RADAR:
                    current.getChars().setRadar(Boolean.parseBoolean(s));
                    break;
                case PRICE:
                    current.setPrice(Double.parseDouble(s));
                    break;
                case HEIGHT:
                    current.getParameters().setHeight(Double.parseDouble(s));
                    break;
                case LENGTH:
                    current.getParameters().setLength(Double.parseDouble(s));
                    break;
                case WINGSPAN:
                    current.getParameters().setWingspan(Double.parseDouble(s));
                    break;
                case MODEL:
                    current.setModel(s);
                    break;
                case ORIGIN:
                    current.setOrigin(s);
                    break;
                case SEATS:
                    current.getChars().setSeats(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
    public List<Plane> getPlanes(){
        return planes;
    }
}
