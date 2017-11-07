package com.company;

public class PlaneBuilderFactory {
    private enum ParserType{
        SAX, DOM, STAX
    }
    public AbstractPlanesBuilder createPlanesBuilder(String parserType){
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch (type){
            case SAX:
                return new SAXBuilder();
            case DOM:
                return new DOMBuilder();
            case STAX:
                return new STAXBuilder();
            default:
                return new DOMBuilder();
        }
    }
}
